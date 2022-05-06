package com.hotel.management.Service;


import com.hotel.management.Model.Rooms;

public interface RoomService  {
    public Rooms getRoomById(long id);
    public Rooms updateRoom(Rooms room);

}
