package com.hotel.management.Repository;

import com.hotel.management.Model.RoomBooked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomBookedRepository extends JpaRepository<RoomBooked, Long> {
}
