package com.zhangds.webservice.jws;


import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * 基于JWS的WebService服务提供者
 * Create by zhangds
 * 2020-05-28 13:12
 **/
@WebService
public class JWSProvider {

    public String getRandomCode(String name){
        System.out.println("getRandomCode()被调用......");
        return name + Math.random();
    }

    public static void main(String[] args) {
        System.out.println("开始发布服务......");
        Endpoint.publish("http://localhost:9000/getRandomCode", new JWSProvider());
        System.out.println("发布服务成功......");
    }
}
