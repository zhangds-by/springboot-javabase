package com.zhangds.webservice.netty.mutiple;

import com.zhangds.webservice.netty.common.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Create by zhangds
 * 2020-05-14 08:46
 **/
public class Server4Mutiple {

    // 监听线程组，监听客户端请求
    public EventLoopGroup acceptorGroup = null;
    // 处理客户端相关操作线程组，负责处理与客户端的数据通讯
    public EventLoopGroup clientGroup = null;
    // 服务启动相关配置信息
    public ServerBootstrap bootstrap = null;

    public Server4Mutiple() {
        this.init();
    }

    /**
     * 初始化监听客户端请求和处理客户端操作线程组，服务启动相关配置信息
     * @Author zhangds
     * @Date 2020/5/15 12:04
     * @Return
     */
    public void init(){ // 通过传参的方法初始化线程组和配置信息，不会成功？ 相当于传值，修改不会影响原来对象
        // 初始化线程组,构建线程组的时候，如果不传递参数，则默认构建的线程组线程数是CPU核心数量。
        acceptorGroup = new NioEventLoopGroup(); //负责监听的线程数
        clientGroup = new NioEventLoopGroup();
        // 初始化服务的配置
        bootstrap = new ServerBootstrap();
        // 绑定线程组
        bootstrap.group(acceptorGroup, clientGroup); //单线程：传入同一个线程组
        // 设定通讯模式为NIO， 同步非阻塞
        bootstrap.channel(NioServerSocketChannel.class);
        // 设定缓冲区大小， 缓存区的单位是字节。
        bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
        // SO_SNDBUF发送缓冲区，SO_RCVBUF接收缓冲区，SO_KEEPALIVE开启心跳监测（保证连接有效）
        bootstrap.option(ChannelOption.SO_SNDBUF, 16 * 1024)
                .option(ChannelOption.SO_RCVBUF, 16 * 1024)
                .option(ChannelOption.SO_KEEPALIVE, true);
    }

    /**
     * 监听处理逻辑
     *
     * @param port             监听端口
     * @param acceptorHandlers 处理客户端请求处理器
     * @return
     */
    public ChannelFuture doAccept(int port, final ChannelHandler... acceptorHandlers) throws InterruptedException {
        /**
         * childHandler是服务的Bootstrap独有的方法。是用于提供处理对象的。
         * 可以一次性增加若干个处理逻辑。是类似责任链模式的处理方式, 顺序处理客户端请求。
         *
         * ChannelInitializer - 用于提供处理器的一个模型对象。
         * initChannel方法用于初始化处理逻辑责任链条。可以保证服务端的Bootstrap只初始化一次处理器，尽量提供处理逻辑的重用。
         */
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel channel) throws Exception {
                channel.pipeline().addLast(acceptorHandlers);
            }
        });
        //bind方法 - 绑定监听端口的。ServerBootstrap可以绑定多个监听端口
        //sync - 开始监听逻辑,返回一个监听成功的结果
        ChannelFuture future = bootstrap.bind(port).sync();
        return future;
    }

    public static void main(String[] args) {
        ChannelFuture future = null;
        Server4Mutiple server = null;
        try {
            server = new Server4Mutiple();
            future = server.doAccept(9999, new ServerHandler());
            System.out.println("server start...");
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (null != future){
                try {
                    future.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (null != server){
                server.release();
            }
        }
    }

    private void release() {
        acceptorGroup.shutdownGracefully();
        clientGroup.shutdownGracefully();
    }
}
