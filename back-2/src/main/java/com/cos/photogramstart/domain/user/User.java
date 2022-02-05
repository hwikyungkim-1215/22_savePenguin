package com.cos.photogramstart.domain.user;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;

//import com.cos.photogramstart.domain.point.Point;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor //기본 생성자 자동 추가 
@Data // Getter
@Entity // 디비에 테이블을 생성
public class User extends TimeEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 데이터베이스를 따라간다.
	private int id;
	
	@Column(length = 100,  unique = true) // OAuth2 로그인을 위해 칼럼 늘리기(제약조건)
	private String userid;//username 
	@Column(nullable = false)
	private String userpw;
	
	@Column(nullable = false)
	private String username; //name
	
	@Column(nullable = false)
	private String useremail; //email
	
	@Column
	private int point; //최종 포인트 


	@Override
	public String toString() {
		return "User [id=" + id + ", userid=" + userid + ", password=" + userpw + ", username=" + username + ", email=" + useremail + ", point=" + point  + "]";
	}
	
	
    /* userpw과 useremail만 수정 가능 */
	public void 회원수정(String userpw, String useremail) {
		this.userpw = userpw;
		this.useremail = useremail;
	}

	
    public User(String userid) {
        this.userid = userid;
        //this.userpw = userpw;
    }


    
    public void PointChange(int point) { //포인트 수정 
    	System.out.println("PointChange");
    	
    	this.point = this.getPoint();
    	
    }


	public String isPresent() {
		// TODO Auto-generated method stub
		return null;
	}


	


	
}

