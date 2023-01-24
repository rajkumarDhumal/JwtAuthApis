package com.jwtAuthapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.jwtAuthapi.entity.Employee;
import com.jwtAuthapi.exception.BusinessException;
import com.jwtAuthapi.exception.ControllerException;
import com.jwtAuthapi.repository.EmployeeRepository;
import com.jwtAuthapi.service.EmployeeService;


@RestController
@RequestMapping("/employee/")
public class MainController implements ErrorController {
	
	  private static final String PATH = "/error";

	    @RequestMapping(value = PATH)
	    public String error() {
	        return "Error handling";
	    }
	    
	    public String getErrorPath() {
	        return PATH;
	    }
	
	

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmployeeRepository employeeRepository;
	

	@GetMapping("/getById/{empId}")
	public ResponseEntity<?> getEmployeeeById(@PathVariable int empId) {

		
		try {
			Employee empRetrieved = employeeService.getEmployeeById(empId);
			return new ResponseEntity<Employee>(empRetrieved, HttpStatus.OK);
			
		}catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			ControllerException ce = new ControllerException("619","Something went wrong in controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}
	 

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllEmployee(){

		List<Employee> listOfAllEmps;
		
		try {
			listOfAllEmps = employeeService.getAllEmployees();
		}catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			ControllerException ce = new ControllerException("619","Something went wrong in controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Employee>>(listOfAllEmps, HttpStatus.OK) ;
		
	
		}
	
	 

	@PutMapping("/update")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee emp){
		
		
		try {
			Employee employee= employeeService.updateEmployee(emp);
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
			
		}catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			ControllerException ce = new ControllerException("619","Something went wrong in controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}
	 
	@DeleteMapping("/delete/{empId}")
	public ResponseEntity<?> deleteEmployee(@PathVariable int empId){
		
		String message;
		try {
			message= employeeService.deletEmployee(empId);
		}catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			ControllerException ce = new ControllerException("619","Something went wrong in controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(message,HttpStatus.OK);
		
	}
	 

	@GetMapping("/city/{city}")
	public ResponseEntity<?> getEmployeeByCity(@PathVariable String city){
		List<Employee> listOfEmps;
		
		try {
			listOfEmps = employeeService.getEmployeeByCity(city);
			
		}catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			ControllerException ce = new ControllerException("619","Something went wrong in controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<List<Employee>>(listOfEmps,HttpStatus.OK);
	}
	
	@GetMapping("/sort")
	public ResponseEntity<?> getSortedEmployeeByLastName(){
		List<Employee> listOfEmps;
		
		try {
			listOfEmps = employeeService.sortEmployeesByLastName();
			
		}catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			ControllerException ce = new ControllerException("620","Something went wrong in controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<List<Employee>>(listOfEmps,HttpStatus.OK);
	}

}
