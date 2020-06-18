package com.zhangds.netty.common;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

/**
 * Create by zhangds
 * 2020-05-15 12:49
 **/
public class ClientHandler extends ChannelHandlerAdapter {

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            /*ByteBuf readBuf = (ByteBuf) msg;
            byte[] datas = new byte[readBuf.readableBytes()];
            readBuf.readBytes(datas);
            System.out.println("from server " + new String(datas, "UTF-8"));*/
            String message = msg.toString();
            System.out.println("from server " + message);
        }finally {
            // 用于释放缓存。避免内存溢出
            ReferenceCountUtil.release(msg);
        }
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("client exceptionCaught method run...");
        ctx.close();
    }

    /**
     * 断开连接时执行
     * @param ctx
     * @throws Exception
     */
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive method run...");
    }

    /**
     * 连接通道建立成功时执行
     * @param ctx
     * @throws Exception
     */
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive method run...");
    }

    /**
     * 每次读取完成时执行
     * @param ctx
     * @throws Exception
     */
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete method run...");
    }
}
