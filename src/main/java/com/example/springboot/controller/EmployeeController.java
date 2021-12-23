package com.example.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.springboot.ResourceNotFoundException;
import com.example.springboot.model.Employee;
import com.example.springboot.repository.EmployeeRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://192.168.1.108","https://192.168.1.108"})
@RestController
@RequestMapping("/api")
public class EmployeeController {

	Logger logger = LoggerFactory.getLogger(EmployeeRepository.class);
	
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }


    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
    	logger.info("in  getAllEmployees method");
        return ResponseEntity.ok(employeeRepository.findAll());
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Integer employeeId)
            throws ResourceNotFoundException {
    	logger.info("in  getEmployeeById method");
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Integer employeeId,
                                                   @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
    	logger.info("in  updateEmployee method");
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employee.setName(employeeDetails.getName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setPhone(employeeDetails.getPhone());
        employee.setDepartment(employeeDetails.getDepartment());

        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Integer employeeId)
            throws ResourceNotFoundException {
    	logger.info("in  deleteEmployee method");
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

