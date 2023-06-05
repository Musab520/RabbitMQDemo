package com.example.producerapi;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/produce")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class ProduceMessage {
    private final RabbitTemplate rabbitTemplate;
    private final String queueName = "BOB";
    @PostMapping
    public ResponseEntity<String> produce(@Valid @RequestBody String body) {
        Message message = new Message(body);
        //Send to Queue
        rabbitTemplate.convertAndSend(queueName, message.getBody());
        return ResponseEntity.ok().body("Message Sent: " + message.getBody());
    }
}
