package com.hotel.management.Service.impl;

import com.hotel.management.Model.*;
import com.hotel.management.Repository.BookingRepository;
import com.hotel.management.Repository.HotelRepository;
import com.hotel.management.Service.*;
import com.hotel.management.dto.AmenitiesDto;
import com.hotel.management.dto.BookingCreateDto;
import com.hotel.management.dto.RoomBookedDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    HotelService hotelService;

    @Autowired
    RoomBookedService roomBookedService;

    @Autowired
    RoomService roomService;

    @Autowired
    RewardsService rewardsService;

    @Override
    public ResponseEntity<Booking> createBooking(BookingCreateDto bookingCreateDto) {

        // create booking
        // update loyalty bonus
        // subtract rooms

        System.out.println("book->"+bookingCreateDto);
        Booking booking = new Booking();

        Customer customer = customerService.getCustomerById(bookingCreateDto.getCustId());
        Hotel hotel = hotelService.getHotelById(bookingCreateDto.getHotelId());

        booking.setHotel(hotel);
        booking.setCustomer(customer);

        booking.setStayFrom(bookingCreateDto.getStayFrom());
        booking.setStayUpto(bookingCreateDto.getStayUpto());
        booking.setTotalBill(bookingCreateDto.getTotalBill());


        booking.setNumberOfGuests(bookingCreateDto.getNumberOfGuests());

        List<RoomBooked> roomBookedList = new ArrayList<>();
        for(RoomBookedDto roomBookedDto: bookingCreateDto.getRoomBookedDtos()){

            // create ameneties for each room
            AmenitiesDto temp = roomBookedDto.getAmenitiesDto();
            Amenities amenities = new Amenities();
            amenities.setDinner(temp.isDinner());
            amenities.setDailyBreakfast(temp.isDailyBreakfast());
            amenities.setLunch(temp.isLunch());
            amenities.setSwimmingPool(temp.isSwimmingPool());
            amenities.setGym(temp.isGym());
            amenities.setParking(temp.isParking());
            amenities.setAllMeals(temp.isAllMeals());

            // create room
            RoomBooked roomBooked = new RoomBooked();
            //set amenities
            roomBooked.setAmenities(amenities);

            //get room
            Rooms room = roomService.getRoomById(roomBookedDto.getRoomId());
            //set room
            roomBooked.setRooms(room);
            //set rate
            roomBooked.setRate(roomBookedDto.getRate());

            //update room
//            room.setNumberOfRooms(room.getNumberOfRooms()-1);
//            roomService.updateRoom(room);

            roomBookedList.add(roomBooked);

            //save
            roomBookedService.addRoomBooked(roomBooked);

        }
        // set roomsbooked
        booking.setRoomBookedList(roomBookedList);

        // To-Do Update rewards points
        // now only do subtract loyalty bonus if user is using loyaly bonus to pay for total bill.
        Rewards rewards = rewardsService.getRewardsByCustId(customer.getId());
        if(rewards==null){
//            Hotel hotelTemp = hotelService.getHotelById(id);
//            Customer customerTemp = customerService.getCustomerById(custid);

            Rewards obj = new Rewards();
            obj.setCustomer(customer);
            obj.setHotel(hotel);
            obj.setLoyaltyBonus(5000);
            rewardsService.enrollCustomer(obj);
        }else {
            double bonusUsed = bookingCreateDto.getBonusUsed();
            double loyaltyBonus = rewards.getLoyaltyBonus();
            loyaltyBonus = loyaltyBonus + booking.getTotalBill()*10 - bonusUsed;
            rewards.setLoyaltyBonus(loyaltyBonus);
            rewardsService.updateRewards(rewards);
        }

        Booking res = bookingRepository.save(booking);
        Booking new_res = bookingRepository.findById(res.getId()).orElse(null);

        return new ResponseEntity<>(new_res, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> cancelBooking(long id) {
        Booking booking = bookingRepository.findById(id).orElse(null);

        if(booking!=null){
            booking.setCancelled(true);
        }
        bookingRepository.save(booking);

        //TO-Do for null throw error

        return new ResponseEntity<>("success",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Booking>> getBookingsByHotelId(long id) {
//        System.out.println(hotelRepository.findById(id));
        return new ResponseEntity<>(bookingRepository.findBookingByHotel_Id(id),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Booking>> getBookingsByCustId(long id) {
        return new ResponseEntity<>(bookingRepository.findBookingByCustId(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Booking> getBookingsById(long id) {
        return new ResponseEntity<>(bookingRepository.findById(id).orElse(null), HttpStatus.OK);
    }
}
