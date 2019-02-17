package com.iv.tensquare.sms.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * 短信工具类
 * @author Zhang Xiang Guo
 *
 */
@Component
public class SmsUtil {

	//产品名称：云通信短信API 产品，
	static final String product = "Dysmsapi";
	
	//产品域名
	static final String domain = "dysmsapi.aliyuncs.com";
	
	static final String regionId = "cn-hangzhou";
	
	static final String endpointName = "cn-hangzhou";
	
	@Autowired
	private Environment env;
	
	/**
	 * 发送短信服务
	 * @param mobile 手机号
	 * @param template_code 模板号
	 * @param sign_name 签名
	 * @param param	参数
	 * @return
	 * @throws ClientException 
	 */
	public SendSmsResponse sendSms(String mobile, String template_code, String sign_name, String param) throws ClientException {
		System.out.println("sendSms start");
		System.out.println("mobile:" + mobile + ",template_code:" + template_code + ",sign_name:" + sign_name + ",param:" + param);
		String accessKeyId = env.getProperty("aliyun.sms.accessKeyId");
		String accessKeySecret = env.getProperty("aliyun.sms.accessKeySecret");
		//可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		//初始化accessClient， 暂不支持region 化
		IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
		DefaultProfile.addEndpoint(endpointName, regionId, product, domain);
//		DefaultProfile.addEndpoint(regionId, product, endpointName);
		IAcsClient acsClient = new DefaultAcsClient(profile);
		//组装请求对象
		SendSmsRequest request = new SendSmsRequest();
		//必填：待发送手机号码
		request.setPhoneNumbers(mobile);
		//必填：短信签名 -- 可在短信控制台找到
		request.setSignName(sign_name);
		//必填：短信模板--可在短信控制台中找到
		request.setTemplateCode(template_code);
		//可选：模板中的变量替换JSON 串，如模板内容为："亲爱的${name}，您的验证码为：${code}时，此处的值为"
		request.setTemplateParam(param);
		//选填：上行短信扩展码（无特殊需求用户请忽略此字段）
//		request.setSmsUpExtendCode("90997");
		//可选：outId 为提供给业务方扩展字段，最终在短信回执消息中将此值带回给调用者
		request.setOutId("yourOutId");
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		
		if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
			//请求成功
			return sendSmsResponse;
		}
		System.out.println("sendSms end");
		return null;
	}
	
	public QuerySendDetailsResponse querySendDetails(String mobile, String bizId) throws ServerException, ClientException {
		String accessKeyId = env.getProperty("aliyun.sms.accessKeyId");
		String accessKeySecret = env.getProperty("aliyun.sms.accessKeySecret");
		//可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		//初始化accessClient， 暂不支持region 化
		IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
		DefaultProfile.addEndpoint(endpointName, regionId, product, domain);
//		DefaultProfile.addEndpoint(regionId, product, endpointName);
		IAcsClient acsClient = new DefaultAcsClient(profile);
		//组装请求对象
		QuerySendDetailsRequest request = new QuerySendDetailsRequest();
		//必填--号码
		request.setPhoneNumber(mobile);
		//可选：流水号
		request.setBizId(bizId);
		//必填：发送日期 支持30天内记录查询，格式：yyyyMMdd
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		request.setSendDate(sdf.format(new Date()));
		//必填：页大小
		request.setPageSize(10L);
		//必填--当前页码，从1开始计数
		request.setCurrentPage(1L);
		
		//hint 此处可能会抛出异常，注意catch
		QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);
		
		//获取返回结果
		if(querySendDetailsResponse.getCode() != null && querySendDetailsResponse.getCode().equals("OK")) {
			return querySendDetailsResponse;
		}
		
		return null;
	}
	
	
	
	
}
