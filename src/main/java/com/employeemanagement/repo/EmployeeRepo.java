package com.employeemanagement.repo;

import com.employeemanagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeRepo extends JpaRepository<Employee, UUID> {
    boolean existsByEmail(String email);
}
