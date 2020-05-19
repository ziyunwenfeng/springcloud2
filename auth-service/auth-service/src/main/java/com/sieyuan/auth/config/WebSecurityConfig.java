package com.sieyuan.auth.config;

import com.sieyuan.auth.Utils.MyPasswordEncoder;
import com.sieyuan.auth.service.UserServiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserServiceDetail userServiceDetail;
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request,response,authException)->response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
                )
                .and()
                .authorizeRequests()
                .antMatchers("/**").authenticated()
                .and()
                .httpBasic();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.userDetailsService(userServiceDetail).passwordEncoder(passwordEncoder());
//        auth.userDetailsService(userServiceDetail).passwordEncoder(new BCryptPasswordEncoder());
        auth
                .inMemoryAuthentication()
                .passwordEncoder(new MyPasswordEncoder())
                .withUser("app")
                .password("1234")
                .roles("ROLE_ADMIN");

    }
    @Override
    @Bean
    public AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManagerBean();
    }
//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//
//        String finalPassword = "{bcrypt}"+bCryptPasswordEncoder.encode("123456");
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("user_1").password(finalPassword).authorities("USER").build());
//        manager.createUser(User.withUsername("user_2").password(finalPassword).authorities("USER").build());
//
//        return manager;
//    }






}
