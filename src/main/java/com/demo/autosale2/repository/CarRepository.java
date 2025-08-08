package com.demo.autosale2.repository;

import com.demo.autosale2.dao.Car;
import com.demo.autosale2.dao.CarType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByType(CarType type);
}
