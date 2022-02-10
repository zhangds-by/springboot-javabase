package com.zhangds.rabbitmq.controller;


import com.zhangds.rabbitmq.config.DelayedQueueConfig;
import com.zhangds.rabbitmq.config.TtlConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RequestMapping("ttl")
@RestController
public class SendMsgController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMsg/{message}")
    public void sendMsg(@PathVariable String message){
        log.info("当前时间：{}，发送一条消息给队列：{}", new Date().toString(), message);
        rabbitTemplate.convertAndSend(TtlConfig.X_EXCHANGE, "XA", "消息来自ttl为10S的队列:" + message);
        rabbitTemplate.convertAndSend(TtlConfig.X_EXCHANGE, "XB", "消息来自ttl为20S的队列:" + message);
    }

    @GetMapping("/sendExpirationMsg/{message}/{ttlTime}")
    public void sendExpirationMsg(@PathVariable String message, @PathVariable String ttlTime){
        log.info("当前时间：{}，发送一条时长为{}ttl的消息给队列：{}", new Date().toString(), ttlTime, message);
        rabbitTemplate.convertAndSend(TtlConfig.X_EXCHANGE, "XC", message, correlationData->{
            correlationData.getMessageProperties().setExpiration(ttlTime);
            return correlationData;
        });
    }

    @GetMapping("/sendDelayMsg/{message}/{delayTime}")
    public void sendMsg(@PathVariable String message, @PathVariable Integer delayTime) {
        rabbitTemplate.convertAndSend(DelayedQueueConfig.DELAYED_EXCHANGE_NAME, DelayedQueueConfig.DELAYED_ROUTING_KEY, message,
                correlationData -> {
                    correlationData.getMessageProperties().setDelay(delayTime);
                    return correlationData;
                });
        log.info("当前时间：{}，发送一条延迟{}毫秒的信息给延迟队列：{}", new Date(), delayTime, message);
    }

}
