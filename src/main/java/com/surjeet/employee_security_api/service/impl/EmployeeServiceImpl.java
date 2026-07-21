package com.surjeet.employee_security_api.service.impl;

import com.surjeet.employee_security_api.dto.EmployeeRequestDto;
import com.surjeet.employee_security_api.dto.EmployeeResponseDto;
import com.surjeet.employee_security_api.entity.Employee;
import com.surjeet.employee_security_api.exception.ResourceNotFoundException;
import com.surjeet.employee_security_api.repository.EmployeeRepository;
import com.surjeet.employee_security_api.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public EmployeeResponseDto createEmployee(EmployeeRequestDto dto) {

        Employee employee = new Employee();
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());
        employee.setSalary(dto.getSalary());

        Employee savedEmployee = employeeRepository.save(employee);

        return mapToResponse(savedEmployee);
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<EmployeeResponseDto> getAllEmployees() {

        return employeeRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public EmployeeResponseDto getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id : " + id));

        return mapToResponse(employee);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto dto) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id : " + id));

        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());
        employee.setSalary(dto.getSalary());

        Employee updatedEmployee = employeeRepository.save(employee);

        return mapToResponse(updatedEmployee);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id : " + id));

        employeeRepository.delete(employee);
    }

    private EmployeeResponseDto mapToResponse(Employee employee) {

        EmployeeResponseDto response = new EmployeeResponseDto();

        response.setId(employee.getId());
        response.setFirstName(employee.getFirstName());
        response.setLastName(employee.getLastName());
        response.setEmail(employee.getEmail());
        response.setDepartment(employee.getDepartment());
        response.setSalary(employee.getSalary());
        response.setCreatedAt(employee.getCreatedAt());
        response.setUpdatedAt(employee.getUpdatedAt());

        return response;
    }
}