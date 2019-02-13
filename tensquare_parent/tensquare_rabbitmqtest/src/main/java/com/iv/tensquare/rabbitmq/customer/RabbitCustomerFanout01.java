package com.iv.tensquare.rabbitmq.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "rabbitmq_fanout01")
public class RabbitCustomerFanout01 {

	@RabbitHandler
	public void showMessage(String message) {
		System.out.println("rabbitmq_fanout01 从rabbitmq 接收到的消息：" + message);
		
	}
}
