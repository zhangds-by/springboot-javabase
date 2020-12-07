package com.zhangds.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class CustomByteToLongDecoder extends ByteToMessageDecoder {

    /**
     * decode 被调用多次，接收数据 / ByteBuf没有可读字节
     * 将不为null的list传递给下一个 channelinboundhandler 处理
     * @param ctx
     * @param byteBuf
     * @param list
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() >= 8){
            list.add(byteBuf.readLong());
        }
    }

}
