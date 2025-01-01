package com.hms.service;

import com.hms.payload.PropertyDto;
import com.hms.entity.Property;
import com.hms.entity.Country;
import com.hms.entity.City;
import com.hms.repository.CityRepository;
import com.hms.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private CityRepository cityRepository;

    public PropertyDto createProperty(PropertyDto propertyDto) {
        Property property = new Property();
        property.setName(propertyDto.getName());
        property.setNo_of_guest(propertyDto.getNoOfGuest());
        property.setNo_of_rooms(propertyDto.getNoOfrooms());
        property.setNo_of_bathrooms(propertyDto.getNoOfBathrooms());
        property.setNo_of_beds(propertyDto.getNoOfBeds());

        // Assuming that Country and City entities have been loaded via ID from DB
        Country country = new Country();
        country.setId(propertyDto.getCountryId());
        property.setCountry(country);

        City city = new City();
        city.setId(propertyDto.getCityId());
        property.setCity(city);

        Property savedProperty = propertyRepository.save(property);
        propertyDto.setId(savedProperty.getId());
        return propertyDto;
    }

    public PropertyDto getPropertyById(Long id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found"));
        return mapToDto(property);
    }

    public List<PropertyDto> getAllProperties() {
        List<Property> properties = propertyRepository.findAll();
        return properties.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public PropertyDto updateProperty(Long id, PropertyDto propertyDto) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found"));
        property.setName(propertyDto.getName());
        property.setNo_of_guest(propertyDto.getNoOfGuest());
        property.setNo_of_rooms(propertyDto.getNoOfrooms());
        property.setNo_of_bathrooms(propertyDto.getNoOfBathrooms());
        property.setNo_of_beds(propertyDto.getNoOfBeds());

        Country country = new Country();
        country.setId(propertyDto.getCountryId());
        property.setCountry(country);

        City city = new City();
        city.setId(propertyDto.getCityId());
        property.setCity(city);

        Property updatedProperty = propertyRepository.save(property);
        return mapToDto(updatedProperty);
    }
 @Transactional
    public void deleteProperty(Long id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found"));
        propertyRepository.delete(property);
    }

    private PropertyDto mapToDto(Property property) {
        PropertyDto propertyDto = new PropertyDto();
        propertyDto.setId(property.getId());
        propertyDto.setName(property.getName());
        propertyDto.setNoOfGuest(property.getNo_of_guest());
        propertyDto.setNoOfBathrooms(property.getNo_of_bathrooms());
        propertyDto.setNoOfrooms(property.getNo_of_rooms());
        propertyDto.setNoOfBeds(property.getNo_of_beds());
        propertyDto.setCountryId(property.getCountry().getId());
        propertyDto.setCityId(property.getCity().getId());
        return propertyDto;
    }

    public List<PropertyDto> searchHotels(String name) {
        List<Property> properties = propertyRepository.searchHotels(name);

        // Convert the list of Property entities to a list of PropertyDto objects
        return properties.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private PropertyDto convertToDto(Property property) {
        PropertyDto propertyDto = new PropertyDto();

        propertyDto.setId(property.getId());
        propertyDto.setName(property.getName());
        propertyDto.setNoOfGuest(property.getNo_of_guest());
        propertyDto.setNoOfrooms(property.getNo_of_rooms()); // Correct mapping
        propertyDto.setNoOfBathrooms(property.getNo_of_bathrooms()); // Correct mapping
        propertyDto.setNoOfBeds(property.getNo_of_beds());

        // Map country and city ID
        if (property.getCountry() != null) {
            propertyDto.setCountryId(property.getCountry().getId());
        }
        if (property.getCity() != null) {
            propertyDto.setCityId(property.getCity().getId());
        }

        return propertyDto;
    }
}