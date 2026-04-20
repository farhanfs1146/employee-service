package com.example.employee_service.scheduler;

import com.example.employee_service.schedulerservice.BirthdaySchedulerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j // for logs.
public class BirthdayNotificationJob implements Job {

    private final BirthdaySchedulerService birthdaySchedulerService;

    // Role of this file is
    // What should run when time arrives!
    // Quartz will automatically call -> execute() method.
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("Birthday notification job started");
        birthdaySchedulerService.processTodayBirthdays();
        log.info("Birthday notification job finished");
    }
}
