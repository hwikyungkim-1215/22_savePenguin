package com.cos.photogramstart;
/*
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.service.UserService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
 @Autowired AuthService memberService;
 @Autowired UserRepository memberRepository;
 @Test
 public void 회원가입() throws Exception {
 //Given
 User user = new User();
 user.setUserid("hello");
 //When
 User saveId = memberService.회원가입(user);
 //Then
 User findMember = memberRepository.findByUserid(saveId).get();
 assertEquals(member.getName(), findMember.getName());
 }
 
 
 @Test
 public void 중복_회원_예외() throws Exception {
 //Given
 Member member1 = new Member();
 member1.setName("spring");
 Member member2 = new Member();
 member2.setName("spring");
 //When
 memberService.join(member1);
 IllegalStateException e = assertThrows(IllegalStateException.class,
 () -> memberService.join(member2));//예외가 발생해야 한다.
 assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
 }
}

*/