package com.hotel.management.Controller;

import com.hotel.management.Model.Hotel;
import com.hotel.management.Model.HotelEmployee;
import com.hotel.management.Model.Rooms;
import com.hotel.management.Service.HotelService;
import com.hotel.management.dto.HotelRequestDto;
import com.hotel.management.dto.RoomDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

//    @Autowired
//    HotelEmployeeService hotelEmployeeService;

    @Autowired
    HotelService hotelService;

    @RequestMapping("/")
    public String showHome(){
        return "hello-2";
    }

//    @PostMapping("/signup")
//    public String addEmployee(@RequestBody HotelEmployee employee){
//
//        System.out.println("->"+employee);
//
//        String password = employee.getPassword();
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//        password = encoder.encode(password);
//        employee.setPassword(password);
//
//        hotelEmployeeService.addEmployee(employee);
//
//        return "Success";
//    }

    //add hotel
    @PostMapping("/add")
    public ResponseEntity<Hotel> addHotel(@RequestBody HotelRequestDto hotelRequestDto){

        System.out.println(hotelRequestDto);

        Hotel hotel = new Hotel();
        hotel.setName(hotelRequestDto.getName());
        hotel.setMobile(hotelRequestDto.getMobile());
        hotel.setAddress(hotelRequestDto.getAddress());
        hotel.setCity(hotelRequestDto.getCity());
        hotel.setCountry(hotelRequestDto.getCountry());
        hotel.setState(hotelRequestDto.getState());
        hotel.setHolidayMultiplier(hotelRequestDto.getHolidayMultiplier());
        hotel.setSeasonalMulitplier(hotelRequestDto.getSeasonalMulitplier());

        List<Rooms> roomsList = new ArrayList<>();
        for(RoomDto dto: hotelRequestDto.getRoomDtoList()){
            Rooms room = new Rooms();
            room.setNumberOfRooms(dto.getNumberOfRooms());
            room.setRate(dto.getRate());
            room.setType(dto.getType());
            room.setHotel(hotel);
            roomsList.add(room);
        }
        hotel.setRooms(roomsList);

        System.out.println("hotel obj->"+hotel);

        return hotelService.addHotel(hotel);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Hotel>> searchHotels(@RequestParam String query){
        return hotelService.searchHotels(query);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable long id){
        Hotel hotel = hotelService.getHotelById(id);
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }



}
