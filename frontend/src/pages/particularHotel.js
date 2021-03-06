import React, { useState, useEffect, StrictMode } from "react";
import Router, { useRouter } from "next/router";
import getWithAuth from "../api/getWithAuth";
import postWithAuth from "../api/postWithAuth";
import Image from "next/image";
import CheckBox from "../components/checkbox";
import moment from "moment";

export default function ParticularHotel() {
  const season = [6, 7, 8, 12];

  const router = useRouter();
  const id = router.query;
  const [hotelDetails, setHotelDetails] = useState([]);
  const [allRooms, setAllRooms] = useState([]);
  const [price, setPrice] = useState(0);
  const [roomType, setRoomType] = useState("");
  const [numberOfRooms, setNumberOfRooms] = useState(0);
  const [roomRate, setRoomRate] = useState(0);
  const [roomTypeChecked, setRoomTypeChecked] = useState(false);

  const [isSeaMul, setIsSeaMul] = useState(false);
  const [isHolMul, setIsHolMul] = useState(false);

  const [allMeals, setAllMeals] = useState(false);
  const [breakfast, setBreakfast] = useState(false);
  const [lunch, setLunch] = useState(false);
  const [dinner, setDinner] = useState(false);
  const [parking, setParking] = useState(false);
  const [gym, setGym] = useState(false);
  const [pool, setPool] = useState(false);

  const [totalPrice, setTotalPrice] = useState(0);
  useEffect(() => {
    let mul = 1;
    if(isHolMul){
      mul = mul * hotelDetails.holidayMultiplier;
    }
    if(isSeaMul){
      mul = mul * hotelDetails.seasonalMulitplier;
    }
    setTotalPrice(price * mul)
  }, [price]);

  const [rewardsData, setRewardsData] = useState([]);
  useEffect(async () => {
    const data = await getWithAuth(
      "/customer/rewards/get?custId=" + localStorage.getItem("user_id")
    );
    const filteredRewards = data.filter(
      (item) => item.hotel.id == hotelDetails.id
    );
    setRewardsData(filteredRewards);
  }, [hotelDetails]);

  useEffect(() => {
    let sdate = localStorage.getItem("startDate");
    let edate = localStorage.getItem("endDate");
    checkWeekend(sdate, edate);
    checkSeason(sdate, edate);
  }, []);

  function getDaysBetweenDates(start, end, dayName) {
    var result = [];
    var days = { sun: 0, mon: 1, tue: 2, wed: 3, thu: 4, fri: 5, sat: 6 };
    var day = days[dayName.toLowerCase().substr(0, 3)];
    // Copy start date
    var current = new Date(start);
    // Shift to next of required days
    current.setDate(current.getDate() + ((day - current.getDay() + 7) % 7));
    // While less than end date, add dates to result array
    while (current < end) {
      result.push(new Date(+current));
      current.setDate(current.getDate() + 7);
    }
    return result;
  }

  const checkSeason = (start, end) => {
    start = moment.unix(start / 1000);
    end = moment.unix(end / 1000);

    if (
      (start.month() >= 5 && start.month() <= 7) ||
      start.month() == 11 ||
      (end.month() >= 5 && end.month() <= 7) ||
      end.month() == 11
    ) {
      setIsSeaMul(true);
    }
  };

  const checkWeekend = (start, end) => {
    if (
      getDaysBetweenDates(
        moment.unix(start / 1000),
        moment.unix(end / 1000),
        "sun"
      ).length > 0
    ) {
      setIsHolMul(true);
    }

    // console.log(start.format("dddd, MMMM Do, YYYY"));
    // console.log(endd.format("dddd, MMMM Do, YYYY"));
    // // while (start != end) {
    //   console.log("STRAT IN: ", start)
    //   if (
    //     moment.unix(start / 1000).day() == 6 ||
    //     moment.unix(start / 1000).day() == 7
    //   ) {
    //     console.log(moment.unix(start / 1000).day())
    //     console.log("BRAEKING")
    //     setIsHolMul(true);
    //     break;
    //   }
    //   start = start + moment().add(86400000, 'milliseconds');
    //   console.log("START: ", start)
    // }
  };

  useEffect(async () => {
    if (id.id != undefined) {
      const data = await getWithAuth("/hotel/get/" + id.id);
      setHotelDetails(data);
      console.log(data);
      const rooms = await getWithAuth("/hotel/roomsbyhotel/" + data.id);
      setAllRooms(rooms);
      console.log(rooms);
    }
  }, [id]);

  const [roomID, setRoomID] = useState(null);

  const getData = (label, rate, id) => {
    setRoomType(label);
    setRoomID(id);
    if (label == "") {
      setRoomRate(0);
    } else {
      setRoomRate(rate);
    }
  };

  useEffect(() => {
    if (roomRate == 0) {
      setPrice(0);
    } else {
      setPrice(roomRate);
    }
  }, [roomRate]);
  useEffect(() => {
    if (parking) {
      setPrice(price + 5);
    } else if (price != 0) {
      setPrice(price - 5);
    }
  }, [parking]);
  useEffect(() => {
    if (gym) {
      setPrice(price + 5);
    } else if (price != 0) {
      setPrice(price - 5);
    }
  }, [gym]);
  useEffect(() => {
    if (pool) {
      setPrice(price + 5);
    } else if (price != 0) {
      setPrice(price - 5);
    }
  }, [pool]);
  useEffect(() => {
    if (dinner) {
      setPrice(price + 5);
    } else if (price != 0) {
      setPrice(price - 5);
    }
  }, [dinner]);
  useEffect(() => {
    if (lunch) {
      setPrice(price + 5);
    } else if (price != 0) {
      setPrice(price - 5);
    }
  }, [lunch]);
  useEffect(() => {
    if (breakfast) {
      setPrice(price + 5);
    } else if (price != 0) {
      setPrice(price - 5);
    }
  }, [breakfast]);
  useEffect(() => {
    if (allMeals) {
      setPrice(price + 5);
    } else if (price != 0) {
      setPrice(price - 5);
    }
  }, [allMeals]);

  const [useRewards, setUseRewards] = useState(false);
  const [bonusUsed, setBonusUsed] = useState(0);
  const [bonusAvailable, setBonusAvailable] = useState(0);

  useEffect(() => {
    if (useRewards) {
      setBonusUsed(bonusAvailable);
      setPrice(price - parseInt(bonusAvailable) / 1000);
    } else {
      setBonusUsed(0);
    }
  }, [useRewards]);

  const handleBooking = () => {
    if (price == 0) {
      return alert("Select room type and Amenities!");
    }
    const data = postWithAuth("/booking/create", {
      body: {
        custId: parseInt(localStorage.getItem("user_id")),
        hotelId: parseInt(id.id),
        stayFrom: localStorage.getItem("startDate"),
        stayUpto: localStorage.getItem("endDate"),
        totalBill: price,
        numberOfGuests: 2,
        bonusUsed: bonusUsed,
        roomBookedDtos: [
          {
            roomId: String(roomID),
            rate: String(roomRate),
            amenitiesDto: {
              dailyBreakfast: breakfast,
              lunch: lunch,
              dinner: dinner,
              swimmingPool: pool,
              parking: parking,
              gym: gym,
              allMeals: allMeals,
            },
          },
        ],
      },
    });
    alert("Booking Complete!");
    router.push("/");
  };

  return (
    <div className="container mt-16">
      <div
        className="w-full flex flex-col justify-between items-center rounded-lg p-4"
        style={{ background: "rgba(255, 255, 255, 0.5)" }}
      >
        <div className="w-full flex justify-between items-start">
          <div>{hotelDetails.name}</div>
          <div>
            <div>Address:</div>
            <div>
              {hotelDetails.address}, {hotelDetails.city}
            </div>
            <div>
              {hotelDetails.state}, {hotelDetails.country}
            </div>
            <div className="flex items-center">
              <div style={{ width: "20px", height: "20px" }}>
                <Image
                  width={20}
                  height={20}
                  src={require("../assets/phone-call-icon.png")}
                />
              </div>
              <div className="ml-2">{hotelDetails.mobile}</div>
            </div>
          </div>
          <div>
            <button
              onClick={handleBooking}
              className="bg-button-color text-white px-8 py-2 rounded-full transition-all hover:shadow-button-shadow"
            >
              Book Now
            </button>
          </div>
        </div>
        <div className="my-4">Total Price: ${totalPrice}</div>
        <div className="flex flex-col items-center">
          <div>Total loyalty points:</div>
          {rewardsData.length == 0
            ? "0"
            : rewardsData.map((item) => {
                return (
                  <div key={item.id} className="flex flex-col items-center">
                    <div>{parseInt(item.loyaltyBonus)}</div>
                    <div>
                      <input
                        onChange={(e) => {
                          setUseRewards(!useRewards);
                          setBonusAvailable(parseInt(item.loyaltyBonus));
                        }}
                        checked={useRewards}
                        type="checkbox"
                        className="mr-2"
                      />
                      Save ${parseInt(item.loyaltyBonus) / 1000}
                    </div>
                  </div>
                );
              })}
        </div>
        <div className="w-full">
          <div>Select room type:</div>
          <div>
            {allRooms.map((item, index) => {
              return (
                <div key={item.id} className="flex w-full justify-between">
                  <div className="w-1/4">{item.type}</div>
                  <div className="w-1/4">{item.numberOfRooms}</div>
                  <div className="w-1/4">${item.rate}</div>
                  <div className="w-1/4">
                    <CheckBox
                      label={item.type}
                      i={index}
                      rate={item.rate}
                      getData={getData}
                      id={item.id}
                    />
                  </div>
                </div>
              );
            })}
          </div>
        </div>
        <div className="my-8 w-full flex flex-col justify-start">
          <div>Select amenities:</div>
          <div className="flex flex-wrap">
            <label className="mx-2">
              <input
                onChange={(e) => setAllMeals(!allMeals)}
                checked={allMeals}
                type="checkbox"
                className="mx-1"
              />
              All meals
            </label>
            <label className="mx-2">
              <input
                onChange={(e) => setBreakfast(!breakfast)}
                checked={breakfast}
                type="checkbox"
                className="mx-1"
              />
              Breakfast
            </label>
            <label className="mx-2">
              <input
                onChange={(e) => setLunch(!lunch)}
                checked={lunch}
                type="checkbox"
                className="mx-1"
              />
              Lunch
            </label>
            <label className="mx-2">
              <input
                onChange={(e) => setDinner(!dinner)}
                checked={dinner}
                type="checkbox"
                className="mx-1"
              />
              Dinner
            </label>
            <label className="mx-2">
              <input
                onChange={(e) => setGym(!gym)}
                checked={gym}
                type="checkbox"
                className="mx-1"
              />
              Gym
            </label>
            <label className="mx-2">
              <input
                onChange={(e) => setParking(!parking)}
                checked={parking}
                type="checkbox"
                className="mx-1"
              />
              Parking
            </label>
            <label className="mx-2">
              <input
                onChange={(e) => setPool(!pool)}
                checked={pool}
                type="checkbox"
                className="mx-1"
              />
              Swimming pool
            </label>
          </div>
        </div>
      </div>
    </div>
  );
}
