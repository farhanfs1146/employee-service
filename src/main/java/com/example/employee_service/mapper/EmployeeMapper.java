package com.example.employee_service.mapper;


import com.example.employee_service.dto.request.EmployeeRequest;
import com.example.employee_service.dto.response.EmployeeResponse;
import com.example.employee_service.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    // Employee DTO request to Employee Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt",  ignore = true)
    @Mapping(target = "updatedAt",  ignore = true)
    @Mapping(target = "createdBy",  ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    Employee toEntity(EmployeeRequest employeeRequest);

    // Employee Entity to Employee DTO response
    EmployeeResponse toEmployeeResponse(Employee employee);
}
