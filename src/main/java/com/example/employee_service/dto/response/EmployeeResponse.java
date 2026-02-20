package com.example.employee_service.dto.response;

public record EmployeeResponse(

        Long id,

        String firstName,

        String lastName,

        String fatherName,

        String motherName,

        Long qualificationId,

        Boolean isActive

) {

}