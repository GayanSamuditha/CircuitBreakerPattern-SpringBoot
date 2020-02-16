package com.gayan.demo.service;

import com.gayan.demo.hystrix.AllocationCommand;
import com.gayan.demo.model.Employee;
import com.gayan.demo.model.Telephone;
import com.gayan.demo.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HttpHeaders httpHeaders;

    /*@Value("#{systemProperties['priority']}")
    private String spelValue;
    We can also use SpEL expressions to get the value. If we have a system property named priority,
    then its value will be applied to the field
    */

    @Override
    public Employee save(Employee employee) {
        for (Telephone telephone:employee.getTelephones()) {
            telephone.setEmployee(employee);
        }

        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(Integer employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);

        if(employee.isPresent())
            return employee.get();
        return new Employee();
    }

    public Employee fetchAllocation(Integer id) {
        Employee employee = this.findById(id);
        AllocationCommand allocationCommand = new AllocationCommand(employee, httpHeaders, restTemplate);
        employee.setAllocations(Arrays.asList(allocationCommand.execute()));

        return employee;
    }


}
