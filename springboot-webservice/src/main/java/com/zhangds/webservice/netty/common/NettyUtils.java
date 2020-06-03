package com.zhangds.webservice.netty.common;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Create by zhangds
 * 2020-05-15 11:45
 **/
public class NettyUtils {

    /**
     * 初始化客户端请求和处理线程组，服务器启动相关配置信息
     * @Author zhangds
     * @Date 2020/5/15 12:01
     * @Return
     */
    public static void initClient(EventLoopGroup group, Bootstrap bootstrap){
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        // 绑定线程组
        bootstrap.group(group);
        // 设定通讯模式为NIO
        bootstrap.channel(NioSocketChannel.class);
    }

    /**
     * 初始化监听客户端请求和处理客户端操作线程组，服务启动相关配置信息
     * @Author zhangds
     * @Date 2020/5/15 12:04
     * @Return
     */
    public static void initServer(EventLoopGroup acceptorGroup, EventLoopGroup clientGroup, ServerBootstrap bootstrap){
        // 初始化线程组,构建线程组的时候，如果不传递参数，则默认构建的线程组线程数是CPU核心数量。
        acceptorGroup = new NioEventLoopGroup();
        clientGroup = new NioEventLoopGroup();
        // 初始化服务的配置
        bootstrap = new ServerBootstrap();
        // 绑定线程组
        bootstrap.group(acceptorGroup, clientGroup);
        // 设定通讯模式为NIO， 同步非阻塞
        bootstrap.channel(NioServerSocketChannel.class);
        // 设定缓冲区大小， 缓存区的单位是字节。
        bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
        // SO_SNDBUF发送缓冲区，SO_RCVBUF接收缓冲区，SO_KEEPALIVE开启心跳监测（保证连接有效）
        bootstrap.option(ChannelOption.SO_SNDBUF, 16 * 1024)
                .option(ChannelOption.SO_RCVBUF, 16 * 1024)
                .option(ChannelOption.SO_KEEPALIVE, true);
    }

    public static ChannelFuture doRequest(String host, int port, Bootstrap bootstrap, ChannelHandlerAdapter channelHandler) throws InterruptedException {
        ChannelFuture future = bootstrap.connect(host, port).sync();
        return future;
    }

    public ChannelFuture doAccept(int port, ServerBootstrap bootstrap, ChannelHandlerAdapter channelHandler) throws InterruptedException{
        ChannelFuture future = bootstrap.bind(port).sync();
        return future;
    }

    public static void release(EventLoopGroup... groups){
        for (EventLoopGroup group : groups){
            group.shutdownGracefully();
        }
    }



}
