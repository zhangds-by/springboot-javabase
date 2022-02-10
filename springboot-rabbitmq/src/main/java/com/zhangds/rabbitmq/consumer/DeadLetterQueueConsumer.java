package com.zhangds.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import com.zhangds.rabbitmq.config.DelayedQueueConfig;
import com.zhangds.rabbitmq.config.TtlConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Slf4j
@Component
public class DeadLetterQueueConsumer {

    @RabbitListener(queues = TtlConfig.QUEUE_D)
    public void receiveD(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody(), "utf-8");
        log.info("当前时间：{}，收到死信ttl={}队列信息：{}", new Date().toString(), message.getMessageProperties().getExpiration(), msg);
    }

    @RabbitListener(queues = DelayedQueueConfig.DELAYED_QUEUE_NAME)
    public void receiveDelayedQueue(Message message){
        String msg = new String(message.getBody());
        log.info("当前时间：{}，收到延时队列的消息：{}", new Date().toString(), msg);
    }


}
