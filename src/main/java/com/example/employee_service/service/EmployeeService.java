package com.example.employee_service.service;

import com.example.employee_service.dto.request.EmployeeRequest;
import com.example.employee_service.dto.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {

    public List<EmployeeResponse> getAllEmployees();

    public EmployeeResponse getEmployeeById(Long id);

    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest);

    public EmployeeResponse updateEmployee(Long id, EmployeeRequest employeeRequest);

}
