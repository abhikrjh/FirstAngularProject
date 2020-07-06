package com.example.FirstAngularProject.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.example.FirstAngularProject.service.UserService;
import com.example.FirstAngularProject.utility.Encryption;

import com.example.FirstAngularProject.model.UserDetail;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		System.out.println("Inside loadByUserName");
		UserDetail dbUser = userService.findUserByusername(username);

		String decryptedPassword = "";

		if (null != dbUser) {
			try {
				Encryption enc = new Encryption();
				decryptedPassword = enc.decrypt(dbUser.getPassword());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			User user = new User(dbUser.getUsername(), decryptedPassword, getGrantedAuthority(dbUser));

			return user;
		}

		return null;
	}

	private Collection<GrantedAuthority> getGrantedAuthority(UserDetail userDetail) {

		Collection<GrantedAuthority> grantedAuthroites = new ArrayList<>();

		if (userDetail.getRole().equals("ADMIN")) {
			grantedAuthroites.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		return grantedAuthroites;
	}

}
