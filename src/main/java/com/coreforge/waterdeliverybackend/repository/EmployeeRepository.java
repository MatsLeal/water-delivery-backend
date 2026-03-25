package com.coreforge.waterdeliverybackend.repository;

import com.coreforge.waterdeliverybackend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByRud(String rud);
}