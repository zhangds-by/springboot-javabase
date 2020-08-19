package com.zhangds.webservice.simpletalk;

import org.springframework.stereotype.Controller;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Controller
@ServerEndpoint("/ws")
public class TalkServer {

    private static CopyOnWriteArraySet<Session> sessions = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session){

        System.out.println("Socket open ...");

        sessions.add(session);
        this.broadcast("用户" + session.getId() + "加入聊天室");

        System.out.println("连接数" + sessions.size());
    }

    @OnClose
    public void onClose(Session session){
        sessions.remove(session);
        this.broadcast("用户" + session.getId() + "退出聊天室");
        System.out.println("连接数" + sessions.size());
    }

    @OnError
    public void onError(Session session, Throwable error){
        sessions.remove(session);
        error.printStackTrace();
    }

    private void broadcast(String message){
        sessions.forEach(session -> sendMessage(session, message));
    }

    private void sendMessage(Session session, String message){
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
