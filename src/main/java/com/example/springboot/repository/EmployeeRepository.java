package com.example.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.model.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

	//That's it, no need to write any code(No need of implementation class,it's the beauty of spring data jpa)
}
