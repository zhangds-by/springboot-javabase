项目启动：

    Exception in thread "main" java.lang.AbstractMethodError: 
    org.springframework.boot.context.config.ConfigFileApplicationListener.supportsSourceType(Ljava/lang/Class;)Z
    
    jar包冲突，ctrl + shift + alt + u 在pom文件中打开jar包依赖树图
    
    springboot maven文件中不需要添加任何与spring相关的依赖包
    
yum安装rabbitmq找不到erlang依赖

    erlang安装问题
        使用yum安装erlang