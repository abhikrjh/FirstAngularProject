package com.example.FirstAngularProject.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.FirstAngularProject.dao.UserDAO;

@Service
public class MyUserDetailService implements UserDetailsService {
	@Autowired
	private UserDAO userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Inside loadByUserName");
		System.out.println("NOTE ***** username and firstname of user is being used as username and password respectively");
     
		com.example.FirstAngularProject.model.User daoUser = userDao.findUserByUserName(username);

		if (null != daoUser) {
			User user = new User(daoUser.getUsername(), daoUser.getFirstname(), new ArrayList<>());

			return user;
		}

		return null;
	}

}
