package com.hotel.management.Service.impl;

import com.hotel.management.Model.RoomBooked;
import com.hotel.management.Repository.RoomBookedRepository;
import com.hotel.management.Service.RoomBookedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoomBookedServiceImpl implements RoomBookedService {

    @Autowired
    RoomBookedRepository roomBookedRepository;

    @Override
    public String addRoomBooked(RoomBooked roomBooked) {
         roomBookedRepository.save(roomBooked);
        return "success"; //new ResponseEntity<String>("success", HttpStatus.OK);
    }
}
