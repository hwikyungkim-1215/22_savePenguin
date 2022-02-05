package com.cos.photogramstart.web;

import java.util.HashMap;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
@RestController // json으로 반환 
public class AuthController {

	private final AuthService authService;
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(AuthController.class);
	
	
	@RequestMapping("/auth/singnin2")
	public void signinForm(HttpServletRequest request){//public void
		System.out.println("id:" + request.getParameter("userid"));
		System.out.println("pw:" +request.getParameter("userpw"));
	}
	
	
	/* 로그인 */
	@RequestMapping("/auth/signin")
    public String login(HttpServletRequest request, @RequestParam(value = "error", required = false)String error,
                        @RequestParam(value = "exception", required = false)String exception,
                        Model model) {
    	System.out.println("로그인 성공");
		System.out.println("id:" + request.getParameter("userid"));
		System.out.println("pw:" +request.getParameter("userpw"));
		
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "200"; //로그인 성공 
    }


	/* 회원가입 */
    @RequestMapping("/auth/signup")
	public String signup(@Valid SignupDto signupDto, BindingResult bindingResult, Errors errors, Model model) { // key=value (x-www-form-urlencoded)

	
        /* 중복검사 */
    	try {
        authService.checkUsernameDuplication(signupDto);	
        authService.checkUseremailDuplication(signupDto);
    	}
    	catch(IllegalStateException e){
    		System.out.println("중복 발생");
    		return "400"; //중복 발생 
    		
    	}

        //AuthService.userJoin(signupDto);

	
        User user = signupDto.toEntity();
		log.info(user.toString());
		authService.회원가입(user);
		// System.out.println(userEntity);
		return "200"; //회원가입 성공 
 
	}
	
	/* 로그아웃 */
	@RequestMapping("/user/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            System.out.println("로그아웃 성공");
        }
        
       
        
    }

	
}
