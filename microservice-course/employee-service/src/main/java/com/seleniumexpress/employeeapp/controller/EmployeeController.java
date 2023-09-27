package com.seleniumexpress.employeeapp.controller;

import com.seleniumexpress.employeeapp.dtos.EmployeeDTO;
import com.seleniumexpress.employeeapp.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeDetails(@PathVariable("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployeeDetails(id));
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDTO>> getEmployees() {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployees());
    }

    @PostMapping("/employees")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createEmployee(employee));
    }
}
