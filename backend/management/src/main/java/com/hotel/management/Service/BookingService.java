package com.hotel.management.Service;

import com.hotel.management.Model.Booking;
import com.hotel.management.dto.BookingCreateDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookingService {
    // create new booking
    public ResponseEntity<Booking> createBooking(BookingCreateDto bookingCreateDto);

    //cancel a booking given id
    public ResponseEntity<String> cancelBooking(long id);

    public ResponseEntity<List<Booking>> getBookingsByHotelId(long id);
    public ResponseEntity<List<Booking>> getBookingsByCustId(long id);

    public ResponseEntity<Booking> getBookingsById(long id);
}
