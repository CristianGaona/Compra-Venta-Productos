package com.crisda24.market.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crisda24.market.domain.dto.AuthenticationRequest;
import com.crisda24.market.domain.dto.AuthenticationResponse;
import com.crisda24.market.domain.service.PlaziUserDetailsService;
import com.crisda24.market.web.security.JWTUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private PlaziUserDetailsService plaziUserDetailsService;
	@Autowired
	private JWTUtil jWTUtil;

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> create(@RequestBody AuthenticationRequest request) {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
			UserDetails userDetails = plaziUserDetailsService.loadUserByUsername(request.getUsername());
			String jwt = jWTUtil.generateToken(userDetails);
			return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);

		} catch (BadCredentialsException e) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

	}
}
