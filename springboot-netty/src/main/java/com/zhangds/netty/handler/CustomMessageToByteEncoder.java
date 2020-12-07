package com.zhangds.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 出站handler 对数据编码
 * @author: zhangds
 * @date: 2020/12/4 15:28
 */
public class CustomMessageToByteEncoder extends MessageToByteEncoder<Long> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf byteBuf) throws Exception {
        byteBuf.writeLong(msg);
    }
}
