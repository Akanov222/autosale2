package com.demo.autosale2.dao;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("MINIVAN")
public class Minivan extends Car {

    @Column(name = "seating_capacity")
    private Double seatingCapacity;
}
