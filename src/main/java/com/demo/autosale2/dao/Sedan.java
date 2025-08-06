package com.demo.autosale2.dao;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("SEDAN")
public class Sedan extends Car{

    private Double trunkCapacity;

}
