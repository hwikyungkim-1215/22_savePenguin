package com.cos.photogramstart.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

	private String userid;
	
	private String userpw;
	
	private String username;
	
	private String useremail;
	
	
	//Entity
	public User toEntity() {
		User user = User.builder()
				.userid(userid)
				.userpw(userpw)
				.username(username)
				.useremail(useremail)
				.build();
		return user;
	}
}
