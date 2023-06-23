package com.Blog.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Blog.blog.model.User;

@Repository // 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> {
	
	// SELECT * FROM user WHERE username = 1?; (네이밍 쿼리)
	Optional<User> findByUsername(String username);
	
}

/*
// JPA Naming 쿼리
// SELECT * FROM user WHERE username = ? AND password = ?;
User findByUsernameAndPassword(String unsername, String password);

// Query 방식
// @Query(value="SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
// User login(String username, String password);
*/
