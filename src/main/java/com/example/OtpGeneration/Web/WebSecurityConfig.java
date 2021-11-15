package com.example.OtpGeneration.Web;


import com.example.OtpGeneration.Security.JwtAuthenticationEntryPoint;
import com.example.OtpGeneration.Security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableGlobalMethodSecurity(prePostEnabled = true,jsr250Enabled = true)
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public JwtFilter jwtFilter()
    {
        return new JwtFilter();
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint);
        http.authorizeRequests()
                .antMatchers("/cdk-auth-service/user/createuser",
                        "/cdk-auth-service/user/generateotp",
                        "/cdk-auth-service/user/resendotp",
                    //    "/cdk-auth-service/user/summa",
                        "/cdk-auth-service/user/validateotp").permitAll()
                .anyRequest().authenticated().and()
                .formLogin().permitAll();

        http.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/cdk-auth-service/user/createuser",
                "/cdk-auth-service/user/generateotp",
                "/cdk-auth-service/user/resendotp",
                //    "/cdk-auth-service/user/summa",
                "/cdk-auth-service/user/validateotp");
    }
    /*@Override
    public void configure(WebSecurity webSecurity) throws Exception
    {
        webSecurity
                .ignoring()
                .antMatchers("/user/create","/user/loggedin","/role/create");
    }*/

}

