rabbitmq

    安装配置erlang
                
        wget http://erlang.org/download/otp_src_22.0.tar.gz
        
        tar -zxvf otp_src_21.1.tar.gz
        cd otp_src_21.1
        
        # erlang编译安装默认是装在/usr/local下的bin和lib中，统一装到/usr/local/erlang。
        mkdir -p /usr/local/erlang
         
        # 在编译之前，必须安装以下依赖包
        yum install -y make gcc gcc-c++ m4 openssl openssl-devel ncurses-devel unixODBC unixODBC-devel java java-devel
         
        # 执行安装路径
        ./configure --prefix=/usr/local/erlang
        
        # 编译安装
        make && make install
        
        # 修改配置环境
        vim /etc/profile
        
        PATH=$PATH:/usr/local/erlang/bin
        
        source /etc/profile
        
        #查看erlang版本
        erl
        
        erlang 和 rabbitmq 版本对应 Erlang Versions
            https://www.rabbitmq.com/which-erlang.html

    安装配置rabbitmq
              
        # 配置环境变量
        PATH=$PATH:/usr/local/rabbitmq_server-3.7.8/sbin
    
        # 添加web管理插件
        rabbitmq-plugins enable rabbitmq_management
        
        http://192.168.25.128:15672/        guest/guest
        
        启动： rabbitmq-server -detached
        停止： rabbitmqctl stop
         
        3.7+ 以上版本需要添加 /etc/rabbitmq/rabbitmq.conf
        下载地址 ：  https://github.com/rabbitmq/rabbitmq-server/blob/master/docs/rabbitmq.conf.example
        启动
        Warning: PID file not written; -detached was passed. //版本遗留问题
        
        修改配置文件目录
        /usr/local/rabbitmq/sbin/rabbitmq-defaults
        
    rabbitmq使用
    
        添加帐号:name 密码:passwd
        #rabbitmqctl add_user name passwd
        赋予其administrator角色
        #rabbitmqctl set_user_tags name administrator
        设置权限
        #rabbitmqctl set_permissions -p / name ".*" ".*" ".*"