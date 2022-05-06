package com.hotel.management.Repository;

import com.hotel.management.Model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query(value ="select * from hotel where lower(city)=?1",nativeQuery = true)
    public List<Hotel> searchHotelsByCity(String city);

    @Query(value ="select * from hotel where lower(name)=?1",nativeQuery = true)
    public List<Hotel> searchHotelsByName(String name);

}
