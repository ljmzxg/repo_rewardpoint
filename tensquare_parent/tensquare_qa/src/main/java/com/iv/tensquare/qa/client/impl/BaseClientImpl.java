package com.iv.tensquare.qa.client.impl;

import org.springframework.stereotype.Component;

import com.iv.tensquare.qa.client.BaseClient;

import entity.Result;
import entity.StatusCode;

@Component
public class BaseClientImpl implements BaseClient {

	@Override
	public Result findById(String labelId) {
		return new Result(false, StatusCode.ERROR, "Hystrix 熔断器触发");
	}
	
}
