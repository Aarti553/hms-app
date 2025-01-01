package com.hms.controller;

import com.hms.payload.PropertyDto;
import com.hms.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

//    public PropertyController(PropertyService propertyService) {
//        this.propertyService = propertyService;
//    }

    @PostMapping
    public ResponseEntity<PropertyDto> createProperty(
            @RequestBody PropertyDto propertyDto) {
        PropertyDto createdProperty = propertyService.createProperty(propertyDto);
        return new ResponseEntity<>(createdProperty, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyDto> getPropertyById(
            @PathVariable Long id) {
        PropertyDto propertyDto = propertyService.getPropertyById(id);
        return new ResponseEntity<>(propertyDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PropertyDto>> getAllProperties() {
        List<PropertyDto> properties = propertyService.getAllProperties();
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropertyDto> updateProperty(
            @PathVariable Long id,
            @RequestBody PropertyDto propertyDto) {
        PropertyDto updatedProperty = propertyService.updateProperty(id, propertyDto);
        return new ResponseEntity<>(updatedProperty, HttpStatus.OK);
    }
@Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProperty(
            @PathVariable Long id) {
        propertyService.deleteProperty(id);
        return new ResponseEntity<>("Property deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/search-hotels")
    public ResponseEntity<List<PropertyDto>> searchHotels(
            @RequestParam String name
    ) {
        List<PropertyDto> properties = propertyService.searchHotels(name);
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }
}