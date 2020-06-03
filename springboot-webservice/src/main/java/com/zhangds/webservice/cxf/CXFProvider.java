package com.zhangds.webservice.cxf;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * 基于CXF的服务提供者接口
 * Create by zhangds
 * 2020-05-28 14:04
 **/
//@WebService(targetNamespace="http://webservice.zhangds.com")
@WebService(targetNamespace="http://127.0.0.1:8080/soap/cxf?wsdl")
public interface CXFProvider {

    //标注该方法为webservice暴露的方法, 用于向外公布, 它修饰的方法是webservice方法, 类似一个注释信息。
    @WebMethod
    String getNameHashCode(String name);
}
