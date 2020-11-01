package com.crisda24.market.web.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.crisda24.market.domain.service.PlaziUserDetailsService;
import com.crisda24.market.web.security.JWTUtil;

import javassist.expr.NewArray;

@Component
public class JwtFilterRequest extends OncePerRequestFilter{

	@Autowired
	private JWTUtil jwtUil;
	
	@Autowired
	private PlaziUserDetailsService plaziUserDetailsService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorizationHeader = request.getHeader("Authorization");
	
		
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
			String jwt = authorizationHeader.substring(7);
			String username = jwtUil.extarctUsername(jwt);
			if(username !=null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = plaziUserDetailsService.loadUserByUsername(username);
				
				if(jwtUil.validateToken(jwt, userDetails)) {
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities() );
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					
					SecurityContextHolder.getContext().setAuthentication(authToken);
					
				}
			}
		}
		
		filterChain.doFilter(request, response);
		
	}
	

}
