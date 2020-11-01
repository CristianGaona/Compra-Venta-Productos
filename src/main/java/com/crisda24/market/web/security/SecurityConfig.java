package com.crisda24.market.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.crisda24.market.domain.service.PlaziUserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private PlaziUserDetailsService plaziUserDetailsService;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(plaziUserDetailsService);
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.csrf().disable().authorizeRequests().antMatchers("/**/authenticate").permitAll()
		.anyRequest().authenticated();
	}


	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	
	 @Override
	    public void configure(WebSecurity web) throws Exception {
	        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui",
	                "/swagger-resources/**", "/configuration/security",
	                "/swagger-ui.html", "/webjars/**");
	    }
	
	
	
	

}
