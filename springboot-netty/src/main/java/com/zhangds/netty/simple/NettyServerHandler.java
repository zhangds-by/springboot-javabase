package com.zhangds.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 继承Netty规定好的某个HandlerAdapter(规范)
 * @author: zhangds
 * @date: 2020/11/16 9:28
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    private Logger log = LoggerFactory.getLogger(NettyServerHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //用户程序自定义普通任务
        //异步执行，提交该channel 对应的NIOEventLoop 的 taskQueue
        /*ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5*1000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello，客户端", CharsetUtil.UTF_8));
                    log.info("Channel Handler" + ctx.channel().hashCode());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });*/

        //用户自定义定时任务
        /*ctx.channel().eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5*1000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello，客户端", CharsetUtil.UTF_8));
                    log.info("Channel Handler" + ctx.channel().hashCode());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 5, TimeUnit.SECONDS);*/

        log.info("服务器读取任务线程" + Thread.currentThread().getName());
        //调试查看channel 和 pipeline的关系
        ByteBuf buffer = (ByteBuf) msg;
        log.info(String.format("用户%s : %s", ctx.channel().remoteAddress(), buffer.toString(CharsetUtil.UTF_8)));
    }

    /**
     * 数据读取完毕
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将数据写入缓存并刷新
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端", CharsetUtil.UTF_8));
    }

    /**
     * 关闭通道
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
