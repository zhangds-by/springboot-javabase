单线程/多线程/主从多线程模型

    EventLoopGroup acceptorGroup = new NioEventLoopGroup(); //负责监听线程的线程数 
    EventLoopGroup clientGroup = new NioEventLoopGroup(); //负责处理客户端任务的线程组 
    ServerBootstrap bootstrap = new ServerBootstrap(); //启动服务的辅助类，配置有关socket参数  
    bootstrap.group(acceptorGroup, clientGroup); // 传入线程组是否是同一个
    
    单线程：监听=1 处理=1 同一个线程组 
    单线程：监听=1 处理>1 不同线程组 
    单线程：监听>1 处理>1 不同线程组 
    
    Netty的线程模型在启动辅助类中创建不同的EventLoopGroup实例并通过适当的参数配置，就可以支持三种Reactor线程模型
    
