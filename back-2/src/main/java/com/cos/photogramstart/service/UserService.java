package com.cos.photogramstart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	/* 회원수정 (dirty checking) */

	@Transactional
	public String 회원수정(@RequestParam String userid, User user) { //public User 회원수정(int id, User user)
		// 1. 영속화
		// 1. 무조건 찾았다. 걱정마 get() 2. 못찾았어 익섹션 발동시킬께 orElseThrow()
	//	User userEntity = userRepository.findByUserid(userid);
		//	return new CustomValidationApiException("찾을 수 없는 id입니다.");});

	   	try {
	   		User userEntity = userRepository.findByUserid(userid);
			// 2. 영속화된 오브젝트를 수정 - 더티체킹 (업데이트 완료)
			userEntity.setUserid(user.getUserid());
			
			String rawPassword = user.getUserpw();
			String encPassword = bCryptPasswordEncoder.encode(rawPassword); //비번 암호
			 
			userEntity.setUserpw(encPassword);
			userEntity.setUseremail(user.getUseremail());
			userEntity.setUsername(user.getUsername());
			userEntity.setPoint(user.getPoint());
			System.out.println("회원 수정 성공");
			return "200"; //성공 
	    	}
	    	catch(IllegalStateException e){
	    		System.out.println("회원 수정 실패");
	    		return "400"; //중복 발생 
	    		
	    	}

	} 

 
    public String pointUpdate(String userid, int point) {   //포인트 업데이트                 
        User user = userRepository.findByUserid(userid);
        user.setPoint(point);
        userRepository.save(user);
        return "200"; // 성공 
    }

    public int totalPoint(String userid) {
    	User user = userRepository.findByUserid(userid);
    	return user.getPoint();
    }





}










