package com.jwtAuthapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort;

import com.jwtAuthapi.entity.Employee;
import com.jwtAuthapi.exception.BusinessException;
import com.jwtAuthapi.repository.AddressRepository;
import com.jwtAuthapi.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	public Employee getEmployeeById(int id) {
		

		
		try {
			return employeeRepository.findById(id).get();
			
		}catch (IllegalArgumentException e) {
			throw new BusinessException("601","given employee id is null, please send some id to be searched" + e.getMessage());
		}
		catch (java.util.NoSuchElementException e) {
			throw new BusinessException("602","given employee id doesnot exist in DB" + e.getMessage());
		}catch (Exception e) {
			throw new BusinessException("603","Something went wrong in Service layer while fetching all employees" + e.getMessage());
		}
		
	}
	
	public List<Employee> getAllEmployees(){
		
		
		List<Employee> empList = null;
		try {
			empList = employeeRepository.findAll();
		}
		catch (Exception e) {
			throw new BusinessException("604","Something went wrong in Service layer while fetching all employees" + e.getMessage());
		}
		if(empList.isEmpty())
			throw new BusinessException("605", "list is completely empty, we have nothing to return");
		
		return empList;
		
	}
	
	public Employee updateEmployee(Employee emp) {
		
		
		Employee employee = getEmployeeById(emp.getId());
		
		employee.setFirstName(emp.getFirstName());
		employee.setLastName(emp.getLastName());
		employee.setEmailId(emp.getEmailId());
		employee.setAddress(emp.getAddress());
		
		
		try {
			Employee savedEmployee = employeeRepository.save(employee);
			return savedEmployee;
		}catch (IllegalArgumentException e) {
			throw new BusinessException("606","given employee is null" + e.getMessage());
		}catch (Exception e) {
			throw new BusinessException("607","Something went wrong in Service layer while saving the employee" + e.getMessage());
		}
	}
	
	public String deletEmployee(int id) {
		
		try {
			employeeRepository.deleteById(id);
		}catch (IllegalArgumentException e) {
			throw new BusinessException("608","given employee id is null, please send some id to be deleted" + e.getMessage());
		}catch (Exception e) {
			throw new BusinessException("609","Something went wrong in Service layer while fetching all employees" + e.getMessage());
		}
		
		
		return id+" is deleted.";
		
		}
	
	public List<Employee> getEmployeeByCity(String city){
		
		
		
		try {
			return employeeRepository.findByAddressCity(city);
			
		}catch (IllegalArgumentException e) {
			throw new BusinessException("610","given city is null" + e.getMessage());
		}
		catch (java.util.NoSuchElementException e) {
			throw new BusinessException("611","given city doesnot exist in DB" + e.getMessage());
		}catch (Exception e) {
			throw new BusinessException("612","Something went wrong in Service layer while fetching employees" + e.getMessage());
		}
			
	}
	
	public List<Employee> sortEmployeesByLastName(){
		
		try {
			return employeeRepository.findAll(Sort.by("lastName"));
			
		}catch (IllegalArgumentException e) {
			throw new BusinessException("613","given last_name is null" + e.getMessage());
		}
		catch (java.util.NoSuchElementException e) {
			throw new BusinessException("614","given last_name doesnot exist in DB" + e.getMessage());
		}catch (Exception e) {
			throw new BusinessException("615","Something went wrong in Service layer while fetching employees" + e.getMessage());
		}
		
	}
}
	
	
	
	
	

