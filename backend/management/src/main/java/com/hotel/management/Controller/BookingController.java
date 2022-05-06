package com.hotel.management.Controller;

import com.hotel.management.Model.*;
import com.hotel.management.Service.*;
import com.hotel.management.dto.AmenitiesDto;
import com.hotel.management.dto.BookingCreateDto;
import com.hotel.management.dto.RoomBookedDto;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;

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



    @RequestMapping("/create")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingCreateDto bookingCreateDto){
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
            room.setNumberOfRooms(room.getNumberOfRooms()-1);
            roomService.updateRoom(room);

            roomBookedList.add(roomBooked);

            //save
            roomBookedService.addRoomBooked(roomBooked);

        }
        // set roomsbooked
        booking.setRoomBookedList(roomBookedList);

        // To-Do Update rewards points
        // now only do subtract loyalty bonus if user is using loyaly bonus to pay for total bill.
        Rewards rewards = rewardsService.getRewardsByCustId(customer.getId());
        double bonusUsed = bookingCreateDto.getBonusUsed();
        double loyaltyBonus = rewards.getLoyaltyBonus();
        loyaltyBonus = loyaltyBonus + booking.getTotalBill()*10 - bonusUsed;
        rewards.setLoyaltyBonus(loyaltyBonus);
        rewardsService.updateRewards(rewards);

        return bookingService.createBooking(booking);//.toString();
    }

    @RequestMapping("/cancel/{id}")
    public ResponseEntity<String> cancelBooking(@PathVariable long id){
        return bookingService.cancelBooking(id);
    }

    @GetMapping("/getall/hotel/{id}")
    public ResponseEntity<List<Booking>> getAllBookingsByHotel(@PathVariable long id){
        System.out.println("hotel id->"+ id);
        return bookingService.getBookingsByHotelId(id);
//                return null;
    }

    @GetMapping("/getall/customer/{id}")
    public ResponseEntity<List<Booking>> getAllBookingsByCustomer(@PathVariable long id){
        return bookingService.getBookingsByCustId(id);
//        return null;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable long id){
        return bookingService.getBookingsById(id);
    }


}
