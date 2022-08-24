package com.david.spring.firstApp.security;

import com.david.spring.firstApp.security.filter.MyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class MySecurityConfig  {

    @Autowired
    private UserDetailsService userDetailsServiceImpl;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web -> web.ignoring().antMatchers("/ignore1", "/ignore2"));
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return myAuthunication(http);
    }

    private SecurityFilterChain myAuthunication(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests(authz -> authz
                        .mvcMatchers(HttpMethod.GET,"/hello").hasAnyAuthority("READ","ADMIN")
                        .mvcMatchers(HttpMethod.GET,"/bye").hasAnyAuthority("ADMIN")
                        .anyRequest().denyAll())
                .csrf().disable()
                .httpBasic(Customizer.withDefaults())
                .userDetailsService(userDetailsServiceImpl)
                .build();
    }



}
