package com.cos.photogramstart.web.dto.auth;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data // Getter, Setter
public class SignupDto { //회원가입 -> DB에 바로 넣기 
	// https://bamdule.tistory.com/35 (@Valid 어노테이션 종류)
	@Size(min = 2, max = 20)
	@NotBlank // 무조건 받아야 
	private String userid; //아이디 
	@NotBlank
	private String userpw; // 비번 
	@NotBlank
	private String useremail; //이메일 
	@NotBlank
	private String username; //이름 
	
	public User toEntity() {
		return User.builder() 
				.userid(userid) //아이디 
				.userpw(userpw)
				.useremail(useremail)
				.username(username)
				.build(); // 바로 리턴 
	}
}
