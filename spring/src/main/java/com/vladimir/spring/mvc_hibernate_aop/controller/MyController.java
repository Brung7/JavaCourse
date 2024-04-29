package com.vladimir.spring.mvc_hibernate_aop.controller;

import com.vladimir.spring.mvc_hibernate_aop.entity.Employee;
import com.vladimir.spring.mvc_hibernate_aop.service.EmployeeService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MyController {

    private final EmployeeService employeeService;

    public MyController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping("/")
    public String showAllEmployees(Model model){
        List<Employee> employeeList = employeeService.getAllEmployees();
        model.addAttribute("allEmps", employeeList);
        return "all-employees";
    }
    @RequestMapping("/addNewEmp")
    public String showAddEmp(Model model){

        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "details-of-employee";
    }
    @RequestMapping("/saveEmployee")
    public String SaveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }
    @RequestMapping("/updateInfo")
    public String updateEmployee(@RequestParam("empId") int id, Model model){

        Employee employee = employeeService.getEmployee(id);
        model.addAttribute("employee", employee);

        return "details-of-employee";

    }
    @RequestMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam("empId") int id){

        employeeService.deleteEmployee(id);
        return "redirect:/";
    }

}
