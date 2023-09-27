package com.seleniumexpress.employeeapp.repositories;

import com.seleniumexpress.employeeapp.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
