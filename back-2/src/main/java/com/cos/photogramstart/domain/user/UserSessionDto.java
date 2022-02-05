package com.cos.photogramstart.domain.user;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class UserSessionDto implements Serializable {
    private String userid;
    private String userpw;
    private String username;
    private String useremail;
    private String modifiedDate;
	
    /* Entity -> Dto */
    public UserSessionDto(User user) {
        this.userid = user.getUserid();
        this.userpw = user.getUserpw();
        this.username = user.getUsername();
        this.useremail = user.getUseremail();
        this.modifiedDate = user.getModifiedDate();
    }
}