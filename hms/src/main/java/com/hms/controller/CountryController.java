
package com.hms.controller;
import com.hms.payload.CountryDto;
import com.hms.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    // 1. Get all countries
    @GetMapping
    public ResponseEntity<List<CountryDto>> getAllCountries() {
        List<CountryDto> countries = countryService.getAllCountries();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    // 2. Get a country by ID
    @GetMapping("/{id}")
    public ResponseEntity<CountryDto> getCountryById(@PathVariable Long id) {
        CountryDto country = countryService.getCountryById(id);
        if (country != null) {
            return new ResponseEntity<>(country, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 3. Create a new country
    @PostMapping
    public ResponseEntity<CountryDto> createCountry(@RequestBody CountryDto countryDto) {
        CountryDto newCountry = countryService.createCountry(countryDto);
        return new ResponseEntity<>(newCountry, HttpStatus.CREATED);
    }

    // 4. Update a country
    @PutMapping("/{id}")
    public ResponseEntity<CountryDto> updateCountry(@PathVariable Long id, @RequestBody CountryDto countryDto) {
        CountryDto updatedCountry = countryService.updateCountry(id, countryDto);
        if (updatedCountry != null) {
            return new ResponseEntity<>(updatedCountry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 5. Delete a country
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        boolean isDeleted = countryService.deleteCountry(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
