package com.casestudy.authentication.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
@EnableWebSecurity
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.authenticationProvider(getDaoAuthProvider());
        auth.userDetailsService(customUserDetailsService).passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

		
		
		  http.csrf().disable().authorizeRequests()
		  .antMatchers("/login","/register","/user/**").permitAll().anyRequest().
		  authenticated().and().exceptionHandling().and()
		  .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		  
		 
		
		
		/*
		 * http .csrf().disable() .authorizeRequests()
		 * .antMatchers("/login","/register").permitAll()
		 * .antMatchers("/admin/**").hasRole("ADMIN")
		 * .antMatchers("/user/**").hasRole("USER") .anyRequest() .authenticated()
		 * .and() .formLogin() .loginPage("http://localhost:4200/login");
		 */
		 
		 
    }




}
