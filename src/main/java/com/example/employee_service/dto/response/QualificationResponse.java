package com.example.employee_service.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class QualificationResponse {

    private Integer id;
    private String code;
    private String name;
    //    private String description;
    private Boolean isActive;
}
