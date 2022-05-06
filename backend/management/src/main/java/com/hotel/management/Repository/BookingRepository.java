package com.hotel.management.Repository;

import com.hotel.management.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

//    @Query(value = "select * from booking where hotel_id=?1", nativeQuery = true)
//    List<Booking> findBookingByHotelId(long id);

    List<Booking> findBookingByHotel_Id(long id);

    @Query(value = "select * from booking where cust_id=?1", nativeQuery = true)
    List<Booking> findBookingByCustId(long id);
}
