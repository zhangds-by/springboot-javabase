package com.zhangds.netty.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * 泛型的类型是传递消息的类型
 * @author: zhangds
 * @date: 2020/11/17 11:09
 */
public class TcpCustomServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] buffer = new byte[msg.readableBytes()];
        msg.readBytes(buffer);

        String message = new String(buffer, Charset.forName("utf-8"));

        ByteBuf response = Unpooled.copiedBuffer("server 收到 : " + message, CharsetUtil.UTF_8);
        ctx.writeAndFlush(response);
    }
}
