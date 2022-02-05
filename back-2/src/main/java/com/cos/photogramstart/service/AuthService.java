package com.cos.photogramstart.service;

import javax.validation.Valid;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserDto;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;
// 회원 가입 
@RequiredArgsConstructor
@Service // 1. IoC  2. 트랜잭션 관리
public class AuthService {

	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
    /* 아이디, 이메일 중복 여부 확인 */
    @Transactional
    public void checkUsernameDuplication(SignupDto signupDto) {
        boolean useridDuplicate = userRepository.existsByUserid(signupDto.getUserid());
        if (useridDuplicate) {
        	System.out.println("아이디 중복");
        	//return "400";// 아이디 중복 
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }

    }


    @Transactional
    public void checkUseremailDuplication(SignupDto signupDto) {
        boolean useremailDuplicate = userRepository.existsByUseremail(signupDto.getUseremail());
        if (useremailDuplicate) {
        	System.out.println("이메일 중복");
        	//return "400";
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }
    }
    
    

	
	@Transactional // Write(Insert, Update, Delete)
	public User 회원가입(User user) throws RuntimeException{
		// 회원가입 진행
		String rawPassword = user.getUserpw();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setUserpw(encPassword);
		//user.setRole("ROLE_USER"); // 관리자 ROLE_ADMIN
		User userEntity = null;
		userEntity = userRepository.save(user);
		
		return userEntity;
	}


}
