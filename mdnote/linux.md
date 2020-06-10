linux安装工具
    
    mysql
        查看是否安装：rpm -qa | grep mysql
        卸载安装：rpm -e --nodeps mysql--version
        
        检查和创建mysql用户组和用户
            cat /etc/group | grep mysql
            cat /etc/passwd | grep mysql
            groupadd mysql
            useradd -r -g mysql mysql
            
        创建data目录
            mkdir /usr/local/mysql/data
        
        授权
            chown -R mysql:mysql /usr/local/mysql
            chmod -R 755 /usr/local/mysql
            
        初始化mysql并获取末尾密码（x3<l7siFOyug）
            ./mysqld --initialize --user=mysql --datadir=/usr/local/mysql/data --basedir=/usr/local/mysql
        
        配置文件/etc/my.cnf
            [mysqld]
            datadir=/usr/local/mysql/data
            port = 3306
            lower_case_table_names=1
            
        启动mysql
            /usr/local/mysql/support-files/mysql.server start
        
            提示PID file启动失败，卸载系统自带的MariaDB数据库
                rpm -qa | grep mariadb
                rpm -e --nodeps fileName
                
            添加软链接启动服务
                ln -s /usr/local/mysql/support-files/mysql.server /etc/init.d/mysql
                ln -s /usr/local/mysql/bin/mysql /usr/bin/mysql
                service mysql restart
                
        修改密码
            set password for root@localhost = password('root');
            
        修改权限密码
            grant all privileges  on *.* to root@'%' identified by "root";
            
        开放远程连接
            use mysql;
            update user set user.Host='%' where user.User='root';
            flush privileges;
            
        设置开机自启
            将服务文件拷贝到init.d下，并重命名为mysql
                cp /usr/local/mysql/support-files/mysql.server /etc/init.d/mysqld
            赋予可执行权限
                chmod +x /etc/init.d/mysqld
            添加服务
                chkconfig --add mysqld
            显示服务列表
                chkconfig --list
            
    zookeeper
        复制命名配置文件
            cp zoo_sample.cfg zoo.cfg
        
        修改配置文件
            dataDir=/usr/local/zookeeper-3.4.6/data
            dataLogDir = /usr/local/zookeeper-3.4.6/log
            
        配置环境变量/etc/profile
            export ZOOKEEPER=/usr/local/zookeeper-3.4.6
            PATH=$PATH:$JAVA_HOME/bin:$ZOOKEEPER/bin
        
        启动
            zkServer.sh start 
            
        启动客户端
            zkCli.sh
            
            
    consul
        
        安装
        
            consul 移动到 /usr/local/bin/ 下
            chmod 777 consul
        
        启动（ /usr/local/bin下执行 ）
            consul agent -dev -ui -node=consul -dev -client=192.168.25.128
            
            consul agent -dev -client 0.0.0.0 -ui
            
        访问端口（8500）
            
            
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
        
        
        
        
linux指令

    全局搜索：whereis mysql
            find / -name mysql
            
    删除目录和文件：rm -rf 
    
    端口占用
        ps -ef | grep 80
        	
        netstat -anp | grep :80
    
        lsof -i:80
    
        netstat -tunlp | grep :80	
    
        netstat -lnpt |grep 5672
    
        netstat -an | grep :80
    
        #查看进程详情
        ps 12345
        
    防火墙
    
    	#开放端口
    	firewall-cmd --zone=public --add-port=5672/tcp --permanent
    
    	#关闭端口
    	firewall-cmd --zone=public --remove-port=5672/tcp --permanent
    
    	#配置生效
    	firewall-cmd --reload
    
    	#查看所有开放端口
    	firewall-cmd --zone=public --list-ports
    
    	systemctl stop firewalld.service
    
    	firewall-cmd --state
    
    	#禁止开机自启
    	systemctl disable firewalld.service
    	
    目录信息
    
    	/etc : 系统配置文件存放的目录 /etc/sysconfig
    	/usr/local/lib： 系统使用的函数库的目录
    	/usr/local
    	/opt ： 额外安装软件
    	/tmp： 一般用户或正在执行的程序临时存放文件的目录
    	/usr： 应用程序存放目录，
    	/usr/bin 存放应用程序，
    	/usr/share 存放共享数据，
    	/usr/lib 存放不能直接运行的，却是许多程序运行所必需的一些函数库文件。
    	/usr/local:存放软件升级包。
    	/usr/share/doc: 系统说明文件存放目录。
    	/usr/share/man: 程序说明文件存放目录
    	/var： 放置系统执行过程中经常变化的文件		/var/log
    
安装指令
    
    使用yum安装的目录文件都在/usr/lib/
    
        如rabbitmq ： /usr/lib/rabbitmq/lib/rabbitmq_server-3.7.25
    
    配置文件在/etc
    
        如rabbitmq ： 
    
    卸载
        使用yum和rpm安装卸载
        yum list | grep erlang
        yum remove esl-erlang.x86_64

    