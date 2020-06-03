单线程/多线程/主从多线程模型

    EventLoopGroup acceptorGroup = new NioEventLoopGroup(); //负责监听线程的线程数 
    EventLoopGroup clientGroup = new NioEventLoopGroup(); //负责处理客户端任务的线程组 
    ServerBootstrap bootstrap = new ServerBootstrap();
    bootstrap.group(acceptorGroup, clientGroup); // 传入线程组是否是同一个
    
    单线程：监听=1 处理=1 同一个线程组 
    单线程：监听=1 处理>1 不同线程组 
    单线程：监听>1 处理>1 不同线程组 