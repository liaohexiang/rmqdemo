package com.example.demo;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TouCunService {



    @Transactional
    public OccupyResVO occupy(OccupyReqVO occupyReqVO){

        /** 流水表主要字段, NOTE:ukRequestID是接口调用方提供的唯一键
         * -------------------------------------------------------------------------
         *  流水ID(自增) | ukRequestID(幂等ID) | requestChannnel(调用方编号) | caseID（场景编号） | status(状态码) | fail_reason
         * -------------------------------------------------------------------------
         * ukRequestID、requestChannnel、caseID 作为流水表的联合唯一键
         *
         * 1 select 流水ID,status from 流水表 where ukRequestID=? and requestChannnel=? and caseID=?
         * 2 如果存在这条流水数据返回 {
         *                          ukBizID:流水ID,
         *                          resCode:status
         *                         }
         * 3 如果不存在
         *      3.1  计算头寸数据是否满足
         *          满足
         *             流水ID = INSERT INTO 流水表 (ukRequestID, requestChannnel,caseID,status) VALUES (?,?,?,'YOUR_SUCCESS_CODE')
         *              update 头寸数据。。。
         *              return {
         *                  ukBizID:流水ID,
         *                  resCode:status
         *              }
         *          不满足
         *              return {
         *              resCode: YOUR_FAIL_CODE
         *              }
         *      3.2 计算头寸数据是否满足且明确记录业务失败
         *          满足
         *              同3.1
         *          不满足 -- 如果要明确记录这笔数据失败，这种情况下ukRequestID只能使用一次
         *              流水ID = INSERT INTO 流水表 (ukRequestID, requestChannnel,caseID,status,fail_reason) VALUES (?,?,?,'YOUR_FAIL_CODE','YOUR_FAIL_REASON')
         *              return {
         *                  ukBizID:流水ID,
         *                  resCode：status，
         *                  resMsg:fail_reason
         *              }
         */


    }



    /**
     * 这三个字段构成请求流水表里面的业务唯一约束
     * 不限于这三个字段，字段可以更多粒度控制就可以更细
     */
    private class OccupyReqVO {

        private Long ukRequestID;//幂等ID，如果是委托调用，这里就是头寸业务唯一键（头寸表里的主键或者多个业务字段产生的唯一Token)
        private String requestChannnel;//服务调用方编号（可以是系统编号，比如：WT,ITP,由服务提供方提供）

        private String caseID;//场景编码（调用方可以有不同的场景调用同一个接口）

        public Long getUkRequestID() {
            return ukRequestID;
        }

        public String getRequestChannnel() {
            return requestChannnel;
        }

        public String getCaseID() {
            return caseID;
        }
    }

    private class OccupyResVO {
        private Long ukBizID;// 对头寸来说这个字段可以是主表里面的自增ID(包含但不限于)
        private String resCode;
        private String resMsg;

        public void setUkBizID(Long ukBizID) {
            this.ukBizID = ukBizID;
        }

        public void setResCode(String resCode) {
            this.resCode = resCode;
        }

        public void setResMsg(String resMsg) {
            this.resMsg = resMsg;
        }
    }
}
