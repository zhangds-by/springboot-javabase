package com.zhangds.netty.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

public class NettyByteBufUse {

    /**
     * 不需要使用 flip 反转
     * 底层维护 readerIndex 、writerIndex 和 capacity
     * 三个区域 ： 0 - readerIndex   已读区域
     *            readerIndex - writerIndex  可读区域
     *            writerIndex - capacity    可写区域
     * @param args
     */
    public static void main(String[] args) {

        ByteBuf buf = Unpooled.copiedBuffer("hello netty", CharsetUtil.UTF_8);

        if (buf.hasArray()){

            System.out.println(new String(buf.array(), Charset.forName("utf-8")));

            System.out.printf("capacity = %d \n readerIndex = %d \n writerIndex = %d \n arrayOffset = %d \n",
                    buf.capacity(), buf.readerIndex(), buf.writerIndex(), buf.arrayOffset());

            System.out.println((char) buf.getByte(0));

            System.out.println("可读长度 = " + buf.readableBytes());
            System.out.println("可写长度 = " + buf.writableBytes());

            System.out.println(buf.getCharSequence(0, 4, Charset.forName("utf-8")));
        }

    }
}
