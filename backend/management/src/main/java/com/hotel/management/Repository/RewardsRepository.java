package com.hotel.management.Repository;

import com.hotel.management.Model.Rewards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardsRepository extends JpaRepository<Rewards, Long> {
}
