#单线程/多线程/主从多线程模型


```
    EventLoopGroup acceptorGroup = new NioEventLoopGroup(); //负责监听线程的线程数 
    EventLoopGroup clientGroup = new NioEventLoopGroup(); //负责处理客户端任务的线程组 
    ServerBootstrap bootstrap = new ServerBootstrap(); //启动服务的辅助类，配置有关socket参数  
    bootstrap.group(acceptorGroup, clientGroup); // 传入线程组是否是同一个
    
    单线程：监听=1 处理=1 同一个线程组 
    单线程：监听=1 处理>1 不同线程组 
    单线程：监听>1 处理>1 不同线程组 
    
    Netty的线程模型在启动辅助类中创建不同的EventLoopGroup实例并通过适当的参数配置，就可以支持三种Reactor线程模型
```

# 模块说明

```
zhangds:
    simple：Netty Server 和 Client 简单实现
    buffer：ByteBuf的操作

tuling:
    splicing
        demo: 模拟粘包/拆包的现象，设置option(ChannelOption.SO_RCVBUF, 16)，将接收缓冲区大小设小
        delimiter：自定义分隔符解决粘包/拆包，DelimiterBasedFrameDecoder
        linebase：回车换行符进行分割解决粘包/拆包，LineBasedFrameDecoder
        fixed：定长解决粘包/拆包，FixedLengthFrameDecoder
        fixed：消息头定义报文长度的字段，
    checkread: 读取数据 channelRead 和 channelReadComplete 方法调用的观察

```