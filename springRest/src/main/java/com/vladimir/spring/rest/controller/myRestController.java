package com.vladimir.spring.rest.controller;

import com.vladimir.spring.rest.entity.Employee;
import com.vladimir.spring.rest.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class myRestController {

    public final EmployeeService employeeService;

    public myRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/employees")
    public List<Employee> showAllEmployees(){
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees;
    }
    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable("id") int id){

        Employee employee = employeeService.getEmployee(id);
        return employee;

    }


    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee){

       Employee employee1 = employeeService.saveEmployee(employee);
        return employee1;
    }
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){

        Employee employee2 = employeeService.saveEmployee(employee);
        return employee2;
    }
    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable("id")int id ){
        Employee employee = employeeService.getEmployee(id);
        if(employee==null){
            throw new NoSuchElementException("There is no employee with ID: " + id);
        }
    employeeService.deleteEmployee(id);
    return "Employee with id: " + id + "was deleted";

    }
}
