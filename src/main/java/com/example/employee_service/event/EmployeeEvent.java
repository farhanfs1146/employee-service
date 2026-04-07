package com.example.employee_service.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEvent {
    private Long employeeId;
    private String name;
    private EventType eventType;
}