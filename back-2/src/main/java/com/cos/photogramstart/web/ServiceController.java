package com.cos.photogramstart.web;
/*
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.photogramstart.service.EmailService;
import com.cos.photogramstart.service.EmailServiceImpl;
import com.cos.photogramstart.service.AuthService;

@Controller
@RequestMapping("/service/*")
public class ServiceController {
	 @Autowired
	 EmailService emailservice;
	 
	 private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	 
		@PostMapping("/mail")
		@ResponseBody
		public void emailConfirm(String userId)throws Exception{
			logger.info("post emailConfirm");
			System.out.println("전달 받은 이메일 : "+userId);
			emailservice.sendSimpleMessage(userId);	
		}
		@PostMapping("/verifyCode")
		@ResponseBody
		public int verifyCode(String code) {
			logger.info("Post verifyCode");
			
			int result = 0;
			System.out.println("code : "+code);
			System.out.println("code match : "+ EmailServiceImpl.ePw.equals(code));
			if(EmailServiceImpl.ePw.equals(code)) {
				result =1;
			}
			
			return result;
		}
}
*/