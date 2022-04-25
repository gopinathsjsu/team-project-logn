package com.hotel.management.Repository;

import com.hotel.management.Model.HotelEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelEmployeeRepository extends JpaRepository<HotelEmployee, Long> {
}
