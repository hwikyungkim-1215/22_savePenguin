package com.cos.photogramstart.web;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.domain.point.Point;
import com.cos.photogramstart.domain.point.PointRepository;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.service.AuthService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
@RestController // json으로 반환 
public class pointHistoryAndUpdateController {
	
	 @Autowired
	 UserRepository userrepository;
	 
	 @Autowired
	 PointRepository pointrepository;
	 
	 
	 
	 //완료
	 @RequestMapping("/qrcode/point/history_update")
    public String pointHistoryandUpdate(HttpServletRequest request) {
    	System.out.println("진입 성공");
    	String qrname = request.getParameter("qrname");
    	int cuptype = Integer.parseInt(request.getParameter("cuptype"));
    	String userid = request.getParameter("userid");
    	int cuppoint = Integer.parseInt(request.getParameter("qrpoint"));
    	System.out.println(qrname + cuptype + userid + " cuppoint: "+cuppoint);
    	
    	User user = userrepository.findByUserid(userid);
    	String username = user.getUsername();
    	
    	System.out.println(qrname + cuptype + userid + " cuppoint: "+cuppoint);
    	System.out.println(user.toString());
    	System.out.println(username);
    	
    	Point newpoint = new Point(username,qrname,cuppoint);
    	System.out.println(newpoint.toString());

    	pointrepository.save(newpoint);
    	
    	System.out.println("기존 total " + user.getPoint());
    	int updateTotalPoint = user.getPoint() + cuppoint;
    	System.out.println(updateTotalPoint);
    	pointrepository.updateUserPoint(updateTotalPoint, userid);
    	System.out.println("hihi");
    	
    	return "success";
    	
    	

	    	
	    }
	 
	 //포인트 내역 조회
	 //포스트맨 테스트 완료
	 //cuppint, pointDate, pointLocation, qrname
	 @RequestMapping(value = "/point/inquiry/{userid}")
	 public JSONObject inquiryPointHistory(@PathVariable("userid") String userid) {
		System.out.println("진입성공");
		User user = userrepository.findByUserid(userid);
		String username = user.getUsername();
		System.out.println(user.toString());
		System.out.println(username);
	    	
		List<Object[]> point_results = pointrepository.sellectAllPoint(username);
		
			
		JSONObject jsonObject = new JSONObject();
		JSONArray pointArray = new JSONArray();
		
		
		for(Object[] result : point_results ) {
			System.out.println("cuppint : " + result[0]);
			System.out.println( "pointData : " + result[1]);
			System.out.println("pointLocation : " + result[2]);
			System.out.println("qrname : " + result[3]);
			
			JSONObject pointInfo = new JSONObject();
			
			pointInfo.put("cuppoint", result[0]);
			pointInfo.put("pointData", result[1]);
			pointInfo.put("pointLocation", result[2]);
			pointInfo.put("qrname", result[3]);
			
			pointArray.add(pointInfo);
			
			
		}
		jsonObject.put("pointlist", pointArray);
		
		System.out.println(jsonObject.toString());
		
		return jsonObject;
		 
	 }
	 
	 
	 
	 
	 

}



