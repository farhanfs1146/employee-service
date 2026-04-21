package com.example.employee_service.schedulerservice;

import com.example.employee_service.client.NotificationClient;
import com.example.employee_service.repsitory.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class BirthdaySchedulerService {

    // Role of service
    // What business logic should happen

    // Meaning:
    // fetch today birthdays
    // loop employees
    // call notification service
    // This is actual business workflow.

    private final EmployeeRepository employeeRepository;
    private final NotificationClient notificationClient;

    public void processTodayBirthdays() {
        var today = LocalDate.now();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();

        var employees = employeeRepository.findTodayBirthdays(month, day);

        if (employees.isEmpty()) {
            log.info("No birthdays found for today");
            return;
        }

        for (var employee : employees) {
            try {
                notificationClient.sendBirthdayWish(
                        employee.getEmail(),
                        employee.getFirstName() + " " + employee.getLastName()
                );
                log.info("Birthday notification sent for employeeId={}", employee.getId());

            } catch (RuntimeException e) {
                log.error("Failed to send birthday notification for {}", employee.getId(), e);
            }
        }
    }
}
