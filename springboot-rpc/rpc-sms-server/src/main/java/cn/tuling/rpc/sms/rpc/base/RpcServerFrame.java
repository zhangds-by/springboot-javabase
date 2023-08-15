package cn.tuling.rpc.sms.rpc.base;

import cn.tuling.rpc.rpc.rpc.reg.service.RegisterServiceWithRegCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *@author Mark老师
 *
 *类说明：rpc框架的服务端部分
 */
@Service
public class RpcServerFrame {

//    @Autowired
//    private RegisterService registerService;
    @Autowired
    private RegisterServiceWithRegCenter registerServiceWithRegCenter;

    //服务的端口号
    private int port;

    /*处理服务请求任务*/
    private static class ServerTask implements Runnable{

        private Socket socket;
        private RegisterServiceWithRegCenter registerServiceWithRegCenter;

        public ServerTask(Socket client,
                          RegisterServiceWithRegCenter registerServiceWithRegCenter) {
            this.socket = client;
            this.registerServiceWithRegCenter = registerServiceWithRegCenter;
        }

        @Override
        public void run() {
            try(
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())){

                /*方法所在类名接口名*/
                String serviceName = inputStream.readUTF();
                /*方法的名字*/
                String methodName = inputStream.readUTF();
                /*方法的入参类型*/
                Class<?>[] paramTypes = (Class<?>[]) inputStream.readObject();
                /*方法的入参的值*/
                Object[] args = (Object[]) inputStream.readObject();

                /*从容器中拿到服务的Class对象*/
                Class serviceClass = registerServiceWithRegCenter.getLocalService(serviceName);
                if(serviceClass == null){
                    throw new ClassNotFoundException(serviceName+ " not found");
                }

                /*通过反射，执行实际的服务*/
                Method method = serviceClass.getMethod(methodName, paramTypes);
                Object result  = method.invoke(serviceClass.newInstance(),args);

                /*将服务的执行结果通知调用者*/
                outputStream.writeObject(result);
                outputStream.flush();

            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 启动服务器，并将服务器服务注册到注册中心
     */
    public void startService(String serviceName, String host, int port, Class impl) throws Throwable{
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(port));
        System.out.println("RPC server on:"+port+":运行");
        registerServiceWithRegCenter.regRemote(serviceName,host,port,impl);
        try{
            while(true){
                new Thread(new ServerTask(serverSocket.accept(), registerServiceWithRegCenter)).start();
            }
        }finally {
            serverSocket.close();
        }
    }

}

