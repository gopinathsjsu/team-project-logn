package com.hotel.management.Service;

import com.hotel.management.Model.Hotel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HotelService {

    public ResponseEntity<Hotel> addHotel(Hotel hotel);

    public ResponseEntity<List<Hotel>> searchHotels(String query);

    public Hotel getHotelById(long id);
}
