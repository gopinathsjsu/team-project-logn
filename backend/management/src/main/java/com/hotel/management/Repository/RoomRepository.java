package com.hotel.management.Repository;

import com.hotel.management.Model.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Rooms,Long> {
}
