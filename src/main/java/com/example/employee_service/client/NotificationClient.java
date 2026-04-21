package com.example.employee_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "notification-service", url = "http://localhost:8380")
public interface NotificationClient {

    @PostMapping("/notifications/birthday")
    void sendBirthdayWish(
            @RequestParam String email,
            @RequestParam String employeeName
    );
}
