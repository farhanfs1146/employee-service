package com.example.employee_service.events;

import com.example.employee_service.dto.event.EmployeeCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeKafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendEmployeeCreatedEvent(EmployeeCreatedEvent event) {
        kafkaTemplate.send("employee-events", event);
    }
}
