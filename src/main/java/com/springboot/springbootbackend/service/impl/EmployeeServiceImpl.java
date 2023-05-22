package com.springboot.springbootbackend.service.impl;

import com.springboot.springbootbackend.model.Employee;
import com.springboot.springbootbackend.repository.EmployeeRepository;
import com.springboot.springbootbackend.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}
