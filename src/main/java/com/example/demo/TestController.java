package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@RestController("user")
public class TestController {
    @GetMapping("hello")
    public String hello(){
        return "hellotest";
    }


    @GetMapping("test")
    public void test(HttpServletResponse response) throws IOException, InterruptedException {

        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");

       //final PrintWriter writer = response.getWriter();
       final OutputStream out = response.getOutputStream();
        int i=0;
        while(i<20){
            //writer.write("第响应"+i);
            out.write(new String("第响应"+i).getBytes(StandardCharsets.UTF_8));
            System.out.println("第响应"+i);
            //writer.flush();
            i++;
            Thread.sleep(10);
        }
         new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    Thread.sleep(60);
                    //writer.write("end response");
                    System.out.println(out.getClass());
                    //writer.flush();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        }).start();
        //writer.close();
    }
}
