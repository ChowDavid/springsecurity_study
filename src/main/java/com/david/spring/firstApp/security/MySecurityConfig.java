package com.david.spring.firstApp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.Md4PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@EnableWebSecurity
@Configuration
public class MySecurityConfig  {

    @Bean
    public PasswordEncoder encoder() {
        //return new Md4PasswordEncoder();
        return new BCryptPasswordEncoder();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web -> web.ignoring().antMatchers("/ignore1", "/ignore2"));
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //return generatePermitAll(http);
        //return generateDefault(http);
        return generateInMemory(http);

    }

    private SecurityFilterChain generateInMemory(HttpSecurity http) throws Exception {
        //generate password from painText
        //PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        // outputs {bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG


        InMemoryUserDetailsManager userDetailService = new InMemoryUserDetailsManager();
        UserDetails user = User
                .withUsername("david")
                .password(encoder().encode("password"))
                .authorities("read")
                .build();

        userDetailService.createUser(user);


        http.authorizeRequests(authz -> authz.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .userDetailsService(userDetailService);
        return http.build();
    }

    private SecurityFilterChain generateDefault(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests((authz)-> authz.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    private SecurityFilterChain generatePermitAll(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests(authz-> authz.anyRequest().permitAll())
                .build();
    }
}
