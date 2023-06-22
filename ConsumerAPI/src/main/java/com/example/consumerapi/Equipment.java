package com.example.consumerapi;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String equipmentName;
    private String equipmentType;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String status;
    private boolean underMaintenance;
    private BigDecimal currentMileage;
    private double fuelPercentage;
    private Date equipmentTime = new Date();
}
