package com.example.employee_service.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EmployeeRequest(

        @NotBlank(message = "First name is required")
        @Size(max = 100, message = "First name must not exceed 100 characters")
        @Schema(description = "Employee first name", example = "Farhan")
        String firstName,

        @NotBlank(message = "Last name is required")
        @Size(max = 100, message = "Last name must not exceed 100 characters")
        @Schema(description = "Employee last name", example = "Ali")
        String lastName,

        @NotBlank(message = "Father name is required")
        @Size(max = 150, message = "Father name must not exceed 150 characters")
        @Schema(description = "Employee father name", example = "Tariq Ali")
        String fatherName,

        @NotBlank(message = "Mother name is required")
        @Size(max = 150, message = "Mother name must not exceed 150 characters")
        @Schema(description = "Employee mother name", example = "Ammi G")
        String motherName,

        @NotNull(message = "Qualification ID is required")
        @Schema(description = "Master Data Qualification ID", example = "1")
        Long qualificationId,

        @NotNull(message = "Employee Status Required")
        @Schema(description = "Employee status", example = "true")
        Boolean isActive

) {
}

