package com.zhangds.netty.mutiple;

import com.zhangds.webservice.netty.common.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Create by zhangds
 * 2020-05-14 15:59
 **/
public class Client4Mutiple {

    // 处理请求和处理服务端响应的线程组
    public static EventLoopGroup group = null;
    // 客户端启动相关配置信息
    public static Bootstrap bootstrap = null;

    public Client4Mutiple(){
        init();
    }

    /**
     * 初始化客户端请求和处理线程组，服务器启动相关配置信息
     * @Author zhangds
     * @Date 2020/5/15 12:01
     * @Return
     */
    public static void init(){
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        // 绑定线程组
        bootstrap.group(group);
        // 设定通讯模式为NIO
        bootstrap.channel(NioSocketChannel.class);
    }

    @SuppressWarnings("all")
    public ChannelFuture doRequest(String host, int port, final ChannelHandler... handlers) throws InterruptedException{
        /*
         * 客户端的Bootstrap没有childHandler方法。只有handler方法。
         * 方法含义等同ServerBootstrap中的childHandler
         * 在客户端必须绑定处理器，也就是必须调用handler方法。
         * 服务器必须绑定处理器，必须调用childHandler方法。
         */
        this.bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel channel) throws Exception {
                channel.pipeline().addLast(handlers);
            }
        });
        // 建立连接。
        ChannelFuture future = this.bootstrap.connect(host, port).sync();
        return future;
    }

    public static void main(String[] args) {
        Client4Mutiple client = null;
        ChannelFuture future = null;
        try{
            client = new Client4Mutiple();
            future = client.doRequest("localhost", 9999, new ClientHandler());

            Scanner s = null;
            while(true){
                s = new Scanner(System.in);
                System.out.print("enter message send to server (enter 'exit' for close client) > ");
                String line = s.nextLine();
                if("exit".equals(line)){
                    // addListener - 增加监听，当某条件满足的时候，触发监听器。
                    // ChannelFutureListener.CLOSE - 关闭监听器，代表ChannelFuture执行返回后，关闭连接。
                    future.channel().writeAndFlush(Unpooled.copiedBuffer(line.getBytes("UTF-8")))
                            .addListener(ChannelFutureListener.CLOSE);
                    break;
                }
                future.channel().writeAndFlush(Unpooled.copiedBuffer(line.getBytes("UTF-8")));
                TimeUnit.SECONDS.sleep(1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(null != future){
                try {
                    future.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(null != client){
                client.release();
            }
        }
    }

    public void release(){
        group.shutdownGracefully();
    }


}
