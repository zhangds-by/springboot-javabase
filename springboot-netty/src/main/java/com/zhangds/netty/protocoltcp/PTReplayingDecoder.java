package com.zhangds.netty.protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class PTReplayingDecoder extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        // 将二进制字节码封装为MessageProtocol数据包(对象)
        int length = in.readInt();
        byte[] content = new byte[length];
        in.readBytes(content);

        MessageProtocol mp = new MessageProtocol();
        mp.setLen(length);
        mp.setContent(content);
        out.add(mp);
    }
}
