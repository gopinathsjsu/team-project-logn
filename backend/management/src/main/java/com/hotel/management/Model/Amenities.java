package com.hotel.management.Model;

import lombok.AllArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
public class Amenities {
    public Amenities() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "amt_id")
    private long id;

    private boolean dailyBreakfast;
    private boolean lunch;
    private boolean dinner;
    private boolean swimmingPool;
    private boolean parking;
    private boolean gym;
    private boolean allMeals;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isDailyBreakfast() {
        return dailyBreakfast;
    }

    public void setDailyBreakfast(boolean dailyBreakfast) {
        this.dailyBreakfast = dailyBreakfast;
    }

    public boolean isLunch() {
        return lunch;
    }

    public void setLunch(boolean lunch) {
        this.lunch = lunch;
    }

    public boolean isDinner() {
        return dinner;
    }

    public void setDinner(boolean dinner) {
        this.dinner = dinner;
    }

    public boolean isSwimmingPool() {
        return swimmingPool;
    }

    public void setSwimmingPool(boolean swimmingPool) {
        this.swimmingPool = swimmingPool;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public boolean isGym() {
        return gym;
    }

    public void setGym(boolean gym) {
        this.gym = gym;
    }

    public boolean isAllMeals() {
        return allMeals;
    }

    public void setAllMeals(boolean allMeals) {
        this.allMeals = allMeals;
    }
}
