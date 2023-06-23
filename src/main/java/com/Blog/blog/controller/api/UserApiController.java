package com.Blog.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Blog.blog.dto.ResponseDto;
import com.Blog.blog.model.RoleType;
import com.Blog.blog.model.User;
import com.Blog.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;
	
	/*
	 * @Autowired private HttpSession session;
	 */
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController : save 호출됨");
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	/*
	 * // 정통 로그인 방식
	 * 
	 * @PostMapping("/api/user/login") // HttpSession을 @Autowired로 빼는 것도 가능하다.
	 * public ResponseDto<Integer> login(@RequestBody User user, HttpSession
	 * session) { System.out.println("UserApiController : login호출됨"); User principal
	 * = userService.로그인(user); // principal (접근주체)
	 * 
	 * if(principal != null) { session.setAttribute("principal", principal); }
	 * return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); }
	 */
	
}
