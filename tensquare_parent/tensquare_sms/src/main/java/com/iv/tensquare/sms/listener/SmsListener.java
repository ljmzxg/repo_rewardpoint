package com.iv.tensquare.sms.listener;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.aliyuncs.exceptions.ClientException;
import com.iv.tensquare.sms.utils.SmsUtil;

@Component
@RabbitListener(queues = "sms")
public class SmsListener {
	
	@Value("${aliyun.sms.template_code}")
	private String template_code;
	
	@Value("${aliyun.sms.sign_name}")
	private String sign_name;
	
	@Autowired
	private SmsUtil smsUtil;
	
	@RabbitHandler
	public void executeSms(Map<String, String> map) {
		String mobile = map.get("mobile");
		String checkcode = map.get("checkcode");
		
		System.out.println("手机号码：" + map.get("mobile"));
		System.out.println("验证码：" + map.get("checkcode"));
		
		try {
			smsUtil.sendSms(mobile, template_code, sign_name, "{\"checkcode\":\""+checkcode+"\"}");
		} catch (ClientException e) {
			e.printStackTrace();
		}
		
	}

}
