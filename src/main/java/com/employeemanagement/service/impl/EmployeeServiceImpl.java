package com.employeemanagement.service.impl;

import com.employeemanagement.dto.EmployeeDto;
import com.employeemanagement.mapper.EmployeeMapper;
import com.employeemanagement.model.Employee;
import com.employeemanagement.repo.EmployeeRepo;
import com.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepo employeeRepo;
    @Autowired
    private EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;

    }

    @Override
    @Transactional
    public void createEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);
        employeeRepo.save(employee);
    }

    @Override
    @Transactional
    public void updateEmployee(EmployeeDto employeeDto) {

        Optional<Employee> existEmployee = employeeRepo.findById(employeeDto.getId());
        if (existEmployee.isPresent()) {
            Employee employee = existEmployee.get();
            employee.setName(employeeDto.getName());
            employee.setDepartment(employeeDto.getDepartment());
            employee.setEmail(employeeDto.getEmail());
            employee.setSalary(employeeDto.getSalary());
            employeeRepo.save(employee);
        }
    }

    @Override
    @Transactional
    public void deleteEmployees(UUID id) {
        Employee empById = viewEmployeeById(id);
        employeeRepo.delete(empById);
    }

    @Override
    @Transactional
    public Employee viewEmployeeById(UUID id) {
        return employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("Id not found:-" + id));
    }

    @Override
    @Transactional
    public List<Employee> getAllEmployee() {
        return employeeRepo.findAll();
    }
}
