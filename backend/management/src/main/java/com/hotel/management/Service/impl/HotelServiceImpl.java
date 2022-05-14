package com.hotel.management.Service.impl;

import com.hotel.management.Model.Booking;
import com.hotel.management.Model.Hotel;
import com.hotel.management.Model.RoomBooked;
import com.hotel.management.Model.Rooms;
import com.hotel.management.Repository.BookingRepository;
import com.hotel.management.Repository.HotelRepository;
import com.hotel.management.Repository.RoomRepository;
import com.hotel.management.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class HotelServiceImpl implements HotelService {

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    BookingRepository bookingRepository;

//    @Autowired


    @Override
    public ResponseEntity<Hotel> addHotel(Hotel hotel) {
        Hotel result = hotelRepository.save(hotel);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Hotel>> searchHotels(String query,String startTime,String endTime) {
        List<Hotel> hotels = new ArrayList<>();
        List<Hotel> searchHotelList = new ArrayList<>();

        Long userStartDate = Long.valueOf(startTime);
        Long userEndDate = Long.valueOf(endTime);


        List<Hotel> hotelByCity = hotelRepository.searchHotelsByCity(query);
        List<Hotel> hotelByname = hotelRepository.searchHotelsByName(query);

        if(hotelByCity!=null){
            hotels.addAll(hotelByCity);
        }
        if(hotelByname!=null){
            hotels.addAll(hotelByname);
        }
        if(hotels.size()>0){

            for(Hotel hotel: hotelByCity){
                Hotel temp = new Hotel();

                List<Booking> bookingList = bookingRepository.findBookingByHotel_Id(hotel.getId());
                Map<Long, Integer> roomIds = new HashMap<>();

                for(Booking booking: bookingList){
                    List<RoomBooked> roomBookedList = booking.getRoomBookedList();
                    Long startFrom = Long.valueOf(booking.getStayFrom());
                    Long stayUpto = Long.valueOf(booking.getStayUpto());

                    if((userStartDate>=startFrom && userStartDate<=stayUpto) ||
                            (userEndDate>=startFrom && userEndDate<=stayUpto) ||
                            (userStartDate<=startFrom && userEndDate>=stayUpto)
                    ){
                        for(RoomBooked rb: roomBookedList){
                            Rooms rooms = rb.getRooms();
                            roomIds.put(rooms.getId(), roomIds.getOrDefault(rooms.getId(),0)+1);
                        }
                    }
                }


                temp.setId(hotel.getId());
                temp.setName(hotel.getName());
                temp.setMobile(hotel.getMobile());
                temp.setCity(hotel.getCity());
                temp.setState(hotel.getState());
                temp.setCountry(hotel.getCountry());

                temp.setAddress(hotel.getAddress());
                temp.setHolidayMultiplier(hotel.getHolidayMultiplier());
                temp.setSeasonalMulitplier(hotel.getSeasonalMulitplier());

                List<Rooms> roomsList = hotel.getRooms();
                List<Rooms> newRooms = new ArrayList<>();
                for(Rooms r: roomsList){
                    Rooms obj = new Rooms();
                    obj.setHotel(hotel);
                    obj.setType(r.getType());
                    obj.setRate(r.getRate());
                    obj.setId(r.getId());
                    obj.setNumberOfRooms(r.getNumberOfRooms()-roomIds.getOrDefault(r.getId(),0));
                    newRooms.add(obj);
                }
                temp.setRooms(newRooms);


                searchHotelList.add(temp);

            }
        }


        return new ResponseEntity<>(searchHotelList,HttpStatus.OK);
//        return new ResponseEntity<>(hotels,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Hotel>> searchAllHotel(){
            return new ResponseEntity<>(hotelRepository.findAll(),HttpStatus.OK);
    }

    @Override
    public Hotel getHotelById(long id) {
        return hotelRepository.findById(id).orElse(null);
    }
    public ResponseEntity<List<Rooms>> getRoomsbyHotelId(String id){
        return new ResponseEntity<>(roomRepository.getRoomByHotelId(id),HttpStatus.OK);
    }
}
