package com.iv.tensquare.sms.listener;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "sms")
public class SmsListener {
	
	@RabbitHandler
	public void executeSms(Map<String, String> map) {
		System.out.println("手机号码：" + map.get("mobile"));
		System.out.println("验证码：" + map.get("checkcode"));
	}

}
