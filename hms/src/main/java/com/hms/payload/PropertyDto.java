package com.hms.payload;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyDto {
    private Long id;
    private String name;
    private Integer noOfGuest;
    private Integer noOfrooms;
    private Integer noOfBathrooms;
    private Integer noOfBeds;
    private Long countryId;
    private Long cityId;
}

