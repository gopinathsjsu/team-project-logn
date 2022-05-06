package com.hotel.management.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter @NoArgsConstructor @ToString
public class BookingCreateDto {
    private long custId;
    private long hotelId;

    private String stayFrom;
    private String StayUpto;

    private double totalBill;
    private int numberOfGuests;

    private double bonusUsed;

    private List<RoomBookedDto> roomBookedDtos;

}
