package com.cos.photogramstart.config;

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.cos.photogramstart.config.auth.PrincipalDetailsService;
import org.springframework.core.annotation.Order;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration // 빈등록 (IoC)
@EnableWebSecurity // 시큐리티 설정 등록
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근하면 권한 및 인증을 미리 체크   
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	private final PrincipalDetailsService myUserDetailsService;
    /* 로그인 실패 핸들러 의존성 주입 */
    private final AuthenticationFailureHandler customFailurHandler;

    @Bean
    public BCryptPasswordEncoder Encoder() {
        return new BCryptPasswordEncoder();
    }
    
    /* 시큐리티가 로그인 과정에서 password를 가로챌때 어떤 해쉬로 암호화 했는지 확인 */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(Encoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable();
        http
        		.authorizeRequests()
                .antMatchers("/", "/user/**", "/api/**").permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/auth/login")
                .failureHandler(customFailurHandler) // 실패 핸들러
                .defaultSuccessUrl("/")
                .and()
                .logout()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }
	

}
