package com.Blog.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
// @DynamicInsert	// Role 처럼 null값이 들어가는 부분을 제외하고 insert 시켜준다.
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 30, unique = true)
	private String username;
	
	@Column(nullable = false, length = 100) // 해쉬 암호화를 위해 패스워드를 길게 설정
	private String password;
	
	@Column(nullable = false, length = 50, unique = true)
	private String email;

	// @ColumnDefault("user")
	// DB는 RoleType이라는 게 없다.
	@Enumerated(EnumType.STRING) // 그래서 ENUM의 자료형을 명시한다. 
	private RoleType role; // Enum을 쓰는 것이 좋다. (null 타입 대신 넣을 자료형, 특정 타입의 자료만 한정, 잘못된 자료를 넣는 것을 방지한다.) // ADMIN, USER
	
	@CreationTimestamp // 시간을 자동 입력
	private Timestamp createDate;
	
}
