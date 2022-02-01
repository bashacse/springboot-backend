package com.example.springboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.model.Employee;
import com.example.springboot.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	Logger logger=LoggerFactory.getLogger(EmployeeRestController.class);
	
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
	
	// expose "/employees" and return list of employees
	@GetMapping("/employees")
	public List<Employee> findAll() {
		logger.info("employee details listed");
		return employeeService.findAll();
	}

	// add mapping for GET /employees/{employeeId}
	
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) 
	{
		
		Employee theEmployee = employeeService.findById(employeeId);
		if(theEmployee!=null){
			logger.info("Employee found : {}",theEmployee);
			return theEmployee;
		}else{
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Employee Not Found with ID : {}",employeeId);
			}
			return new Employee();
		}
		
	}
	
	// add mapping for POST /employees - add new employee
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {
		
		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update
		
		theEmployee.setId(0);
		
		employeeService.save(theEmployee);
		logger.info("Employee details inserted : {}",theEmployee);
		return theEmployee;
	}
	
	// add mapping for PUT /employees - update existing employee
	
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {
		
		employeeService.save(theEmployee);
		logger.info("Employee details updated : {}",theEmployee);
		return theEmployee;
	}
	
	// add mapping for DELETE /employees/{employeeId} - delete employee
	
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		
		Employee tempEmployee = employeeService.findById(employeeId);
		
		if(tempEmployee!=null){
			logger.info("Employee found : {}",tempEmployee);
			employeeService.deleteById(employeeId);
			logger.info("Employee deleted : {}",tempEmployee);
			return "Employee deleted with  id - " + employeeId;
		}else{
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Employee Not Found with ID : {}",employeeId);
			}
			return "Employee Not Found with ID - " + employeeId;
		}
	}	
}