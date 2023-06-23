package com.example.consumerapi;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consume")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ConsumeMessage {
    private final AmqpTemplate rabbitTemplate;
    private final String queueName = "BOB2";
    @GetMapping
    public ResponseEntity<String> consume() {
        //Send to Queue
        Message message = rabbitTemplate.receive(queueName);
        return ResponseEntity.ok().body("Message Recieved: " + message.toString());
    }
}
