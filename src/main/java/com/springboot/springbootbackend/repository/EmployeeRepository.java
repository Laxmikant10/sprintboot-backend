package com.springboot.springbootbackend.repository;

import com.springboot.springbootbackend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
