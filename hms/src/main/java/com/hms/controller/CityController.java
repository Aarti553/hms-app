package com.hms.controller;

import com.hms.payload.CityDto;
import com.hms.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    // 1. Get all cities
    @GetMapping
    public ResponseEntity<List<CityDto>> getAllCities() {
        List<CityDto> cities = cityService.getAllCities();
        return ResponseEntity.ok(cities);
    }

    // 2. Get city by ID
    @GetMapping("/{id}")
    public ResponseEntity<CityDto> getCityById(@PathVariable Long id) {
        CityDto cityDto = cityService.getCityById(id);
        return ResponseEntity.ok(cityDto);
    }

    // 3. Create a new city ("/api/cities")
    @PostMapping
    public ResponseEntity<CityDto> createCity(@RequestBody CityDto cityDto) {
        CityDto savedCity = cityService.createCity(cityDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCity);
    }

    // 4. Update a city
    @PutMapping("/{id}")
    public ResponseEntity<CityDto> updateCity(@PathVariable Long id, @RequestBody CityDto cityDto) {
        CityDto updatedCity = cityService.updateCity(id, cityDto);
        return ResponseEntity.ok(updatedCity);
    }

    // 5. Delete a city
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
        return ResponseEntity.noContent().build();
    }
}