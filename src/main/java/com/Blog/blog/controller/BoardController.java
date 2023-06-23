package com.Blog.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Blog.blog.config.auth.PrincipalDetail;
import com.Blog.blog.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	// @AuthenticationPrincipal PrincipalDetail principal // 시큐리티 세션 확인
	@GetMapping({"", "/"})
	public String index(Model model, @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("boards", boardService.글목록(pageable));
		// prefix, suffix /WEB-INF/views/index.jsp
		// System.out.println("로그인 사용자 아이디 : " + principal.getUsername());
		return "index"; // @Controller를 리턴할 때 viewResolver 작동
	}
	
	// USER 권한이 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
	
}
