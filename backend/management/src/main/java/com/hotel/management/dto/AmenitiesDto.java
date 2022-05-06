package com.hotel.management.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class AmenitiesDto {
    private boolean dailyBreakfast;
    private boolean lunch;
    private boolean dinner;
    private boolean swimmingPool;
    private boolean parking;
    private boolean gym;
    private boolean allMeals;
}
