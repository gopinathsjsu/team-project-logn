package com.hotel.management.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class RoomBookedDto {

    private long roomId;
    private double rate;

    private AmenitiesDto amenitiesDto;

}
