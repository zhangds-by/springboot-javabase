package com.zhangds.netty.protocoltcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

public class CustomClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private Logger log = LoggerFactory.getLogger(CustomClientHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        byte[] content = "hello server".getBytes(Charset.forName("utf-8"));
        int length = content.length;

        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLen(length);
        messageProtocol.setContent(content);
        ctx.writeAndFlush(messageProtocol);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        int len = msg.getLen();
        byte[] content = msg.getContent();
        log.info("收到信息 : " + new String(content, CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
