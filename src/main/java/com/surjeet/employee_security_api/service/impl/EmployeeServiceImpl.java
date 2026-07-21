package com.surjeet.employee_security_api.service.impl;

import com.surjeet.employee_security_api.dto.EmployeeRequestDto;
import com.surjeet.employee_security_api.dto.EmployeeResponseDto;
import com.surjeet.employee_security_api.entity.Employee;
import com.surjeet.employee_security_api.exception.ResourceNotFoundException;
import com.surjeet.employee_security_api.repository.EmployeeRepository;
import com.surjeet.employee_security_api.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto) {

        Employee employee = new Employee();

        employee.setFirstName(employeeRequestDto.getFirstName());
        employee.setLastName(employeeRequestDto.getLastName());
        employee.setEmail(employeeRequestDto.getEmail());
        employee.setDepartment(employeeRequestDto.getDepartment());
        employee.setSalary(employeeRequestDto.getSalary());

        Employee savedEmployee = employeeRepository.save(employee);

        return mapToResponse(savedEmployee);
    }

    @Override
    public List<EmployeeResponseDto> getAllEmployees() {

        List<Employee> employees = employeeRepository.findAll();

        return employees.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeResponseDto getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id : " + id));

        return mapToResponse(employee);
    }

    @Override
    public EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto employeeRequestDto) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id : " + id));

        employee.setFirstName(employeeRequestDto.getFirstName());
        employee.setLastName(employeeRequestDto.getLastName());
        employee.setEmail(employeeRequestDto.getEmail());
        employee.setDepartment(employeeRequestDto.getDepartment());
        employee.setSalary(employeeRequestDto.getSalary());

        Employee updatedEmployee = employeeRepository.save(employee);

        return mapToResponse(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id : " + id));

        employeeRepository.delete(employee);
    }

    /**
     * Converts Employee Entity to EmployeeResponseDto
     */
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
