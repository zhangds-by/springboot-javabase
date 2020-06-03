常见的RPC的调用技术

    SpringCloud：Spring的，基于Socket的，SOA架构的分布式框架
    
    Dubbo(x)：阿里巴巴的，基于Socket的，SOA架构的分布式框架
    
    WebService：跨语言的，基于SOAP协议，走xml数据或json数据
    
    Hessian：跨语言的，基于Binary-RPC协议，走二进制数据
    
    HttpClient：通常用于RESTful风格的调用，跨语言，基于http和json
    
    jdk原生：HttpURLConnection
    
webService理解：
    
    WebService通过http协议发送请求和接收结果时，发送的请求内容和结果内容都采用xml格式封装，
    并增加了一些特定的http消息头，以说明 http消息的内容格式，这些特定的http消息头和xml内容格式就是SOAP协议。

    WebService应用程序（服务端）
    
    SOAP(Simple Object Access Protocal)，即简单对象访问协议，它用来描述传递信息的格式。
    
    SOAP是一种简单的基于xml的协议和规范，它使应用程序通过http来交换信息。SOAP = http + xml
    
    平台语言无关性——WebService基于SOAP协议，且发布的所有服务都有对应的xml(wsdl)，可以实现跨平台、跨语言的支持
    安全通信——WebService走http请求，不受防火墙限制
    功能复用——通过使用WebService，可以暴露共用服务，实现功能复用
    
    WSDL(Web Services Description Language)，即网络服务描述语言，可描述WebService服务。
    
    每一个WebService服务必定会有它对应的WSDL，通过这个WSDL，可以在不同语言平台上解析成对应语言的源码，进而被开发者使用
    
webservice应用场景

    提供统一服务访问接口（没有语言限制）
    系统间服务发布与调用
    分布式（可以用但不方便，当然现在用的更多的是SpringCloud和Dubbo(x)）
    对性能要求不是很高的服务
    
java实现webservice的方式

    jdk原生的WebService方式（基础）
    使用Apache的CXF框架（常用）
    使用Apache的axis框架（多语言，重量级）
    使用XFire框架（已没落）
    
wsdl访问地址为
    
    jws：
        http://localhost:9000/getRandomCode?wsdl
        
    cxf：
        http://127.0.0.1:8080/soap/cxf?wsdl