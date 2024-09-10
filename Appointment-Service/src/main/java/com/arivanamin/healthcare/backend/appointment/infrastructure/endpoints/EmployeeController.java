package com.arivanamin.healthcare.backend.appointment.infrastructure.endpoints;

import com.arivanamin.healthcare.backend.core.domain.aspects.LogExecutionTime;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Builder;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class EmployeeController {
    
    @GetMapping ("protected/appointments/employee/all")
    @Operation (summary = "Get list of all customer transactions")
    @ResponseStatus (HttpStatus.OK)
    @LogExecutionTime
    public List<Employee> getAllEmployees () {
        
        log.info("received request for getAllEmployees");
        log.error("received request for getAllEmployees error");
        
        List<Employee> employees = new ArrayList<>();
        employees.add(Employee.builder().name("employee 1").build());
        employees.add(Employee.builder().name("employee 2").build());
        employees.add(Employee.builder().name("employee 3").build());
        return employees;
    }
}

@Value
@Builder
class Employee {
    
    String name;
}
