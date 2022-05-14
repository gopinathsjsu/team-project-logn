package com.hotel.management.Service.impl;

import com.hotel.management.Model.Booking;
import com.hotel.management.Model.RoomBooked;
import com.hotel.management.Model.Rooms;
import com.hotel.management.Repository.BookingRepository;
import com.hotel.management.Repository.HotelRepository;
import com.hotel.management.Repository.RoomBookedRepository;
import com.hotel.management.Repository.RoomRepository;
import com.hotel.management.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    RoomBookedRepository roomBookedRepository;

    @Autowired
    RoomRepository roomRepository;

    @Override
    public ResponseEntity<Booking> createBooking(Booking booking) {
        Booking res = bookingRepository.save(booking);
        Booking new_res = bookingRepository.findById(res.getId()).orElse(null);

        return new ResponseEntity<>(new_res, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> cancelBooking(long id) {
        Booking booking = bookingRepository.findById(id).orElse(null);

        if(booking!=null && booking.isCancelled()==false){
            List<RoomBooked> roomBookedList = booking.getRoomBookedList();
            for(RoomBooked rb: roomBookedList){
                long roomBookedId = rb.getId();
                RoomBooked temp = roomBookedRepository.getById(roomBookedId);
                System.out.println("room booked -> "+temp );

                Rooms tempRoom = roomRepository.getById(temp.getRooms().getId());
                tempRoom.setNumberOfRooms(tempRoom.getNumberOfRooms()+1);

                roomRepository.saveAndFlush(tempRoom);
            }

            booking.setCancelled(true);
            bookingRepository.saveAndFlush(booking);
        }else {
            return new ResponseEntity<>("Booking already cancelled", HttpStatus.BAD_REQUEST);
        }

        //TO-Do for null throw error

        return new ResponseEntity<>("success",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Booking>> getBookingsByHotelId(long id) {
//        System.out.println(hotelRepository.findById(id));
        return new ResponseEntity<>(bookingRepository.findBookingByHotel_Id(id),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Booking>> getBookingsByCustId(long id) {
        return new ResponseEntity<>(bookingRepository.findBookingByCustId(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Booking> getBookingsById(long id) {
        return new ResponseEntity<>(bookingRepository.findById(id).orElse(null), HttpStatus.OK);
    }
}
