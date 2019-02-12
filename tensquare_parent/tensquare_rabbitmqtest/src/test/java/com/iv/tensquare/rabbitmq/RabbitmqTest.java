package com.iv.tensquare.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqTest {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Test
	public void testSend() {
		rabbitTemplate.convertAndSend("tensquare_rabbitmq", "猪年快乐03");
	}
}
