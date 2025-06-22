package com.employeemanagement.controller;

import com.employeemanagement.dto.EmployeeDto;
import com.employeemanagement.exception.EmailExitsException;
import com.employeemanagement.model.Employee;
import com.employeemanagement.repo.EmployeeRepo;
import com.employeemanagement.service.impl.EmployeeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/employee")
@Slf4j
public class EmployeeController {

    private final EmployeeServiceImpl service;
    private final EmployeeRepo employeeRepo;

    public EmployeeController(EmployeeServiceImpl service, EmployeeRepo employeeRepo) {
        this.service = service;
        this.employeeRepo = employeeRepo;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("employee", service.getAllEmployee());
        return "home";
    }

    @GetMapping("/add")
    public String ShowAddEmployee(Model model) {

        model.addAttribute("employee", new EmployeeDto());
        return "add";
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public String addEmployee(@ModelAttribute EmployeeDto employeeDto, Model model) {
        try {
            if (employeeRepo.existsByEmail(employeeDto.getEmail())) {
                throw new EmailExitsException("Email already exits!");
            }
            UUID userId = UUID.randomUUID();
            employeeDto.setId(userId);
            service.createEmployee(employeeDto);
            return "redirect:/api/employee/viewAll?message=Employee+added+successfully";
        } catch (Exception e) {
            e.printStackTrace();
            //log.error("Exception {}", e.getMessage());
            return "add";
        }
    }

    @GetMapping("/update/{id}")
    public String showUpdateView(@PathVariable UUID id, Model model) {
        model.addAttribute("employee", service.viewEmployeeById(id));
        return "update";
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateEmployee(@ModelAttribute EmployeeDto employeeDto) {
        service.updateEmployee(employeeDto);
        return "redirect:/api/employee/viewAll?message=Employee+updated+successfully";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteEmployee(@PathVariable UUID id) {
        service.deleteEmployees(id);
        return "redirect:/api/employee/viewAll?message=Employee+deleted+successfully";
    }

    @GetMapping("/view/{id}")
    @PreAuthorize("hasRole('USER')")
    public String viewEmployeeById(@PathVariable UUID id, Model model) {
        model.addAttribute("employee", service.viewEmployeeById(id));
        return "view";
    }

    @GetMapping("/viewAll")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String viewAllEmployees(Model model, @RequestParam(value = "message", required = false) String message) {
        List<Employee> employees = service.getAllEmployee();
        model.addAttribute("employees", employees);
        model.addAttribute("message", message);
        return "view"; // Will load viewAll.jsp
    }
}
