package com.example.consumerapi;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RabbitMQListener {
    private final EquipmentRepository equipmentRepository;
    @RabbitListener(queues = "BOB")
    public void handleMessage(Equipment equipment) {
        if(equipment != null) {
            equipmentRepository.save(equipment);
        }
    }
}

