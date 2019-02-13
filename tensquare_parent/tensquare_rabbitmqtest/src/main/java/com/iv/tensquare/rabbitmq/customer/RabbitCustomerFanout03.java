package com.iv.tensquare.rabbitmq.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "rabbitmq_fanout03")
public class RabbitCustomerFanout03 {

	@RabbitHandler
	public void showMessage(String message) {
		System.out.println("rabbitmq_fanout03 从rabbitmq 接收到的消息：" + message);
		
	}
}
