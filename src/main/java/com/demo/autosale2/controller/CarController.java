package com.demo.autosale2.controller;

import com.demo.autosale2.dao.*;
import com.demo.autosale2.dto.CarRequest;
import com.demo.autosale2.dto.CarResponse;
import com.demo.autosale2.repository.CarRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@Tag(name = "Car API", description = "Car dealership management")
public class CarController {

    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Operation(summary = "Get all cars")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    @GetMapping
    public List<CarResponse> getAllCars() {
        return carRepository.findAll().stream()
                .map(CarResponse::fromCar)
                .toList();
    }

    @Operation(summary = "Get cars by type")
    @ApiResponse(responseCode = "200", description = "List cars by type")
    @GetMapping("/type/{type}")
    public List<CarResponse> getCarsByType(@PathVariable CarType type) {
        return carRepository.findByType(type).stream()
                .map(CarResponse::fromCar)
                .toList();
    }

    @Operation(summary = "Get cars by id")
    @ApiResponse(responseCode = "200", description = "List cars by id")
    @ApiResponse(responseCode = "404", description = "Car is not found")
    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> getOneCarById(@PathVariable Long id) {
        return carRepository.findById(id)
                .map(CarResponse::fromCar)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add new car")
    @ApiResponse(responseCode = "200", description = "Car is added")
    @ApiResponse(responseCode = "400", description = "Uncorrected data")
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

    @Operation(summary = "Update car info")
    @ApiResponse(responseCode = "200", description = "Car is updated")
    @ApiResponse(responseCode = "400", description = "Uncorrected data")
    @ApiResponse(responseCode = "404", description = "Car is not found")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCar(@PathVariable Long id, @Valid @RequestBody CarRequest request,
                                       BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
        }
        return carRepository.findById(id).map(car -> {
            car.setBrand(request.brand());
            car.setModel(request.model());
            car.setYear(request.year());
            car.setType(request.type());
            car.setPrice(request.price());

            if (car instanceof Sedan && request.trunkCapacity() != null) {
                ((Sedan) car).setTrunkCapacity(request.trunkCapacity());
            } else if (car instanceof Truck && request.loadCapacity() != null) {
                ((Truck) car).setLoadCapacity(request.loadCapacity());
            } else if (car instanceof Minivan && request.seatingCapacity() != null) {
                ((Minivan) car).setSeatingCapacity(request.seatingCapacity());
            }

            carRepository.save(car);
            return ResponseEntity.ok("Car is updated");
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete car by id")
    @ApiResponse(responseCode = "200", description = "Car is deleted")
    @ApiResponse(responseCode = "400", description = "Uncorrected data")
    @ApiResponse(responseCode = "404", description = "Car is not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCarById(@PathVariable Long id) {
        carRepository.deleteById(id);
        return ResponseEntity.ok("Car ID:" + id + " is deleted!");
    }
}
