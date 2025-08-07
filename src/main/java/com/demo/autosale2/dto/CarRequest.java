package com.demo.autosale2.dto;

import com.demo.autosale2.dao.CarType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

//POST, PUT
public record CarRequest(

    @NotBlank(message = "Brand can not be blank!")
    String brand,

    @NotBlank(message = "Model can not be blank!")
    String model,

    @NotBlank(message = "Year can not be blank!")
    Integer year,

    @NotBlank(message = "Type can not be blank!")
    CarType type,

    @PositiveOrZero
    BigDecimal price,

    // Опциональные поля для специфичных характеристик
    Double trunkCapacity,
    Double loadCapacity,
    Double seatingCapacity
) {
}