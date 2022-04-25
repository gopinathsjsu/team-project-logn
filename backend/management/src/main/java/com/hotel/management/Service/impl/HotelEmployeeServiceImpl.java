package com.hotel.management.Service.impl;

import com.hotel.management.Model.HotelEmployee;
import com.hotel.management.Repository.HotelEmployeeRepository;
import com.hotel.management.Service.HotelEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class HotelEmployeeServiceImpl implements HotelEmployeeService {

    @Autowired
    HotelEmployeeRepository hotelEmployeeRepository;

    @Override
    public HotelEmployee addEmployee(HotelEmployee employee) {
        hotelEmployeeRepository.save(employee);
        return employee;
    }
}
