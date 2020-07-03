package com.example.FirstAngularProject.service;

import java.util.List;

import com.example.FirstAngularProject.model.Employee;


public interface EmployeeService {
   
	public List<Employee> findAllEmployee();
	
	public void saveEmployee(Employee employee);
	
	public void deleteEmployee(Long id);
	
}
