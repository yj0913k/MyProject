package com.example.junproject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CharacterEncodingFilter;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    MyUserDetailsService customUserDetailsService() {
        return new MyUserDetailsService();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);

        http
                //url 보안설정
                .authorizeRequests(authorize -> authorize
                        //윗줄에서 적용한 주소나 패턴이 우선적용됩니다.
                        .antMatchers("/css/**","/js/**","/images/**").permitAll()
                        .antMatchers("/progress/**").hasRole("ADMIN") //인증권한 ADMIN이 필요하다. 권한 =(ROLE_ADMIN)
                        .antMatchers("/member/signup","/common/**").permitAll()//인증없이 접속할수 있는 주소나 패턴
//                        .antMatchers("/**").permitAll()//인증없이 접속할수 있음
                        .anyRequest().authenticated()//모든 페이지에 접속제한.

                )
                .formLogin(formLogin->formLogin
                        .loginPage("/user/login")
                        .loginProcessingUrl("/login") //form태그 액션 설정.
                        .usernameParameter("email")// username 을 email 로 설정
                        .passwordParameter("pass")//  password 를 pass 로 변경
                        .defaultSuccessUrl("/",true) //인증 성공시 접속페이지 "/"이므로 index.
                        .permitAll()


                )
                .csrf(csrf->csrf.disable()) //토큰으로 대체가능 위조떄문에


        ;
        return http.build();
    }




}
