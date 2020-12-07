package com.zhangds.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * 初始化添加多个处理器
 */
public class CustomServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        socketChannel.pipeline()
                .addLast("myHttpServerCodec", new HttpServerCodec())
                .addLast("customHttpServerHandler", new CustomHttpServerHandler());
    }
}
