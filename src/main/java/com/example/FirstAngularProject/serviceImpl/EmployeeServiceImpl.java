package com.example.FirstAngularProject.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FirstAngularProject.dao.EmployeeRepository;
import com.example.FirstAngularProject.model.Employee;
import com.example.FirstAngularProject.service.EmployeeService;


@Service
public class EmployeeServiceImpl implements EmployeeService{
	
    @Autowired
    EmployeeRepository employeeRepository;
	@Override
	public List<Employee> findAllEmployee() {
		List<Employee> employeeList = employeeRepository.findAll();
		return employeeList;
	}
	@Override
	public void saveEmployee(Employee employee) {
		
		employeeRepository.save(employee);
		
	}
	@Override
	public void deleteEmployee(Long id) {
		
		employeeRepository.deleteById(id);
		
	}

}
