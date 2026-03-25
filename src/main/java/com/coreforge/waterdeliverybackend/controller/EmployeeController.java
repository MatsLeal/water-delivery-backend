package com.coreforge.waterdeliverybackend.controller;

import com.coreforge.waterdeliverybackend.dto.CreateEmployeeRequest;
import com.coreforge.waterdeliverybackend.model.Employee;
import com.coreforge.waterdeliverybackend.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public Employee createEmployee(@Valid @RequestBody CreateEmployeeRequest request) {
        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setRud(request.getRud());
        employee.setArea(request.getArea());

        return employeeService.createEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/rud/{rud}")
    public Employee getByRud(@PathVariable String rud) {
        return employeeService.getByRud(rud);
    }
}