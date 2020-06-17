package com.zhangds.netty.reactor;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * 单Reactor单线程模型：所有I/O操作（多路复用、事件分发和处理）都是在一个Reactor线程完成
 * @author zhangds
 * @date 2020/6/17 11:22
 */
public class SingleHandler implements Runnable {
    public static final int READING = 0, WRITING = 1;
    int state;
    final SocketChannel socket;
    final SelectionKey selectionKey;
    private Selector selector;

    public SingleHandler(SocketChannel socket, Selector sl) throws Exception {
        this.state = READING;
        this.socket = socket;
        selectionKey = socket.register(selector, SelectionKey.OP_READ);
        selectionKey.attach(this);
        socket.configureBlocking(false);
    }

    @Override
    public void run() {
        if (state == READING) {
            read();
        } else if (state == WRITING) {
            write();
        }
    }

    private void read() {
        process();
        //下一步处理写事件
        selectionKey.interestOps(SelectionKey.OP_WRITE);
        this.state = WRITING;
    }

    private void write() {
        process();
        //下一步处理读事件
        selectionKey.interestOps(SelectionKey.OP_READ);
        this.state = READING;
    }

    /**
     * task 业务处理
     */
    public void process() {
        //do something
    }
}
