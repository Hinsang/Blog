package com.Blog.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // IoC
public class SecurityConfig {

	// AuthenticationManager를 어디에서든 사용할 수 있게 가져온다.
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	    return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean // IOC
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable() // csrf 토큰 비활성화 (테스트시 걸어두는 게 좋음)
			.authorizeRequests()
				.antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**") // antMatchers - 페이지 경로 허용 (인증이 안 됐을 때) 
				// 인증이 안 되었을 때 이 URL을 인증 없이 사용할 수 있도록 허가. 다른 URL이라면 .loginPage로 넘어간다. 
				.permitAll()
				.anyRequest()
				.authenticated()
			.and()
				.formLogin()
				.loginPage("/auth/loginForm") // antMatchers로 들어오는 요청은 이곳으로 넘어온다.
				.loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 해당 주소로 요청이 오는 로그인을 가로채서 대신 로그인 해준다.
				.defaultSuccessUrl("/") // 로그인에 성공했을 때 URL
				// .failureUrl("/fail")
				;
		
		return http.build();
	}

}
