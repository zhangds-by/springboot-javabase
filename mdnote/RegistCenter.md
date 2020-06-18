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
            
            
        > 基本
        
        1. 类似hdfs的树形文件结构
        
        1. watch事件，是一次性触发的，当watch监视的数据发生变化时，通知设置了该watch的client，即watcher
        
        1. 三个角色：Learner，Follower，Observer
        
        > 应用场景
        
        数据发布与订阅（配置中心）
        
            全局的配置信息，服务式服务框架的服务地址列表
        
        负载均衡
        
            生产者负载均衡
        
            消费负载均衡：同一个应用戒同一个服务的提供方都会部署多份，达到对等服务。
        
        命名服务(Naming Service)
        
            在分布式系统中，通过使用命名服务，客户端应用能够根据挃定名字来获取资源戒服务的地址，提供者等信息。
            服务地址列表
                服务提供者服务发布：ZK上的挃定节点/dubbo/${serviceName}/providers目彔下写入自己的URL地址
                服务消费者订阅：订阅/dubbo/${serviceName}/providers目彔下的提供者URL地址， 并向/dubbo/${serviceName} /consumers目彔下写入自己的URL地址。
                
                
        分布式通知/协调
        
            ZooKeeper中特有watcher注册不异步通知机制，实现对数据变更的实时处理
                心跳检测机制：zk上的节点关联检测系统和被检测系统
                系统调度模式：
                    控制台：控制推送系统迚行相应的推送工作，
                    推动系统：zk节点状态的修改，通知给注册Watcher的客户端
                工作汇报模式：任务分发系统，子任务启劢后，到zk来注册一个临时节点，并且定时将自己的迚度迚行汇报，任务管理者就能够实时知道任务迚度。
                
        集群管理与Master选举
            
            实时集群机器存活性监控系统
                a.客户端在节点 x 上注册一个Watcher，那么如果 x 的子节点变化了，会通知该客户端。 
                b.创建EPHEMERAL类型的节点，一旦客户端和服务器的会话结束或过期，那么该节点就会消失。
                
                监控系统在 /clusterServers 节点上注册一个Watcher，以后每劢态加机器，
                那么就往 /clusterServers 下创建一个 EPHEMERAL类型的节点：/clusterServers/{hostname}. 
                这样，监控系统就能够实时知道机器的增减情况，至于后续处理就是监控系统的业务了。
                
            Master选举
                ZooKeeper的强一致性，能够保证在分布式高并发情况下节点创建的全局唯一性
                所有的请求最终在ZK上创建结果的一种可能情况是这样： /currentMaster/{sessionId}-1 , 
                /currentMaster/{sessionId}-2 , /currentMaster/{sessionId}-3 , 每次选取序列号最小的那个机器作为Master
                
        分布式锁
        
            保持独占：
                所有试图来获取这个锁的客户端，最终只有一个可以成功获得这把锁。
                通常的做法是把zk上的一个znode看作是一把锁，通过create znode的方式来实现。
                所有客户端都去创建 /distribute_lock 节点，最终成功创建的那个客户端也即拥有了这把锁。
                
            控制时序：
                所有视图来获取这个锁的客户端，最终都是会被安排执行，有个全局时序
                /distribute_lock 已绊预先存在，客户端在它下面创建临时有序节点，
                Zk的父节点（/distribute_lock）维持一份sequence,保证子节点创建的时序性，从而也形成了每个客户端的全局时序。
                
        分布式队列
            
            先进先出队列：控制时序
            
            等到队列成员聚齐后的才统一挄序执行：
                在/queue 这个znode下预先建立一个/queue/num 节点，并且赋值为n，表示队列大小
                分布式环境中，一个大任务Task A，需要在很多子任务完成（戒条件就绪）情况下才能进行。
                凡是其中一个子任务完成，在/taskList 下建立自己的临时时序节点（CreateMode.EPHEMERAL_SEQUENTIAL），
                当 /taskList 发现自己下面的子节点满足指定个数，就可以进行行下一步挄序迚行处理。
            
        > 配置
        
            tar zxvf /usr/local/temp/zookeeper-3.4.8.tar.gz
            cp -r /usr/local/temp/zookeeper-3.4.8  /usr/local/zookeeper
        
            修改环境变量： 
            vi /etc/profile
            export ZOOKEEPER_HOME=/usr/local/zookeeper
            export PATH=.:$HADOOP_HOME/bin:$ZOOKEEPER_HOME/bin:$JAVA_HOME/...
            
            刷新： 
            source /etc/profile
            
            修改配置文件
            cd /usr/local/zookeeper/conf
            mv zoo_sample.cfg zoo.cfg
            
            dataDir=/usr/local/zookeeper/data
            集群配置
            server.A = B:C:D
            A 表示这个是第几号服务器,
            B 是这个服务器的 ip 地址；
            C 表示的是这个服务器与集群中的 Leader
            服务器交换信息的端口；
            D 表示的是万一集群中的 Leader
            server.0=bhz:2888:3888
            server.1=hadoop1:2888:3888
            server.2=hadoop2:2888:3888
            
            服务器标识配置：
            创建文件夹：mkdir data
            创建文件myid并填写内容为0：vi myid (内容为服务器标识 ： 0)
            
            复制并把hadoop01、hadoop02中的myid文件里的值修改为1和2，路径(vi /usr/local/zookeeper/data/myid)
            
            启动
            zkServer.sh start
            
            状态
            zkServer.sh
            status(在三个节点上检验zk的mode,一个leader和俩个follower)
            
            防火墙中放行2181端口
            
            
consul
    
    安装
    
        consul 移动到 /usr/local/bin/ 下
        chmod 777 consul
    
    启动（ /usr/local/bin下执行 ）
        consul agent -dev -ui -node=consul -dev -client=192.168.25.128
        
        consul agent -dev -client 0.0.0.0 -ui
        
    访问端口（8500）