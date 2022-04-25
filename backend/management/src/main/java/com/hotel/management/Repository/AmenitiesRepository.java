package com.hotel.management.Repository;

import com.hotel.management.Model.Amenities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmenitiesRepository extends JpaRepository<Amenities, Long> {
}
