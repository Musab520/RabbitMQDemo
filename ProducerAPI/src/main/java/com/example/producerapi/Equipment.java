package com.example.producerapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Equipment {
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
