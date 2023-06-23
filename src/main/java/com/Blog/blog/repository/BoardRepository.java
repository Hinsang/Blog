package com.Blog.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Blog.blog.model.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
	
}
