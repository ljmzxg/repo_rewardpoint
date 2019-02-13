package com.iv.tensquare.rabbitmq.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "rabbitmq_fanout02")
public class RabbitCustomerFanout02 {

	@RabbitHandler
	public void showMessage(String message) {
		System.out.println("rabbitmq_fanout02 从rabbitmq 接收到的消息：" + message);
		
	}
}
