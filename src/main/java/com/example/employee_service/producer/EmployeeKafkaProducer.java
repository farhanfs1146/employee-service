package com.example.employee_service.producer;

import com.example.employee_service.event.EmployeeEvent;
import com.example.employee_service.event.EventType;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeKafkaProducer {

    private final KafkaTemplate<String, EmployeeEvent> kafkaTemplate;

    public void sendEmployeeCreatedEvent(EmployeeEvent event) {
        event.setEventType(EventType.CREATE);
        kafkaTemplate.send("employee-events", event);
    }

    public void sendEmployeeUpdateEvent(EmployeeEvent event) {
        event.setEventType(EventType.UPDATE);
        kafkaTemplate.send("employee-events", event);
    }

    public void sendEmployeeInActiveEvent(EmployeeEvent event) {
        event.setEventType(EventType.INACTIVE);
        kafkaTemplate.send("employee-events", event);
    }

    public void sendEmployeeDeletedEvent(EmployeeEvent event) {
        event.setEventType(EventType.DELETE);
        kafkaTemplate.send("employee-events", event);
    }
}
