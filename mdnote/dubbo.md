> SOA架构(Service Oriented Ambiguity)
    
    专门访问数据库服务/项目  
    数据访问控制和代码复用
    
    1. Dubbo 做为服务
    2. WebService 做为服务
    3. Dubbox 做为服务
    4. 服务方就是 web 项目 调用 web 项目的控制器
    5. 使用 HttpClient 可以调用其他项目的控制器
    
    
> RPC(Remote Procedure Call Protocol) : 远程过程调用协议

通过互联网调用远程服务器提供的服务

> Dubbo : RPC 服务框架

    1. Provider : 提供者 服务发布方
    2. Consumer : 消费者 调用服务方
    3. Container : Dubbo容器 依赖于 Spring 容器
    4. Registry : 注册中心 当 Container 启动时把所有可以提供的服务列表上 Registry 中进行注册
        告诉 Consumer 提供了什么服务和服务方在哪里
    5. Monitor : 监听器
    
    过程：
        启动Provider，在注册中心注册所有提供的服务列表
        Consumer 从Registry中获取服务列表和Provider的地址，进行订阅
        Provider有修改，通过注册中心把消息推送给Consumer （观察者发布/订阅设计模式）
        根据获取的地址调用服务功能 （代理设计模式：创建一个Provider代理对象，调用Provider真实功能）
        
> 支持协议

    1. Dubbo: 使用NIO和线程池进行处理
    2. RMI: JDK提供的协议, 远程方法调用协议
    3. Hession 基于http协议, http请求支持
    
> 配置

    1. <dubbo: application/>给provider起名 在monitor或管理工具中区别是哪个provider
    2. <dubbo: registry/>配置注册中心
        address: 注册中心的ip和端口
        protocol 使用哪种注册中心
    3. <dubbo:protocol/>配置协议 
        name协议名称
        port: consumer invoke provider时的端口号
    4. <dubbo:service/> 注册接口
        ref引用接口实现类 <bean> 的id值
        
    给当前 Provider 自定义个名字
    <dubbo:application name = "dubbo service" />
    <!-- 配置注册中心  -->
    <dubbo:registry address="192.168.139.130:2181" protocol="zookeeper"></dubbo:registry>
    <!-- 配置端口 -->
    <dubbo:protocol name="dubbo" port="20888"></dubbo:protocol>
    <!-- 注册功能 -->
    <dubbo:service interface="com.zhangds.service.DemoService" ref="demoServiceImpl"></dubbo:service>
    <bean id="demoServiceImpl" class="com.zhangds.service.impl.DemoServiceImpl"></bean>
    
    客户端配置
    <!-- 给当前Provider自定义个名字 -->
    <dubbo:application name="dubbo-consumer"/>
    <!-- 配置注册中心  -->
    <dubbo:registry address="192.168.139.130:2181" protocol="zookeeper"></dubbo:registry>
    <!-- 配置注解扫描 -->
    <dubbo:annotation package="com.zhangds.service.impl"/>

    
> dubbo-admin管理界面

> maven-assembly-plugin打包插件，指定打包类型

    