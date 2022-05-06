package com.hotel.management.Service.impl;

import com.hotel.management.Model.Booking;
import com.hotel.management.Repository.BookingRepository;
import com.hotel.management.Repository.HotelRepository;
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

    @Override
    public ResponseEntity<Booking> createBooking(Booking booking) {
        Booking res = bookingRepository.save(booking);
        Booking new_res = bookingRepository.findById(res.getId()).orElse(null);

        return new ResponseEntity<>(new_res, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> cancelBooking(long id) {
        Booking booking = bookingRepository.findById(id).orElse(null);

        if(booking!=null){
            booking.setCancelled(true);
        }
        bookingRepository.save(booking);

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
