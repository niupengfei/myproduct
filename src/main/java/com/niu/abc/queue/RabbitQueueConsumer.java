package com.niu.abc.queue;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

/**
 * rabbit 消费者 服务
 * 
 * 
 */
public class RabbitQueueConsumer implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(RabbitQueueConsumer.class);


    @Override
    public void onMessage(Message message) {
        String json = null;
        try {
            json = new String(message.getBody(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.warn("收到的队列消息转换成字符串的时候失败:{}", new String(message.getBody()));
            RabbitQueueManager.putMessage(json);
        }
        logger.info("收到队列消息:{}", json);
    }
}
