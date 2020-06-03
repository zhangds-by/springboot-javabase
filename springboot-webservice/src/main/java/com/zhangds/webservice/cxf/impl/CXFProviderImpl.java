package com.zhangds.webservice.cxf.impl;

import com.zhangds.webservice.cxf.CXFProvider;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

/**
 * 服务接口实现类
 * Create by zhangds
 * 2020-05-28 14:06
 **/
@WebService(serviceName="CXFService",//对外发布的服务名
        targetNamespace="http://127.0.0.1:8080/soap/cxf?wsdl",//指定你想要的名称空间，通常使用使用包名反转
        endpointInterface= "com.zhangds.webservice.cxf.CXFProvider")//服务接口全路径, 指定做SEI（Service EndPoint Interface）服务端点接口
@Component
public class CXFProviderImpl implements CXFProvider {
    @Override
    public String getNameHashCode(String name) {
        System.out.println("调用基于CXF服务getNameHashCode......");
        return name + name.hashCode();
    }
}
