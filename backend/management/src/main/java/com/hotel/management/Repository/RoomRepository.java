package com.hotel.management.Repository;

import com.hotel.management.Model.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Rooms,Long> {

    @Query(value ="select * from rooms where hotel=?1",nativeQuery = true)
    public List<Rooms> getRoomByHotelId(String id);
}
