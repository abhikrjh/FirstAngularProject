package com.example.FirstAngularProject.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import com.example.FirstAngularProject.model.User;

@Repository
public class UserDAO {

	private static final Map<Long, User> userMap = new HashMap<Long, User>();

	static {
		initUsers();
	}

	private static void initUsers() {
		User usr1 = new User(1L, "sachin", "tendulkar", "srt", 42, 20000);
		User usr2 = new User(2L, "virendra", "sehwag", "viru", 38, 19000);
		User usr3 = new User(3L, "sourav", "gangully", "dada", 41, 18000);
		User usr4 = new User(4L, "rahul", "dravid", "wall", 44, 17000);
		User usr5 = new User(5L, "vvs", "laxman", "laxi", 44, 16000);
		User usr6 = new User(6L, "yuvraj", "singh", "yuvi", 37, 15000);
		User usr7 = new User(7L, "mahendra", "singh", "mahi", 38, 14000);

		userMap.put(usr1.getUserId(), usr1);
		userMap.put(usr2.getUserId(), usr2);
		userMap.put(usr3.getUserId(), usr3);
		userMap.put(usr4.getUserId(), usr4);
		userMap.put(usr5.getUserId(), usr5);
		userMap.put(usr6.getUserId(), usr6);
		userMap.put(usr7.getUserId(), usr7);

	}

	public Long getMaxUserId() {
		Set<Long> keys = userMap.keySet();
		Long max = 0L;
		for (Long key : keys) {
			if (key > max) {
				max = key;
			}
		}
		return max;
	}

	public User getUser(Long userId) {
		return userMap.get(userId);
	}
	
	public User findUserByUserName(String username) {
		User user = new User();
		for(Entry<Long, User> entry : userMap.entrySet()) {
			user = entry.getValue(); 
			if(user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}

	public List<User> addUser(JSONObject userJson) {
		Long usrId = this.getMaxUserId() + 1;
		User newUser = new User();
		if (userJson != null && !userJson.isEmpty()) {
			User existingUsr = new User();
			for(Entry<Long, User> entry : userMap.entrySet()) {
				 existingUsr = entry.getValue(); 
					if(existingUsr.getUsername().equals((String) userJson.get("username"))) {
						return getAllUsers();
					}
			}
			newUser.setUserId(usrId);
			newUser.setFirstname((String) userJson.get("firstname"));
			newUser.setLastname((String) userJson.get("lastname"));
			newUser.setUsername((String) userJson.get("username"));
			newUser.setAge(((Long)userJson.get("age")).intValue());
			newUser.setSalary(((Long) userJson.get("salary")).intValue());
			userMap.put(newUser.getUserId(), newUser);
		}
		return getAllUsers();
	}
  
	public List<User> modifyUser(JSONObject userJson){
		
		User existingUsr = new User();
		for(Entry<Long, User> entry : userMap.entrySet()) {
			 existingUsr = entry.getValue(); 
				if(existingUsr.getUserId().equals(((Long) userJson.get("userId")))) {
					existingUsr.setUserId((Long) userJson.get("userId"));
					existingUsr.setFirstname((String) userJson.get("firstname"));
					existingUsr.setLastname((String) userJson.get("lastname"));
					existingUsr.setUsername((String) userJson.get("username"));
					existingUsr.setAge(((Long)userJson.get("age")).intValue());
					existingUsr.setSalary(((Long) userJson.get("salary")).intValue());
					userMap.put(existingUsr.getUserId(), existingUsr);
					return getAllUsers();
				}
		}
		return getAllUsers();
	}
	
	
	public List<User> deleteUser(Long userId) {
		userMap.remove(userId);
		
		return getAllUsers();
	}

	public List<User> getAllUsers() {

		Collection<User> c = userMap.values();
		List<User> usersList = new ArrayList<>();
		usersList.addAll(c);
		return usersList;
	}

}
