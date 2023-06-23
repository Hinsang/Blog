package com.Blog.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터
	private String content;
	
	private int count; // 조회수
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userId") // User 객체를 userId라는 컬럼으로 만든다.
	private User user;
	
	// 댓글 정보도 가져와야 하므로 fetch = FetchType.LAZY를 FetchType.EAGER로 변경한다.
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
