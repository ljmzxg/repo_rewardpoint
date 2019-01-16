package com.iv.tensquare.base.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import entity.Result;
import entity.StatusCode;

@RestControllerAdvice
public class BaseExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public Result exception(Exception e) {
		e.printStackTrace();
		return new Result(false, StatusCode.ERROR, e.getMessage());
	}
}
