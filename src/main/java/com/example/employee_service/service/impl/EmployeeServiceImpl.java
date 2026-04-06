package com.example.employee_service.service.impl;

import com.example.employee_service.dto.event.EmployeeCreatedEvent;
import com.example.employee_service.dto.request.EmployeeRequest;
import com.example.employee_service.dto.response.EmployeeResponse;
import com.example.employee_service.events.EmployeeKafkaProducer;
import com.example.employee_service.exception.ValidationException;
import com.example.employee_service.mapper.EmployeeMapper;
import com.example.employee_service.repsitory.EmployeeRepository;
import com.example.employee_service.service.EmployeeService;
import com.example.employee_service.service.MasterDataValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final MasterDataValidationService masterDataValidationService;

    // event working
    private final EmployeeKafkaProducer employeeKafkaProducer;


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

        if (employeeRepository.existsByFirstName(employeeRequest.firstName())) {
            throw new ValidationException("Employee with the same first name already exists");
        }
        // validate the qualification exist by id in master data.
        masterDataValidationService.validateDepartmentById(employeeRequest.qualificationId());

        var newEmployee = employeeMapper.toEntity(employeeRequest);
        employeeRepository.save(newEmployee);

        // create & fire event.
        // 🔥 EVENT FIRE KARO
        EmployeeCreatedEvent event = new EmployeeCreatedEvent(
                newEmployee.getId(),
                newEmployee.getFirstName()
        );

        employeeKafkaProducer.sendEmployeeCreatedEvent(event);
        log.info("🔥 EVENT Sent to Consumer: {}", event);
        return employeeMapper.toEmployeeResponse(newEmployee);
    }

    @Override
    public EmployeeResponse updateEmployee(Long id, EmployeeRequest employeeRequest) {
        return null;
    }
}
