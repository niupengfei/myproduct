package com.niu.abc.queue;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestMQController {

	@RequestMapping(value = "testmq")
	public void mq(String message) {
		RabbitQueueManager.putMessage(message);
	}
}
