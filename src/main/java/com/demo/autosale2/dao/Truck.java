package com.demo.autosale2.dao;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("TRUCK")
public class Truck extends Car {

    @Column(name = "load_capacity")
    private Double loadCapacity;
}
