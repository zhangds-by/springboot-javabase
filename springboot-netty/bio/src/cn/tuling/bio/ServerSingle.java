package cn.tuling.bio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 单线程服务端
 * @author Mark老师
 *
 *类说明：单线程版Bio通信的服务端，无法正常工作
 *
 * 1、启动ServerSingle
 * 2、启动Client
 *
 * 主线程启动服务器，连接建立后，在读取socket发送过来的信息前，线程一直等待，处于阻塞的状态
 */
public class ServerSingle {

    public static void main(String[] args) throws IOException {
        //服务端启动必备
        ServerSocket serverSocket = new ServerSocket();
        //表示服务端在哪个端口上监听
        serverSocket.bind(new InetSocketAddress(8888));
        System.out.println("Start Server ....");
        int connectCount = 0;
        try {
            while(true){
                Socket socket = serverSocket.accept();

                System.out.println("accept client socket ....total =" + ( ++connectCount));
                //实例化与客户端通信的输入输出流
                try(ObjectInputStream inputStream =
                            new ObjectInputStream(socket.getInputStream());
                    ObjectOutputStream outputStream =
                            new ObjectOutputStream(socket.getOutputStream())){

                    //接收客户端的输出，也就是服务器的输入
                    String userName = inputStream.readUTF();
                    System.out.println("Accept client message:"+userName);

                    //服务器的输出，也就是客户端的输入
                    outputStream.writeUTF("Hello,"+userName);
                    outputStream.flush();
                }catch(Exception e){
                    e.printStackTrace();
                }finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } finally {
            serverSocket.close();
        }

    }
}
