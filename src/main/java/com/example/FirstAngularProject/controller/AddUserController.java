package com.example.FirstAngularProject.controller;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.FirstAngularProject.model.Employee;
import com.example.FirstAngularProject.model.UserDetail;
import com.example.FirstAngularProject.service.EmployeeService;
import com.example.FirstAngularProject.service.UserService;
import com.example.FirstAngularProject.utility.Encryption;

@RestController
public class AddUserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AddUserController.class);
     
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	UserService userService;

	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {
		System.out.println("INside hello method ..");
		return "Hello world";
	}
	
	@RequestMapping(value = "/employeeList", method = RequestMethod.GET)
	public List<Employee> getAllEmployee(){
		List<Employee> employees = employeeService.findAllEmployee();
		return employees;
	}
	
	@PostMapping(value = "/addEmployee", consumes = "application/json", produces = "application/json")
	public List<Employee> addEmployee(@RequestBody Employee employee) {
		 employeeService.saveEmployee(employee);
		 List<Employee> employees = employeeService.findAllEmployee();
		 return employees;
	}
  
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "/deleteEmployee", consumes = "application/json", produces = "application/json")
	public List<Employee> deleteEmployee(@RequestBody Long employeeId) {
		employeeService.deleteEmployee(employeeId);
		List<Employee> employees = employeeService.findAllEmployee();
		return employees;
	}
	
	
	
	@PostMapping(value = "/registerUser", consumes = "application/json", produces = "application/json")
	public void registerUser(@RequestBody UserDetail user) {
	
		 try {
			Encryption encryption = new Encryption();
			String encryptrdPAssword = encryption.encrypt(user.getPassword());
			user.setPassword(encryptrdPAssword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 userService.saveUser(user);
	}

	/*@PostMapping(value = "/modifyUser", consumes = "application/json", produces = "application/json")
	public List<User> modifyExistingUser(@RequestBody User user) {

		UtilityCLass utilityCLass = new UtilityCLass();
		JSONObject jsonObject = utilityCLass.convertToJSON(user);
		List<User> userList = userDao.addUser(jsonObject);

		return userList;
	}*/

}
