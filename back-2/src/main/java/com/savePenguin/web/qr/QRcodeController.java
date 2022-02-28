package com.savePenguin.web.qr;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.savePenguin.dao.QRcodeRepository;
import com.savePenguin.domain.qr.QRcode;


//@RequiredArgsConstructor
@RestController
public class QRcodeController {
	
	QRcodeRepository qrcoderepository;
	
	public QRcodeController(QRcodeRepository qrcoderepository) {
		super();
		this.qrcoderepository = qrcoderepository;
	}
	
	
	/*
	//qrcode 생성
	@PostMapping("/qrcode")
	public QRcode putQRcode (String qrname, String cuppic , int cuptype, String userid) {
		System.out.println("QR code 생성 진입");
		QRcode c_QR = new QRcode(qrname,cuppic ,cuptype,userid);
		System.out.println("QR code 생성 완료");
		return qrcoderepository.save(c_QR);
		
	} 
	
	
	@PostMapping("/qrupload")
	public String QRcodeUpload(QRcodeUploadDto qrcodeUploadDto ) {
		
		if(qrcodeUploadDto.getFile().isEmpty()) {
			System.out.println("이미지가 첨부되지 않았습니다.");
		}
		
		String cuppic =  qrcodeservice.QRupload(qrcodeUploadDto );
		
		
		
	}
	
	
	//qrcode 생성 -- test용임. 서버 소통 안함.
	@PostMapping("/qrcodetest/get")
	public QRcode uploadQRcode2 (String qrname,  @RequestParam("image")MultipartFile image, int cuptype, String userid) {
		
		System.out.println("QR code 생성 진입");
		//image 업로드 과정
		if(image == null || image.isEmpty()) {
			System.out.println("실패");
		}
		//확장자
		String prefix = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf(".")+1, image.getOriginalFilename().length());
		UUID uuid = UUID.randomUUID(); // uuid
		String filename =  uuid+"_"+ image.getOriginalFilename() + "." + prefix;
		System.out.println("이미지 파일 이름 : "+ filename);
		String filepath = "/Users/chaehuiseon/Documents/workspace-spring-tool-suite-4-4.13.0.RELEASE/cup/"+userid+"/";
		
		//파일 없으면 만들
		File folder = new File(filepath);
		if(!folder.isDirectory()) {
			folder.mkdirs();
		}
		
		//컵이미지 저장
		File dest = new File(filepath+filename);
		try {
			image.transferTo(dest);
			System.out.println("완료");
		}catch(Exception e) {
			e.printStackTrace();
		}
		String cuppic = filename;
		
		//QR 생성
		QRcode c_QR = new QRcode(qrname,cuppic ,cuptype,userid);
		System.out.println("QR code 생성 완료");
		return qrcoderepository.save(c_QR);
		
	} 
	*/
	//
	

	//완성본
	//qrcode 생성
	@PostMapping("/qrcodetest")
	public QRcode uploadQRcode3 (MultipartHttpServletRequest request) {
		
		String qrname = request.getParameter("qrname");
		//String userid = request.getParameter("userid");
		int cuptype = Integer.parseInt(request.getParameter("cuptype"));
		MultipartFile image =  request.getFile("cuppic");
		String userid = request.getParameter("userid");
				
		System.out.println("QR code 생성 진입");
		//image 업로드 과정
		if(image == null || image.isEmpty()) {
			System.out.println("실패");
		}
		System.out.println(image.getOriginalFilename());
		//확장자
		String prefix = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf(".")+1, image.getOriginalFilename().length());
		UUID uuid = UUID.randomUUID(); // uuid
		String filename =  uuid+"_"+ image.getOriginalFilename() + "." + prefix;
		System.out.println("이미지 파일 이름 : "+ filename);
		String filepath = "C:/Users/skaja/test/cup/"+userid+"/";
		
		//파일 없으면 만들
		File folder = new File(filepath);
		if(!folder.isDirectory()) {
			folder.mkdirs();
		}
		
		//컵이미지 저장
		File dest = new File(filepath+filename);
		try {
			image.transferTo(dest);
			System.out.println("완료");
		}catch(Exception e) {
			e.printStackTrace();
		}
		String cuppic = filename;
		
		//QR 생성
		QRcode c_QR = new QRcode(qrname,cuppic ,cuptype,userid);
		System.out.println("QR code 생성 완료");
		return qrcoderepository.save(c_QR);
		
	} 
	
	
	//완성본
	//userid 가 생성했던 모든 qr을 보여줌
	@PostMapping(value = "/qrcode/{userid}",produces =  {"application/json", "text/xml"})
	@ResponseBody
	public   JSONObject QRcodeLookup2(@PathVariable("userid") String userid) {
		int count = qrcoderepository.countByuserid(userid);
		
		System.out.println(qrcoderepository.sellectAllQRcode(userid).toString());
		System.out.println(qrcoderepository.sellectAllQRcode(userid).size());
		
		List<Object[]> results = qrcoderepository.sellectAllQRcode(userid);
		
		JSONObject jsonObject = new JSONObject();
		JSONArray qrArray = new JSONArray();
		
		for(Object[] result : results ) {
			System.out.println("qrname:"+result[0]);
			System.out.println("cuppic:"+result[1]);
			String tmp_cuppic = result[1].toString();
			String qrname = result[0].toString();
			System.out.println(tmp_cuppic);
			
			try {
				InputStream imageStream = new FileInputStream("C:/Users/skaja/test/cup/" + userid+"/"+tmp_cuppic);
				byte[] imageByteArray = IOUtils.toByteArray(imageStream);
				System.out.println("image");
				System.out.println(imageByteArray.toString());
				JSONObject qrInfo = new JSONObject();

				qrInfo.put("qrname", qrname);
				qrInfo.put("data",imageByteArray);
				qrArray.add(qrInfo);
				jsonObject.put(qrname,imageByteArray);
				imageStream.close();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			jsonObject.put("qrlist", qrArray);
		
	
		
			//byte[] imageByteArray = IOUtils.toByteArray(imageStream);
			//System.out.println("image");
			//System.out.println(imageByteArray.toString());
			//imageStream.close();
			
		}
		
		//HashMap<String,byte[]> hashmap = new HashMap<>();
		for(int i = 0 ; i < count; i++) {
			System.out.println("hello");
		}
		System.out.println(jsonObject.toString());
		
		return jsonObject;
		
	}
	
	
	
	

	//큐알 인증 페이지
	//포스트맨 테스트 완료 + 완성본
	@PostMapping(value = "/specifyqrcode/{userid}/{qrname}",produces =  {"application/json", "text/xml"})
	@ResponseBody
	public   JSONObject SpecifyQRcodeLookup(@PathVariable("userid") String userid, @PathVariable("qrname") String qrname) {
		List<Object[]> qr_one_results = qrcoderepository.sellectOneQRcode(userid, qrname);
		
		JSONObject jsonObject = new JSONObject();
		JSONArray qrArray = new JSONArray();
				
		for(Object[] qr_one_result : qr_one_results ) {
			System.out.println("qrpic:"+ qr_one_result[0]);
			System.out.println("찾은 qrname:"+ qr_one_result[1] + "--받은 것-- :"  +qrname);
			String qrpic = qr_one_result[0].toString();
			//String qrname = qr_one_result[1].toString();
			System.out.println();
			
			try {
				InputStream imageStream = new FileInputStream("C:/Users/skaja/test/qr/" + userid+"/"+qrpic +".png");
				byte[] imageByteArray = IOUtils.toByteArray(imageStream);
				System.out.println("image");
				System.out.println(imageByteArray.toString());
				JSONObject qrInfo = new JSONObject();

				qrInfo.put("qrname", qrname);
				qrInfo.put("data",imageByteArray);
				qrArray.add(qrInfo);
				imageStream.close();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			jsonObject.put("qrlist", qrArray);
			
	
		
			//byte[] imageByteArray = IOUtils.toByteArray(imageStream);
			//System.out.println("image");
			//System.out.println(imageByteArray.toString());
			//imageStream.close();
			
		}
		System.out.println("hi");
		System.out.println(jsonObject.toString());
		
		
		return jsonObject;
		
	}
	
	
	
	
	
	
	
	

}
