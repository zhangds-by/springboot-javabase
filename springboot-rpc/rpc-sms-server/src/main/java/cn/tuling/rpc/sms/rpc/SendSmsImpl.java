package cn.tuling.rpc.sms.rpc;


import cn.tuling.rpc.sms.remote.SendSms;
import cn.tuling.rpc.sms.remote.vo.UserInfo;

/**
 *@author Mark老师
 *
 *类说明：短信息发送服务的实现
 */
public class SendSmsImpl implements SendSms {

    @Override
    public boolean sendMail(UserInfo user) {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("已发送短信息给："+user.getName()+"到【"+user.getPhone()+"】");
        return true;
    }
}
