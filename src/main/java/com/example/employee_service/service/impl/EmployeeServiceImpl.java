package com.example.employee_service.service.impl;

import com.example.employee_service.dto.request.EmployeeRequest;
import com.example.employee_service.dto.response.EmployeeResponse;
import com.example.employee_service.mapper.EmployeeMapper;
import com.example.employee_service.repsitory.EmployeeRepository;
import com.example.employee_service.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        return List.of();
    }

    @Override
    public EmployeeResponse getEmployeeById(Long id) {
        var employee = employeeRepository.findById(id).orElseThrow(()-> new RuntimeException("Employee does not exist with id " + id));
        return employeeMapper.toEmployeeResponse(employee);
    }

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        var newEmployee = employeeMapper.toEntity(employeeRequest);
        employeeRepository.save(newEmployee);
        return employeeMapper.toEmployeeResponse(newEmployee);
    }

    @Override
    public EmployeeResponse updateEmployee(Long id, EmployeeRequest employeeRequest) {
        return null;
    }
}
