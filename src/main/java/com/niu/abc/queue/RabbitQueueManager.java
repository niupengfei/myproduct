package com.niu.abc.queue;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;

/**
 * rabbit 消息服务
 * 
 * 
 */
public class RabbitQueueManager {

	/**
	 * 消息队列 基础资源同步服务
	 */
	private AmqpTemplate rabbitQueueTemplate;
	private static AmqpTemplate queueTemplate;

	private static final Logger logger = LoggerFactory.getLogger(RabbitQueueManager.class);

	public static boolean putMessage(String message) {
		try {
			logger.info("发送消息到队列:" + message);
			queueTemplate.convertAndSend(message);
			String s;
		} catch (Exception e) {
			logger.warn("发送消息到队列发生异常:", e);
			return false;
		}
		return true;
	}

	public AmqpTemplate getRabbitQueueTemplate() {
		return rabbitQueueTemplate;
	}

	public void setRabbitQueueTemplate(AmqpTemplate rabbitQueueTemplate) {
		this.rabbitQueueTemplate = rabbitQueueTemplate;
		RabbitQueueManager.queueTemplate = rabbitQueueTemplate;
	}

}
