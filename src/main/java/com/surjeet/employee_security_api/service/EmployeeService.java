package com.surjeet.employee_security_api.service;

import com.surjeet.employee_security_api.dto.EmployeeRequestDto;
import com.surjeet.employee_security_api.dto.EmployeeResponseDto;

import java.util.List;

public interface EmployeeService {

    EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto);

    List<EmployeeResponseDto> getAllEmployees();

    EmployeeResponseDto getEmployeeById(Long id);

    EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto employeeRequestDto);

    void deleteEmployee(Long id);
}