package com.employeemanagement.service;

import com.employeemanagement.dto.EmployeeDto;
import com.employeemanagement.model.Employee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeService {

    void createEmployee(EmployeeDto employeeDto);

    void updateEmployee(EmployeeDto employeeDto);

    void deleteEmployees(UUID id);

    Employee viewEmployeeById(UUID id);

    List<Employee> getAllEmployee();
}
