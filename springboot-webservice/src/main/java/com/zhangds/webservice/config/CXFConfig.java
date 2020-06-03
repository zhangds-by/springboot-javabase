package com.zhangds.webservice.config;

import com.zhangds.webservice.cxf.CXFProvider;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * CXF发布webService配置
 * Create by zhangds
 * 2020-05-28 14:23
 **/
@Configuration
@ComponentScan(basePackages = "com.zhangds.*")
public class CXFConfig {

    @Autowired
    private Bus bus;

    @Autowired
    private CXFProvider cxfProvider;

    @SuppressWarnings("all")
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new CXFServlet(), "/soap/*");
    }

    /**
     * 站点服务
     * @return
     */
    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, cxfProvider);
        endpoint.publish("/cxf");
        return endpoint;
    }

}
