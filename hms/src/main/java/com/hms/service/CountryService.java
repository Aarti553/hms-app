package com.hms.service;

import com.hms.entity.Country;
import com.hms.payload.CountryDto;
import com.hms.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    // Convert Entity to DTO
    private CountryDto convertToDto(Country country) {
        CountryDto dto = new CountryDto();
        dto.setId(country.getId());
        dto.setName(country.getName());
        return dto;
    }

    // Convert DTO to Entity
    private Country convertToEntity(CountryDto dto) {
        Country country = new Country();
        country.setId(dto.getId());
        country.setName(dto.getName());
        return country;
    }

    // 1. Get all countries
    public List<CountryDto> getAllCountries() {
        List<Country> countries = countryRepository.findAll();
        return countries.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // 2. Get a country by ID
    public CountryDto getCountryById(Long id) {
        Optional<Country> countryOptional = countryRepository.findById(id);
        return countryOptional.map(this::convertToDto).orElse(null);
    }

    // 3. Create a new country
    public CountryDto createCountry(CountryDto countryDto) {
        Country country = convertToEntity(countryDto);
        Country savedCountry = countryRepository.save(country);
        return convertToDto(savedCountry);
    }

    // 4. Update a country
    public CountryDto updateCountry(Long id, CountryDto countryDto) {
        Optional<Country> countryOptional = countryRepository.findById(id);
        if (countryOptional.isPresent()) {
            Country country = countryOptional.get();
            country.setName(countryDto.getName());
            Country updatedCountry = countryRepository.save(country);
            return convertToDto(updatedCountry);
        }
        return null;
    }

    // 5. Delete a country
    @Transactional
    public boolean deleteCountry(Long id) {
        Optional<Country> countryOptional = countryRepository.findById(id);
        if (countryOptional.isPresent()) {
            countryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

