package com.Blog.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.Blog.blog.dto.ResponseDto;

@ControllerAdvice // 어디에서든 예외가 발생할 때 실행되는 컨트롤러 (에러 페이지)
@RestController
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = Exception.class) // 예외를 가져오는 어노테이션
	public ResponseDto<String> handlerArgumentException(Exception e) {
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
	}
	
}
