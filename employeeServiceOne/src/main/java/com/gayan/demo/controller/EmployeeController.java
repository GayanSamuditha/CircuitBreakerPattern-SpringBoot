package com.gayan.demo.controller;

import com.gayan.demo.model.Allocation;
import com.gayan.demo.model.Employee;
import com.gayan.demo.model.Telephone;
import com.gayan.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import javax.transaction.TransactionalException;
import java.util.List;

@RestController
@RequestMapping("/services")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
    public Employee save(@RequestBody Employee employee) {

        return employeeService.save(employee);

    }

    @RequestMapping("/employee/allocation/{id}")
    public Employee getEmployeesAllocation(@PathVariable Integer id) {

        return employeeService.fetchAllocation(id);
    }

//    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
//    public Employee save(@RequestBody Employee employee) {
//
//        return employeeService.save(employee);
//
//    }

    @Transactional(rollbackOn = TransactionalException.class)
    @RequestMapping(value = "/getEmployees", method = RequestMethod.POST)
    public Employee getEmployees(@RequestBody Employee e) {
        e.getTelephones().stream().forEach(t -> {
            t.setEmployee(e);
        });

        Employee employee = employeeService.save(e);
        return employee;
    }
}





