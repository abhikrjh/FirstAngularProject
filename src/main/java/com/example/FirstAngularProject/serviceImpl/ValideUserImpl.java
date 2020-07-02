package com.example.FirstAngularProject.serviceImpl;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.FirstAngularProject.model.User;
import com.example.FirstAngularProject.service.ValidateUser;

@Service
public class ValideUserImpl implements ValidateUser{

	@Override
	public boolean validUser(List<User> userList) {
		
		Iterator<User> itr = userList.iterator();
		
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
		return true;
	}

}
