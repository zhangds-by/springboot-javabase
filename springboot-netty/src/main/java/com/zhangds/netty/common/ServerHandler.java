package com.zhangds.netty.common;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Create by zhangds
 * 2020-05-14 13:01
 **/
@Sharable
public class ServerHandler extends ChannelHandlerAdapter {
    /**
     *用于服务端读取数据请求的逻辑
     * @param ctx 客户端连接的所有资源 如对应的Channel
     * @param msg 读取到的数据。 默认类型是ByteBuf，是Netty自定义的。是对ByteBuffer的封装。 不需要考虑复位问题。
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        /*// 获取读取的数据， 是一个缓冲。
        ByteBuf readBuffer = (ByteBuf) msg;
        // 创建一个字节数组，用于保存缓存中的数据。
        byte[] datas = new byte[readBuffer.readableBytes()];
        // 将缓存中的数据读取到字节数组中。
        readBuffer.readBytes(datas);
        String message = new String(datas, "UTF-8");
        System.out.println("from client : " + message);
        if("exit".equals(message)){
            ctx.close();
            return;
        }
        String line = "server receive message response to client!";
        // 写操作自动释放缓存，避免内存溢出问题。
        ctx.writeAndFlush(Unpooled.copiedBuffer(line.getBytes("UTF-8")));
        // 注意，如果调用的是write方法。不会刷新缓存，缓存中的数据不会发送到客户端，必须再次调用flush方法才行。
        // ctx.write(Unpooled.copiedBuffer(line.getBytes("UTF-8")));
        // ctx.flush();*/
        String message = msg.toString();
        System.out.println("from client : " + message.trim());
        String line = "ok!";
        ctx.writeAndFlush(Unpooled.copiedBuffer(line.getBytes("UTF-8")));
    }

    /**
     * 异常处理逻辑， 当客户端异常退出的时候，也会运行。
     * @param ctx 关闭ChannelHandlerContext，也代表当前与客户端连接的资源关闭。
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("server exceptionCaught method run...");
        ctx.close();
    }
}
