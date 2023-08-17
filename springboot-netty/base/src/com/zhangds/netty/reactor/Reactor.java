package com.zhangds.netty.reactor;


import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Reactor线程，负责多路分离套接字
 * @author zhangds
 * @date 2020/6/17 11:16
 */
public class Reactor implements Runnable {

    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    public Reactor(Selector selector, ServerSocketChannel serverSocketChannel) {
        this.selector = selector;
        this.serverSocketChannel = serverSocketChannel;
    }

    private Reactor() throws Exception {

        SelectionKey sk = serverSocketChannel.register(this.selector,
                        SelectionKey.OP_ACCEPT);
        // attach Acceptor 处理新连接
        sk.attach(new Acceptor());
    }


    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                selector.select();
                Set selected = selector.selectedKeys();
                Iterator it = selected.iterator();
                while (it.hasNext()) {
                    it.remove();
                    //分发事件处理
                    dispatch((SelectionKey) (it.next()));
                }
            }
        } catch (IOException ex) {
            //do something
        }
    }

    void dispatch(SelectionKey selectionKey) {
        // 若是连接事件获取是acceptor
        // 若是IO读写事件获取是handler
        Runnable runnable = (Runnable) (selectionKey.attachment());
        if (runnable != null) {
            runnable.run();
        }
    }

}
