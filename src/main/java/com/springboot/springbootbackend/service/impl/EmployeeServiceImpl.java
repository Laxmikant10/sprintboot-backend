package com.springboot.springbootbackend.service.impl;

import com.springboot.springbootbackend.exception.ResourceNotFoundException;
import com.springboot.springbootbackend.model.Employee;
import com.springboot.springbootbackend.repository.EmployeeRepository;
import com.springboot.springbootbackend.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
        //Old Approach
        /*
        Optional<Employee> employee =  employeeRepository.findById(id);

        if(employee.isPresent()){
            return employee.get();
        }
        else{
            throw new ResourceNotFoundException("Employee","Id",id);
        }
        */

        //Using java 8 feature
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        //we need to check whether employee with given id is existed in DB or not
        Employee exsitingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));

        exsitingEmployee.setFirstName(employee.getFirstName());
        exsitingEmployee.setLastName(employee.getLastName());
        exsitingEmployee.setEmail(employee.getEmail());

        //Save the existing Employee to DB
        employeeRepository.save(exsitingEmployee);

        return exsitingEmployee;

    }

    @Override
    public void deleteEmployee(long id) {
        //Check whether a Employee exist in DB or not
        employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));

        employeeRepository.deleteById(id);
    }
}
