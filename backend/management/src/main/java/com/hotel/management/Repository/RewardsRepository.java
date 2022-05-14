package com.hotel.management.Repository;

import com.hotel.management.Model.Rewards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardsRepository extends JpaRepository<Rewards, Long> {

    @Query(value = "select * from rewards where cust_id=?1",nativeQuery = true)
    public Rewards findByCustId(long id);

    List<Rewards> findAllByCustomer_Id(long id);
}
