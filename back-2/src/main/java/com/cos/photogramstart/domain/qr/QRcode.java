package com.cos.photogramstart.domain.qr;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//data ->클래스의 모든 멤버 변수들에 대해 getter, setter등을 자동 생성함.

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class QRcode {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY) //id자동생성 //값 보내주지 않아도 자동 생성됨.자동생성할 수 있다지, 접근 불가능은 아님.
	private int id;
	
	@Column(length = 1024)
	private String qrname;
	
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int qrcount;
	
	@Column(length = 1024)
	private String qrpic; // 사진을 전송받아서 그 사진을 서버에 특정 폴더에 저장 - DB에 그 저장된 경로를 insert
	
	@Column(length = 1024)
	private String cuppic;
	
	@Column
	private int cuptype;
	
	@Column
	private int qrpoint;
	
	@Column(length = 1024)
	private String userid;

	/*
	public QRcode(String qrname, String cuppic, int cuptype, String userid) {
		System.out.println("hello");

		this.qrname = qrname;
		this.cuppic = cuppic;
		this.cuptype = cuptype;
		this.userid = userid;
		
		if(this.cuptype == 1) {
			this.qrpoint = 50;
		}else {
			this.qrpoint = 100;
		}
		
		//최종 완성될 JSONObject 선언(전체)
        JSONObject jsonObject = new JSONObject();
        //QR의 JSON정보를 담을 Array 선언
        JSONArray qrArray = new JSONArray();
        //QR의 한명 정보가 들어갈 JSONObject 선언
        JSONObject qrInfo = new JSONObject();

        qrInfo.put("qrname", this.qrname);
        qrInfo.put("cuptype", this.cuptype);
        qrInfo.put("userid", this.userid);
        qrInfo.put("qrpoint", this.qrpoint);
        
        qrArray.add(qrInfo);
        jsonObject.put("qrinfo",qrArray);
        
        String jsonInfo = jsonObject.toJSONString();
		
	    String filepath = "/Users/chaehuiseon/Documents/workspace-spring-tool-suite-4-4.13.0.RELEASE/qr/";
	    this.qrpic = filepath;
	    String text = jsonInfo;
	    
	    try {
	    	createQRImage img = new createQRImage(text,this.qrname, filepath);
	    	
	    }catch (Exception e) {
			System.out.print(e.getMessage());
		}
	    
		
		
	}
	*/
	
	
	public QRcode(String qrname, String cuppic, int cuptype, String userid) {
		System.out.println("hello");

		this.qrname = qrname;
		this.cuppic = cuppic;
		this.cuptype = cuptype;
		this.userid = userid;
		
		if(this.cuptype == 1) {
			this.qrpoint = 50;
		}else {
			this.qrpoint = 100;
		}
		
		
	    String filepath = "C:/Users/skaja/test/qr/"+userid+"/";
	    
	    
	    //파일 없으면 만들
  		File folder = new File(filepath);
  		if(!folder.isDirectory()) {
  			folder.mkdirs();
  		}
	    
  		UUID uuid = UUID.randomUUID(); // uuid
	    
	    this.qrpic = uuid+"_"+this.qrname;
	    
	    String text = this.qrname + "|" + this.cuptype +  "|" + this.userid +  "|" + this.qrpoint;
	    
	    try {
	    	createQRImage img = new createQRImage(text,this.qrpic, filepath);
	    	
	    }catch (Exception e) {
			System.out.print(e.getMessage());
		}
	    
		
		
	}
	
	



}

