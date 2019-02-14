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
	
	/**
	 * 直接模式
	 */
	@Test
	public void testSendDirect() {
		rabbitTemplate.convertAndSend("tensquare_rabbitmq", "猪年快乐01");
	}
	
	/**
	 * 分列模式
	 */
	@Test
	public void testSendFanoutExchange() {
		rabbitTemplate.convertAndSend("fanout_exchange", "", "fanout exchange 小猪佩奇过大年");
	}
	
	/**
	 * 主题模式
	 */
	@Test
	public void testSendTopic() {
		rabbitTemplate.convertAndSend("tensquare_topic", "good.abc.cfg", "主题模式测试：流浪地球");
	}
	
	@Test
	public void testSendTopic02() {
		rabbitTemplate.convertAndSend("tensquare_topic", "good.abc.log", "主题模式测试：流浪地球");
	}
	
	@Test
	public void testSendTopic03() {
		rabbitTemplate.convertAndSend("tensquare_topic", "good.log", "主题模式测试：流浪地球");
	}
	
	
}
