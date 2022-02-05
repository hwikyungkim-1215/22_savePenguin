package com.cos.photogramstart.domain.point;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
@Data
@Entity
public class Point {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 데이터베이스를 따라간다.
	private int id;

	@Column
	private String username; //아이디 
	@Column
	private int cuppint; // 포인트 
	
	@Column
	private String pointDate = "2022.02.05"; //포인트 획득 날짜 
	
	@Column
	private String pointLocation = "스타벅스 여의도역점"; //개인 컵 타입 
	
	@Column
	private String qrname; //qr이름 = 컵이름 
	
	
    public Point(Point entity){
    	this.username = entity.getUsername();
        this.cuppint = entity.getCuppint();
        this.pointDate = entity.getPointDate();
        this.pointLocation = entity.getPointLocation();
        this.qrname = entity.getQrname();
    }
    
    public Point(String username, String qrname, int cuppint) {
    	
    	long systemTime = System.currentTimeMillis(); // 출력 형태를 위한 formmater 
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
    	String dTime = formatter.format(systemTime);
    	
    	this.username = username;
    	this.qrname = qrname;
    	this.cuppint = cuppint;
    	this.pointDate = dTime;
    	this.pointLocation = "스타벅스 여의도역점";
    	
    }
    


}



