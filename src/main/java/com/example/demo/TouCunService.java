package com.example.demo;

import org.springframework.transaction.annotation.Transactional;

public class TouCunService {



    @Transactional
    public OccupyResponse occupy(OccupyRequest occupyRequest){

        /** 流水表主要字段
         * ---------------------------------------------------
         *  ukRequestID | requestChannnel | caseID | status
         * ---------------------------------------------------
         */
        /**
         * 1 select status from 流水表 where ukRequestID=? and requestChannnel=? and caseID=?
         * 2 如果存在这边数据返回 status:成功/失败
         * 3 如果不存在
         *      3.1  计算头寸数据是否满足
         *          满足
         *              insert 流水表 set ukRequestID =?  requestChannnel=? caseID=? status="failure"
         *              update 头寸数据。。。
         *          不满足
         *              return 失败
         *
         */


    }

    private class OccupyResponse {
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

    /**
     * 这三个字段构成请求流水表里面的业务唯一约束
     * 不限于这三个字段，字段可以更多粒度控制就可以更细
     */
    private class OccupyRequest {

        private Long ukRequestID;//如果是委托调用，这里就是头寸业务唯一键（头寸表里的主键或者多个业务字段产生的唯一Token)
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
}