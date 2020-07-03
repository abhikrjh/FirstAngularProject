package com.example.FirstAngularProject.serviceImpl;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.FirstAngularProject.model.UserDetail;
import com.example.FirstAngularProject.service.ValidateUser;

//@Service
public class ValideUserImpl implements ValidateUser{

	@Override
	public boolean validUser(List<UserDetail> userList) {
		
		Iterator<UserDetail> itr = userList.iterator();
		
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
		return true;
	}


}
