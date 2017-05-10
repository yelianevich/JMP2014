package com.epam.controller;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.model.Employee;
import com.epam.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = GET)
    public Iterable<Employee> showEmployees() {
        Iterable<Employee> employees = employeeService.findAll();
        return employees;
    }

    @RequestMapping(value = "/{id}", method = GET)
    public Employee showEmployeeById(@PathVariable("id") Long id) {
        Employee employee = employeeService.findOne(id);
        return employee;
    }

    @RequestMapping(method = POST)
    public Employee createEmployee(@RequestBody Employee employee) {
        employee = employeeService.save(employee);
        return employee;
    }

    @RequestMapping(method = PUT)
    public Employee updateEmployee(@RequestBody Employee employee) {
        employee = employeeService.save(employee);
        return employee;
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
    }

    @RequestMapping(params="find=ByName", method = GET)
    public List<Employee> findEMployeeByName(@RequestParam("name") String name) {
        List<Employee> employees = employeeService.findByName(name);
        return employees;
    }

}
