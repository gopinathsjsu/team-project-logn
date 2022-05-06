package com.hotel.management.Service.impl;

import com.hotel.management.Model.Rewards;
import com.hotel.management.Repository.RewardsRepository;
import com.hotel.management.Service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RewardsServiceImpl implements RewardsService {

    @Autowired
    RewardsRepository rewardsRepository;

    @Override
    public ResponseEntity<String> enrollCustomer(Rewards rewards) {
        rewardsRepository.save(rewards);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateRewards(Rewards rewards) {
        rewardsRepository.save(rewards);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @Override
    public Rewards getRewardsByCustId(long id) {
        Rewards rewards = rewardsRepository.findByCustId(id); //.orElse(null);
        return rewards;
    }
}
