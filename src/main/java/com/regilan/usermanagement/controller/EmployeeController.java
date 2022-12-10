package com.regilan.usermanagement.controller;

import com.regilan.usermanagement.models.Employee;
import com.regilan.usermanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("/api/employee")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/email")
    public ResponseEntity<Employee> getEmployeeByEmail(@RequestParam("email") String emailId) throws Exception {
        Optional<Employee> foundEmployee = employeeRepository.findByEmailId(emailId);
        if (foundEmployee.isPresent()) {
            return ResponseEntity.ok(foundEmployee.get());
        } else {
            throw new Exception("Employee not found");
        }
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(savedEmployee);
    }

    @PutMapping("/{employee_id}/inactivate")
    public ResponseEntity<Employee> inactivateEmployee(@PathVariable("employee_id") Employee employee) {
        employee.setStatus(false);
        Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

}
