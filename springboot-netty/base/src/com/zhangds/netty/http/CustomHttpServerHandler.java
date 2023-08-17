package com.zhangds.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class CustomHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    private static Logger log = LoggerFactory.getLogger(CustomHttpServerHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        if (msg instanceof HttpRequest){
            log.info("ctx type is " + ctx.getClass());

            log.info("业务处理通道 pipeline = " + ctx.pipeline().hashCode());

            log.info("业务处理器 CustomHttpServerHandler " + this.hashCode());

            HttpRequest httpRequest = (HttpRequest) msg;
            URI uri = new URI(httpRequest.uri());

            //对特定的uri进行拦截

            ByteBuf buf = Unpooled.copiedBuffer("hello, 我是 server", CharsetUtil.UTF_8);
            FullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buf);

            httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            httpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, buf.readableBytes());

            //将构建好 response返回
            ctx.writeAndFlush(httpResponse);

        }
    }
}
