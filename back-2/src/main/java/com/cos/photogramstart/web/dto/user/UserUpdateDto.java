package com.cos.photogramstart.web.dto.user;

import javax.validation.constraints.NotBlank;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

// 회원수정에서 뭐 바꿀 수 있는지 확인하
@Data
public class UserUpdateDto { //회원 수
	@NotBlank
	private String username; // 필수
	@NotBlank
	private String userpw; // 필수
	private String useremail;
	
	// 조금 위함함. 코드 수정이 필요할 예정
	public User toEntity() {
		return User.builder()
				.username(username) // 이름을 기재 안했으면 문제!! Validation 체크
				.userpw(userpw) // 패스워드를 기재 안했으면 문제!! Validation 체크
				.useremail(useremail)
				.build();
	}
}
