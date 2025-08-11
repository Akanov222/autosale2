package com.demo.autosale2.controller;

import com.demo.autosale2.dao.*;
import com.demo.autosale2.dto.CarRequest;
import com.demo.autosale2.dto.CarResponse;
import com.demo.autosale2.repository.CarRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.demo.autosale2.dao.CarType.*;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping
    public List<CarResponse> getAllCars() {
        return carRepository.findAll().stream()
                .map(CarResponse::fromCar)
                .toList();
    }

    @GetMapping("/type/{type}")
    public List<CarResponse> getCarsByType(@PathVariable CarType type) {
        return carRepository.findByType(type).stream()
                .map(CarResponse::fromCar)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> getOneCarById(@PathVariable Long id) {
        return carRepository.findById(id)
                .map(CarResponse::fromCar)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> addCar(@Valid @RequestBody CarRequest request) {
        Car car = switch(request.type()) {
            case SEDAN -> {
                Sedan sedan = new Sedan();
                sedan.setTrunkCapacity(request.trunkCapacity());
                yield sedan;
            }
            case TRUCK -> {
                Truck truck = new Truck();
                truck.setLoadCapacity(request.loadCapacity());
                yield truck;
            }
            case MINIVAN -> {
                Minivan minivan = new Minivan();
                minivan.setSeatingCapacity(request.seatingCapacity());
                yield minivan;
            }
        };

        car.setBrand(request.brand());
        car.setModel(request.model());
        car.setYear(request.year());
        car.setType(request.type());
        car.setPrice(request.price());

        carRepository.save(car);
        return ResponseEntity.ok("Car is included!");
    }
}
