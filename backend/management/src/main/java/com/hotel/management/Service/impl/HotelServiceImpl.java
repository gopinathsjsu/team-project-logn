package com.hotel.management.Service.impl;

import com.hotel.management.Model.Hotel;
import com.hotel.management.Repository.HotelRepository;
import com.hotel.management.Repository.RoomRepository;
import com.hotel.management.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class HotelServiceImpl implements HotelService {

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    RoomRepository roomRepository;

    @Override
    public ResponseEntity<Hotel> addHotel(Hotel hotel) {
        Hotel result = hotelRepository.save(hotel);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Hotel>> searchHotels(String query) {
        List<Hotel> hotels = new ArrayList<>();

        List<Hotel> temp = hotelRepository.searchHotelsByCity(query);
        List<Hotel> temp1 = hotelRepository.searchHotelsByName(query);

        if(temp!=null){
            hotels.addAll(temp);
        }
        if(temp1!=null){
            hotels.addAll(temp1);
        }

        return new ResponseEntity<>(hotels,HttpStatus.OK);
    }


    @Override
    public Hotel getHotelById(long id) {
        return hotelRepository.findById(id).orElse(null);
    }
}
