package com.zhangds.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * 入站解码器 handler
 * @author: zhangds
 * @date: 2020/12/4 15:26
 */
public class CustomReplayingDecoder extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        //不需要判断数据是否足够读取，内部会进行处理判断
        list.add(byteBuf.readLong());
    }
}
