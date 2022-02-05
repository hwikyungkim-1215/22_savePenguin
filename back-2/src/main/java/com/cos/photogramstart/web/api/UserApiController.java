package com.cos.photogramstart.web.api;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.UserService;

import lombok.RequiredArgsConstructor;

//@EnableJpaRespositories(basePackages="sam.springboot.dao") 
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user/{userid}") //put?
public class UserApiController {

	
	private final UserService userService;

    private final AuthenticationManager authenticationManager;
    

    //@RequestMapping("/api/user/{id}") //put?
    public ResponseEntity<String> 회원수정(@PathVariable String userid, @RequestBody User dto) {
    	System.out.println("회원 수정 시작");
        userService.회원수정(userid, dto);
        
       
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getUserpw()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("회원 수정 성공");
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

	


}
