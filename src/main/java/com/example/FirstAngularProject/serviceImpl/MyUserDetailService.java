package com.example.FirstAngularProject.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.FirstAngularProject.service.UserService;
import com.example.FirstAngularProject.utility.Encryption;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		System.out.println("Inside loadByUserName");
		System.out.println(
				"NOTE ***** username and firstname of user is being used as username and password respectively");

		com.example.FirstAngularProject.model.UserDetail dbUser = userService.findUserByusername(username);

		String decryptedPassword = "";
		try {
			Encryption enc = new Encryption();
			decryptedPassword = enc.decrypt(dbUser.getPassword());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (null != dbUser) {
			User user = new User(dbUser.getUsername(), decryptedPassword, new ArrayList<>());

			return user;
		}

		return null;
	}

}
