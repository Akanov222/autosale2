package com.demo.autosale2.dto;

import com.demo.autosale2.dao.CarType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

//POST, PUT
public record CarRequest(

    @NotBlank(message = "Brand can not be blank!")
    String brand,

    @NotBlank(message = "Model can not be blank!")
    String model,

    @NotNull(message = "Year can not be null!")
    Integer year,

    @Enumerated(EnumType.STRING)
    CarType type,

    @PositiveOrZero
    BigDecimal price,

    // Опциональные поля для специфичных характеристик
    Double trunkCapacity,
    Double loadCapacity,
    Double seatingCapacity
) {
}