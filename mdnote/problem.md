项目启动：

    Exception in thread "main" java.lang.AbstractMethodError: 
    org.springframework.boot.context.config.ConfigFileApplicationListener.supportsSourceType(Ljava/lang/Class;)Z
    
    jar包冲突，ctrl + shift + alt + u 在pom文件中打开jar包依赖树图
    
    springboot maven文件中不需要添加任何与spring相关的依赖包
    
yum安装rabbitmq找不到erlang依赖

    erlang安装问题
        使用yum安装erlang
        
        
nginx

    执行./nginx -s reload报错
    nginx: [error] open() "/var/run/nginx/nginx.pid" failed (2: No such file or directory)
    
    在/var/run 创建nginx目录
    或
    修改/usr/local/nginx/conf/nginx.conf 配置文件
        pid /usr/local/nginx/logs/nginx.pid;
        
    先要执行一遍 ./sbin/nginx
    再执行 ./nginx -s reload
    