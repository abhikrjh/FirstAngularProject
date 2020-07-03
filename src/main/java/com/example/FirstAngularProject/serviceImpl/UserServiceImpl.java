package com.example.FirstAngularProject.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FirstAngularProject.dao.UserRepository;
import com.example.FirstAngularProject.model.UserDetail;
import com.example.FirstAngularProject.service.UserService;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;
     
	@Override
	public List<UserDetail> findAllUser() {
		List<UserDetail> userList = userRepository.findAll();
		return userList;
	}
	
	
	@Override
	public UserDetail findUserByusername(String username) {
		UserDetail user = new UserDetail();
		user = userRepository.findByusername(username);
		return user;
	}

}