package com.example.FirstAngularProject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.FirstAngularProject.Authentication.AuthenticationRequest;
import com.example.FirstAngularProject.Authentication.AuthenticationResponse;
import com.example.FirstAngularProject.SpringSecurity.JwtTokenUtil;
import com.example.FirstAngularProject.service.EmployeeService;
import com.example.FirstAngularProject.service.UserService;
import com.example.FirstAngularProject.serviceImpl.MyUserDetailService;

@RestController
public class LoginController {
    
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	UserService userService;
	
    @Autowired
    EmployeeService employeeService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailService myUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		 LOGGER.info("Into PostMethod of createAuthenticationToken");
		AuthenticationResponse authRes = null;
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		
		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		 String   jwt = jwtTokenUtil.generateToken(userDetails);

		authRes = new AuthenticationResponse(jwt);
		} catch (BadCredentialsException e) {
			authRes = new AuthenticationResponse("");
			throw new Exception("Invalid Username and Password");
		}

		return ResponseEntity.ok(authRes);
	}
	
}
