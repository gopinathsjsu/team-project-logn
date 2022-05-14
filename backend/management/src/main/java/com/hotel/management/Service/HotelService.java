package com.hotel.management.Service;

import com.hotel.management.Model.Hotel;
import com.hotel.management.Model.Rooms;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HotelService {

    public ResponseEntity<Hotel> addHotel(Hotel hotel);

    public ResponseEntity<List<Hotel>> searchHotels(String query);

    public Hotel getHotelById(long id);

    public ResponseEntity<List<Hotel>> searchAllHotel();
    public ResponseEntity<List<Rooms>> getRoomsbyHotelId(String id);

}
