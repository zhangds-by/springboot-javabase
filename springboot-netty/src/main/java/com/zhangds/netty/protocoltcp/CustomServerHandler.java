package com.zhangds.netty.protocoltcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class CustomServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private Logger log = LoggerFactory.getLogger(CustomServerHandler.class);

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        int len = msg.getLen();
        byte[] content = msg.getContent();
        log.info("客户端 : " + new String(content, CharsetUtil.UTF_8));

        String responseContent = UUID.randomUUID().toString();
        byte[] resContent = responseContent.getBytes("utf-8");
        int resLen = resContent.length;

        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLen(resLen);
        messageProtocol.setContent(content);
        ctx.writeAndFlush(messageProtocol);
    }

}
