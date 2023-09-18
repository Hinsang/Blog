package com.Blog.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Blog.blog.config.auth.PrincipalDetail;
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
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController : save 호출됨");
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user) { // @RequestBody로 json 데이터 받기
		userService.회원수정(user);
		// 여기서는 트랜잭션이 종료되기 때문에 DB에 값은 변경이 됐지만 세션 값은 변경이 되지 않은 상태이므로 세션값을 변경해준다.
		
		// 세션 등록
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		// 시큐리티 컨텍스트 홀더 안의 시큐리티 컨텍스트 안에 있는 Authentication 세션을 변경한다.
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return new ResponseDto<Integer> (HttpStatus.OK.value(), 1);
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
