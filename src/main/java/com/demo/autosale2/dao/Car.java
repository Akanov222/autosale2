package com.demo.autosale2.dao;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "car_type")
public abstract class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Brand can not be blank!")
    @Column(nullable = false)
    private String brand;

    @NotBlank(message = "Model can not be blank!")
    @Column(nullable = false)
    private String model;

    @NotNull(message = "Year can not be blank!")
    @Column(nullable = false)
    private Integer year;

    @Enumerated(EnumType.STRING)
    private CarType type;

    @PositiveOrZero(message = "Price must be positive or zero!")
    @Column(nullable = false)
    private BigDecimal price;
}
