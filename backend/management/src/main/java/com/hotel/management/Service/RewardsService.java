package com.hotel.management.Service;

import com.hotel.management.Model.Rewards;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RewardsService {

    public ResponseEntity<String> enrollCustomer(Rewards rewards);
    public ResponseEntity<String> updateRewards(Rewards rewards);
    public Rewards getRewardsByCustId(long id);

    ResponseEntity<List<Rewards>> getAllRewardsForCustomer(String id);

}
