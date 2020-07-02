package com.example.FirstAngularProject.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FirstAngularProject.Authentication.AuthenticationRequest;
import com.example.FirstAngularProject.Authentication.AuthenticationResponse;
import com.example.FirstAngularProject.SpringSecurity.JwtTokenUtil;
import com.example.FirstAngularProject.dao.UserDAO;
import com.example.FirstAngularProject.model.Credentials;
import com.example.FirstAngularProject.model.User;
import com.example.FirstAngularProject.service.ValidateUser;
import com.example.FirstAngularProject.serviceImpl.MyUserDetailService;
import com.example.FirstAngularProject.utility.UtilityCLass;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.security.core.userdetails.UserDetails;

@RestController
public class AddUserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AddUserController.class);

	@Autowired
	ValidateUser validateUser;
	@Autowired
	private UserDAO userDao;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailService myUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {
		System.out.println("INside hello method ..");
		return "Hello world";
	}

	//@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/userList", method = RequestMethod.GET)
	public @ResponseBody List<User> getAllUsers() {

		// LOGGER.info("Into GetMethod of cotroller");
		List<User> users = userDao.getAllUsers();
		return users;
	}

	//@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "/users", consumes = "application/json", produces = "application/json")
	public List<User> getUserList(@RequestBody Credentials credentials) {
		// System.out.println("userNamePassword : "+credentials.getUsername() +" "+
		// credentials.getPassword());

		List<User> users = userDao.getAllUsers();
		boolean isValidUser = validateUser.validUser(users);
		return users;
	}

	//@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "/addUser", consumes = "application/json", produces = "application/json")
	public List<User> addUser(@RequestBody User user) {

		UtilityCLass utilityCLass = new UtilityCLass();
		JSONObject jsonObject = utilityCLass.convertToJSON(user);
		List<User> userList = userDao.addUser(jsonObject);

		return userList;
	}

	//@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "/modifyUser", consumes = "application/json", produces = "application/json")
	public List<User> modifyExistingUser(@RequestBody User user) {

		UtilityCLass utilityCLass = new UtilityCLass();
		JSONObject jsonObject = utilityCLass.convertToJSON(user);
		List<User> userList = userDao.addUser(jsonObject);

		return userList;
	}

	//@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "/deleteUser", consumes = "application/json", produces = "application/json")
	public List<User> deleteUser(@RequestBody Long userId) {

		List<User> users = userDao.deleteUser(userId);

		return users;
	}

	//@CrossOrigin(origins = "http://localhost:4200")
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
