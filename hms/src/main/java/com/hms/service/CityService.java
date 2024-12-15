package com.hms.service;

import com.hms.entity.City;
import com.hms.payload.CityDto;
import com.hms.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    // Convert Entity to DTO
    private CityDto convertToDto(City city) {
        CityDto dto = new CityDto();
        dto.setId(city.getId());
        dto.setName(city.getName());
        return dto;
    }

    // Convert DTO to Entity
    private City convertToEntity(CityDto dto) {
        City city = new City();
        city.setId(dto.getId());
        city.setName(dto.getName());
        return city;
    }

    // 1. Get all cities
    public List<CityDto> getAllCities() {
        List<City> cities = cityRepository.findAll();
        return cities.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // 2. Get a city by ID
    public CityDto getCityById(Long id) {
        Optional<City> cityOptional = cityRepository.findById(id);
        return cityOptional.map(this::convertToDto).orElse(null);
    }

    // 3. Create a new city
    public CityDto createCity(CityDto cityDto) {
        City city = convertToEntity(cityDto);
        City savedCity = cityRepository.save(city);
        return convertToDto(savedCity);
    }

    // 4. Update a city
    public CityDto updateCity(Long id, CityDto cityDto) {
        Optional<City> cityOptional = cityRepository.findById(id);
        if (cityOptional.isPresent()) {
            City city = cityOptional.get();
            city.setName(cityDto.getName());
            City updatedCity = cityRepository.save(city);
            return convertToDto(updatedCity);
        }
        return null;
    }

    // 5. Delete a city
    @Transactional
    public boolean deleteCity(Long id) {
        Optional<City> cityOptional = cityRepository.findById(id);
        if (cityOptional.isPresent()) {
            cityRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

