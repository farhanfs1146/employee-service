package com.example.employee_service.service;

import com.example.employee_service.client.MasterDataClient;
import com.example.employee_service.dto.ApiResponse;
import com.example.employee_service.dto.response.QualificationResponse;
import com.example.employee_service.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j // used for purpose of create logs like log.info() etc.
public class MasterDataValidationService {

    private final MasterDataClient masterDataClient;
    
    // validating the qualification id from master data tables using master data service.
    public ApiResponse<QualificationResponse> validateDepartmentById(Long qualificationId) {
        if (qualificationId == null) {
            return null;
        }
        log.debug("Validating department ID: {}", qualificationId);
        var response = masterDataClient.getQualificationById(qualificationId);
        if (response == null || response.getData() == null) {
            throw new ValidationException("Qualification with ID " + qualificationId + " not found");
        }
        if (!response.getData().getIsActive()) {
            throw new ValidationException("Qualification with ID " + qualificationId + " is not active");
        }
        log.info("Qualification validation successful for ID: {}", qualificationId);
        return response;
    }
}
