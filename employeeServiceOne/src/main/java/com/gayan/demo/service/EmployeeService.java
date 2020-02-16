package com.gayan.demo.service;

import com.gayan.demo.model.Allocation;
import com.gayan.demo.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee save(Employee employee);

    List<Employee> getAllEmployees();

    Employee findById(Integer employeeId);

    Employee fetchAllocation(Integer id);

}
