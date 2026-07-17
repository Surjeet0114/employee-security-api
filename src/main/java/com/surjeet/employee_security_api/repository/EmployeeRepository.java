package com.surjeet.employee_security_api.repository;

import com.surjeet.employee_security_api.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
