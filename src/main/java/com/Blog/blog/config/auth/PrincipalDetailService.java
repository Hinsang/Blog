package com.Blog.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Blog.blog.model.User;
import com.Blog.blog.repository.UserRepository;

@Service
public class PrincipalDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	// 스프링이 로그인 요청을 가로챌 때, username, password 변수 2개를 가로채는데
	// password 부분 처리는 알아서 하기 때문에
	// 이 부분에서 username이 DB에 있는지만 확인해준다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Optional 객체를 반환하므로 예외처리를 해준다.
		User principal = userRepository.findByUsername(username)
				.orElseThrow(() -> {
					return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : " + username);
				});
		// UserDetails 타입을 구현한 PrincipalDetail()을 리턴한다.
		return new PrincipalDetail(principal); // 시큐리티의 세션에 유저 정보가 저장이 됨.
	}
	
}
