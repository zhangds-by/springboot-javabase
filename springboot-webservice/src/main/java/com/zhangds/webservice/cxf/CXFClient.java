package com.zhangds.webservice.cxf;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

/**
 * Create by zhangds
 * 2020-05-28 15:19
 **/
public class CXFClient {
    public static void main(String[] args) {
//        CXFClient.proxyInvoke();
        CXFClient.DynamicInvoke();
    }


    /**
     * 代理类工厂的方式,需要拿到对方的接口地址
     */
    public static void proxyInvoke(){
        // 接口地址
        String address = "http://127.0.0.1:8080/soap/cxf?wsdl";
        // 代理工厂
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        // 设置代理地址
        jaxWsProxyFactoryBean.setAddress(address);
        // 设置接口类型
        jaxWsProxyFactoryBean.setServiceClass(CXFProvider.class);
        // 创建一个代理接口实现
        CXFProvider us = (CXFProvider) jaxWsProxyFactoryBean.create();
        // 数据准备
        String name = "zhangds";
        // 调用代理接口的方法调用并返回结果
        String result = us.getNameHashCode(name);
        System.out.println("返回结果:" + result);
    }

    public static void DynamicInvoke(){
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://127.0.0.1:8080/soap/cxf?wsdl");
        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));
        Object[] objects = new Object[0];
        try {
            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke("getUserName", "maple");
            System.out.println("返回数据:" + objects[0]);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}
