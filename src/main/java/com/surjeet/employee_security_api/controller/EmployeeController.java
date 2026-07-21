package com.surjeet.employee_security_api.controller;

import com.surjeet.employee_security_api.dto.EmployeeRequestDto;
import com.surjeet.employee_security_api.dto.EmployeeResponseDto;
import com.surjeet.employee_security_api.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDto> createEmployee(
            @Valid @RequestBody EmployeeRequestDto employeeRequestDto) {

        EmployeeResponseDto employee =
                employeeService.createEmployee(employeeRequestDto);

        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees() {

        List<EmployeeResponseDto> employees =
                employeeService.getAllEmployees();

        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(
            @PathVariable Long id) {

        EmployeeResponseDto employee =
                employeeService.getEmployeeById(id);

        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequestDto employeeRequestDto) {

        EmployeeResponseDto employee =
                employeeService.updateEmployee(id, employeeRequestDto);

        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(
            @PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return ResponseEntity.noContent().build();
    }

}