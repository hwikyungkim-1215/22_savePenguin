package com.cos.photogramstart.config.auth;
import javax.servlet.http.HttpSession;

//로그인 
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Component
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    private final HttpSession session;
    

    /* username이 DB에 있는지 확인 */
    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {

        
		User userEntity = userRepository.findByUserid(userid);
		
		if(userEntity == null) {
			return null; //해당 사용자 존재 X 
		}else {
			/* 시큐리티 세션에 유저 정보 저장 */
			return new PrincipalDetails(userEntity);
		}
    }
}

