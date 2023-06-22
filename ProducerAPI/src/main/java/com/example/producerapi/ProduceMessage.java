package com.example.producerapi;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/produce")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class ProduceMessage {
    private final RabbitTemplate rabbitTemplate;
    private final String queueName = "BOB";
    @PostMapping
    public ResponseEntity<Equipment> produce() {
        Equipment equipment = new Equipment();
        Random random = new Random();

        equipment.setEquipmentName(getRandomElement(getNameList()));
        equipment.setEquipmentType(getRandomElement(getTypeList()));
        equipment.setLongitude(generateRandomBigDecimal());
        equipment.setLatitude(generateRandomBigDecimal());
        equipment.setStatus(getRandomElement(getStatusList()));
        equipment.setUnderMaintenance(random.nextBoolean());
        equipment.setCurrentMileage(generateRandomBigDecimal());
        equipment.setFuelPercentage(random.nextDouble());
        equipment.setEquipmentTime(new Date());
        //Send to Queue
        rabbitTemplate.convertAndSend(queueName, equipment);
        return ResponseEntity.ok().body(equipment);
    }
    private static List<String> getNameList() {
        // Define your list of names
        return Arrays.asList("CAT", "Komatsu", "Volvo Construction", "JCB");
    }

    private static List<String> getTypeList() {
        // Define your list of types
        return Arrays.asList("Bulldozer", "Excavator", "Cement Machine");
    }

    private static List<String> getStatusList() {
        // Define your list of statuses
        return Arrays.asList("ACTIVE", "NON-ACTIVE", "UNDER_MAINTENANCE", "OFF_SITE");
    }

    private static String getRandomElement(List<String> list) {
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }

    private static BigDecimal generateRandomBigDecimal() {
        // Implement your logic to generate a random BigDecimal
        // Example implementation:
        Random random = new Random();
        int scale = 10;
        BigDecimal randomBigDecimal = new BigDecimal(random.nextDouble());
        randomBigDecimal = randomBigDecimal.setScale(scale, BigDecimal.ROUND_HALF_UP);
        return randomBigDecimal;
    }
}
