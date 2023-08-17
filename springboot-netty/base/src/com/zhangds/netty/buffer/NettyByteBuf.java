package com.zhangds.netty.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class NettyByteBuf {

    /**
     * 不需要使用 flip 反转
     * 底层维护 readerIndex 、writerIndex 和 capacity
     * 三个区域 ： 0 - readerIndex   已读区域
     *            readerIndex - writerIndex  可读区域
     *            writerIndex - capacity    可写区域
     * @param args
     */
    public static void main(String[] args) {

        ByteBuf buffer = Unpooled.buffer(10);

        for (int i=0; i<buffer.capacity(); i++){
            buffer.writeByte(i);
        }

        for (int i=0; i<buffer.capacity(); i++){
            System.out.println(buffer.readByte());
        }

    }
}
