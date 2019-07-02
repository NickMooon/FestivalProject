package com.example.demo.config;

import com.example.demo.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;



@Configuration
@EnableWebSecurity
public class LoginConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetails;

    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetails).passwordEncoder(NoOpPasswordEncoder.getInstance());
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/start", "/", "/events", "/register", "/data/**").permitAll()
                .antMatchers("/events/\\d+/signup","/events/\\d+").hasAnyRole("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/events/addevent", "/events/*/addperf","/addperf").hasAnyRole("ADMIN")
                .anyRequest().authenticated().and().formLogin().loginPage("/login").defaultSuccessUrl("/events").permitAll().and().logout().and().csrf().disable();

    }

}