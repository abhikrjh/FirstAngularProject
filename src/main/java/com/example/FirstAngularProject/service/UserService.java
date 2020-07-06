package com.example.FirstAngularProject.service;

import java.util.List;

import com.example.FirstAngularProject.model.UserDetail;

public interface UserService {
	
	public List<UserDetail> findAllUser();
	
	public UserDetail findUserByusername(String username);
	
	public void saveUser(UserDetail user);

}
