package com.example.employee_service.scheduler;

import com.example.employee_service.job.BirthdayNotificationJob;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BirthdayScheduler {

    private final Scheduler scheduler;

    @PostConstruct
    public void init() throws SchedulerException {

        JobDetail jobDetail = JobBuilder.newJob(BirthdayNotificationJob.class)
                .withIdentity("birthday-job", "employee-jobs")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("birthday-trigger", "employee-jobs")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("0 */5 * * * ?") // every mint
                        // second minute hour day month dayOfWeek
                        // 0      */1    *    *    *      ?
                        // like:
                        // run at second 0
                        // every 1 minute
                        // every hour
                        // every month
                        // every day

                )
                .build();

        scheduler.scheduleJob(jobDetail, trigger);

        log.info("Birthday scheduler initialized successfully");
    }
}