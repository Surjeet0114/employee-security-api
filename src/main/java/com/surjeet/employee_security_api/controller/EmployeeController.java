package com.surjeet.employee_security_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @GetMapping("/api/employees")
    public String getEmployees() {
        return "JWT Authentication Successful!";
    }
}
