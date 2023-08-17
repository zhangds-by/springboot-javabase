package com.zhangds.netty.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomTextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private Logger log = LoggerFactory.getLogger(CustomTextWebSocketFrameHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器回复：" + msg.text()));
    }

    /**
     * 客户端连接触发
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info(String.format("客户端连接触发，唯一值 %d，不同值 %d", ctx.channel().id().asLongText(), ctx.channel().id().asShortText()));
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error(cause.getMessage());
        ctx.close();
    }
}
