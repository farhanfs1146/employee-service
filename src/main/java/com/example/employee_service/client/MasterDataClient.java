package com.example.employee_service.client;

import com.example.employee_service.dto.ApiResponse;
import com.example.employee_service.dto.response.QualificationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "master-data-service", url = "http://localhost:8180")
public interface MasterDataClient {
    
    @GetMapping("/api/qualifications/{id}")
    ApiResponse<QualificationResponse> getQualificationById(@PathVariable Long id);
}
