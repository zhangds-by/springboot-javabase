package com.zhangds.netty.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyServer {

    private static Logger log = LoggerFactory.getLogger(NettyServer.class);

    public static void main(String[] args) {

        // 初始化线程组
        // bossGroup 处理连接请求
        // workGroup 客户端业务处理
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup(); // 默认为cpu的核数*2

        try {
            //创建服务器启动对象
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            //使用链式编程配置参数
            serverBootstrap.group(bossGroup, workGroup) //设置线程组
                    .channel(NioServerSocketChannel.class)  //服务器通道实现
                    .option(ChannelOption.SO_BACKLOG, 128)  //设置线程队列获取的连接数
                    .childOption(ChannelOption.SO_KEEPALIVE, true)  //设置保持活动连接状态
                    //.handler(null) //handler对应bossGroup，childHandler 对应 workerGroup
                    .childHandler(new ChannelInitializer<SocketChannel>() { //通道初始化对象，给workerGroup 的 EventLoop 对应的管道设置处理器
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            log.info("客户SocketChannel" + hashCode());
                            //pipeline设置处理器，集合管理SocketChannel，在handler推送消息时
                            // 将业务加入到各个channel 对应的 NIOEventLoop 的 taskQueue 或者 scheduleTaskQueue
                            socketChannel.pipeline().addLast(new NettyServerHandler());
                        }
                    });

            log.info("服务器已就绪......");

            ChannelFuture channelFuture = serverBootstrap.bind(6666).sync();

            //给 channelFuture 添加监听器
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture cf) throws Exception {
                    if (cf.isSuccess()){
                        log.info("6666端口监听成功");
                    }else {
                        log.error("6666端口监听失败");
                    }
                }
            });
            //关闭通道监听
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
