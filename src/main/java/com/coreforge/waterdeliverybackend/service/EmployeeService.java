package com.coreforge.waterdeliverybackend.service;

import com.coreforge.waterdeliverybackend.exception.ResourceNotFoundException;
import com.coreforge.waterdeliverybackend.model.Employee;
import com.coreforge.waterdeliverybackend.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getByRud(String rud) {
        return employeeRepository.findByRud(rud)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with RUD: " + rud));
    }
}