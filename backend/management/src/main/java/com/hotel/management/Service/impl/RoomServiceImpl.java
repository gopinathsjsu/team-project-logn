package com.hotel.management.Service.impl;

import com.hotel.management.Model.Rooms;
import com.hotel.management.Repository.RoomRepository;
import com.hotel.management.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomRepository roomRepository;

    @Override
    public Rooms getRoomById(long id){
        return roomRepository.findById(id).orElse(null);
    }

    @Override
    public Rooms updateRoom(Rooms room) {
        return roomRepository.save(room);
    }


}
