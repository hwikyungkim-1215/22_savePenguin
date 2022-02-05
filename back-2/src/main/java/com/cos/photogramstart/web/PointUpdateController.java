package com.cos.photogramstart.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
@RestController  
public class PointUpdateController {
    @Autowired
    private UserService userService;
 
    @RequestMapping("/user/pointUpdate/{userid}") //포인트 수정     
    public void pointUpdate(HttpServletRequest request) {
    	System.out.println("진입 ");
    	String userid = request.getParameter("userid");
    	String userpoint = request.getParameter("userpoint");
    	userService.pointUpdate(userid, Integer.parseInt(userpoint));
    }
    
    
    @RequestMapping("/user/point/{userid}") //포인트 반환 
    public String totalPoint(HttpServletRequest request) {
    	System.out.println("total point 진입");
    	int point = userService.totalPoint(request.getParameter("userid"));
    	return String.valueOf(point);

    }
    
}
