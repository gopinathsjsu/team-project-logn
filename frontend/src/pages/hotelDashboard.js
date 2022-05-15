import React, { useState, useEffect } from "react";
import getWithAuth from "../api/getWithAuth";
import postWithAuth from "../api/postWithAuth";
import moment from "moment";

export default function HotelDashboard() {
  const [tab, setTab] = useState("hotel");
  const [allHotels, setAllHotels] = useState([]);
  const [hotelIds, setHotelIds] = useState([]);
  useEffect(() => {
    setHotelIds([]);
    let temp_arr = [];
    allHotels.forEach(function (item) {
      temp_arr.push(item.id);
    });
    setHotelIds(temp_arr);
  }, [allHotels]);
  useEffect(async () => {
    const data = await getWithAuth(
      "/hotel/get/byuser/" + localStorage.getItem("user_id")
    );
    setAllHotels(data);
  }, []);
  const [allBookings, setAllBookings] = useState([]);
  useEffect(async () => {
    let data = [];
    hotelIds.forEach(async function (id) {
      let res = await getWithAuth("/booking/getall/hotel/" + id);
      res.forEach(function (item) {
        data.push(item);
      });
    });
    setAllBookings(data);
  }, [hotelIds]);
  const getDate = (epoch) => {
    return moment.unix(epoch / 1000).format("dddd, MMMM Do, YYYY");
  };

  const [hotelName, setHotelName] = useState("");
  const [hotelMobile, setHotelMobile] = useState("");
  const [hotelCity, setHotelCity] = useState("");
  const [hotelState, setHotelState] = useState("");
  const [hotelCountry, setHotelCountry] = useState("");
  const [hotelAddress, setHotelAddress] = useState("");
  const [hotelSeasonalMultiplier, setHotelSeasonalMultiplier] = useState(1);
  const [hotelHolidayMultiplier, setHotelHolidayMultiplier] = useState(1);
  const [singleRoomNumber, setSingleRoomNumber] = useState(0);
  const [doubleRoomNumber, setDoubleRoomNumber] = useState(0);
  const [suitNumber, setSuitNumber] = useState(0);
  const [singleRoomRate, setSingleRoomRate] = useState(0);
  const [doubleRoomRate, setDoubleRoomRate] = useState(0);
  const [suitRoomRate, setSuitRoomRate] = useState(0);

  const addHotel = async () => {
    alert("GOING to post")
    const data = await postWithAuth("/hotel/add", {
      body: {
        custId: parseInt(localStorage.getItem("user_id")),
        name: hotelName,
        mobile: hotelMobile,
        city: hotelCity,
        state: hotelState,
        country: hotelCountry,
        holidayMultiplier: hotelHolidayMultiplier,
        seasonalMulitplier: hotelSeasonalMultiplier,
        address: hotelAddress,
        roomDtoList: [
          {
            type: "double",
            rate: doubleRoomRate,
            numberOfRooms: doubleRoomNumber,
          },
          {
            type: "single",
            rate: singleRoomRate,
            numberOfRooms: singleRoomNumber,
          },
          {
            type: "suit",
            rate: suitRoomRate,
            numberOfRooms: suitNumber,
          },
        ],
      },
    });
    alert('Added');
    window.location.reload();
  };
  return (
    <div className="container">
      <div>
        <button
          onClick={() => setTab("hotel")}
          className="mr-4 bg-button-color text-white px-8 py-2 rounded-full transition-all hover:shadow-button-shadow"
        >
          View Hotels
        </button>
        <button
          onClick={() => setTab("add")}
          className="bg-button-color text-white px-8 py-2 rounded-full transition-all hover:shadow-button-shadow"
        >
          Add Hotel
        </button>
        <button
          onClick={() => setTab("bookings")}
          className="ml-4 bg-button-color text-white px-8 py-2 rounded-full transition-all hover:shadow-button-shadow"
        >
          View Bookings
        </button>
      </div>
      {tab == "bookings" ? (
        <div>
          {allBookings.map((item) => {
            return (
              <div
              key={item.id}
                className="flex flex-wrap justify-between w-full px-2 py-4 my-2 rounded-lg"
                style={{ background: "rgba(255, 255, 255, 0.5)" }}
              >
                <div className="w-1/3">{item.hotel.name}</div>
                <div className="w-1/3">From: {getDate(item.stayFrom)}</div>
                <div className="w-1/3">To: {getDate(item.stayUpto)}</div>
                <div className="w-1/3">Amount: ${item.totalBill}</div>
                <div className="w-1/3">
                  Number of Guests: {item.numberOfGuests}
                </div>
                <div className="w-1/3">
                  Status: {item.cancelled ? "Cancelled" : "Active"}
                </div>
              </div>
            );
          })}
        </div>
      ) : (
        <></>
      )}
      {tab == "hotel" ? (
        <div>
          {allHotels.map((item, index) => {
            return (
              <div
              key={index}
                className="flex justify-between w-full px-2 py-4 my-2 rounded-lg"
                style={{ background: "rgba(255, 255, 255, 0.5)" }}
              >
                <div>
                  <div>{item.name}</div>
                  <div>
                    <div>{item.address}</div>
                    <div>
                      {item.city}, {item.state}, {item.country}
                    </div>
                  </div>
                </div>
                <div>
                  {item.rooms.map((item, index) => {
                    return (
                      <div key={index} className="flex">
                        <div>
                          Type: {item.type}, Number of Rooms:{" "}
                          {item.numberOfRooms}, Rate: ${item.rate}
                        </div>
                      </div>
                    );
                  })}
                </div>
              </div>
            );
          })}
        </div>
      ) : (
        <></>
      )}
      {tab == "add" ? (
        <div
          className="w-1/2 flex flex-col justify-between w-full px-2 py-4 my-2 rounded-lg"
          style={{ background: "rgba(255, 255, 255, 0.5)" }}
        >
          <label className="w-full flex flex-col">
            <span>Hotel Name:</span>
            <input
              className="p-2 rounded-lg"
              type="text"
              name="email"
              value={hotelName}
              onChange={(event) => setHotelName(event.target.value)}
            />
          </label>
          <label className="w-full flex flex-col">
            <span>Hotel Contact Number:</span>
            <input
              className="p-2 rounded-lg"
              type="text"
              name="email"
              value={hotelMobile}
              onChange={(event) => setHotelMobile(event.target.value)}
            />
          </label>
          <label className="w-full flex flex-col">
            <span>Hotel Address:</span>
            <input
              className="p-2 rounded-lg"
              type="text"
              name="email"
              value={hotelAddress}
              onChange={(event) => setHotelAddress(event.target.value)}
            />
          </label>
          <label className="w-full flex flex-col">
            <span>Hotel City:</span>
            <input
              className="p-2 rounded-lg"
              type="text"
              name="email"
              value={hotelCity}
              onChange={(event) => setHotelCity(event.target.value)}
            />
          </label>
          <label className="w-full flex flex-col">
            <span>Hotel State:</span>
            <input
              className="p-2 rounded-lg"
              type="text"
              name="email"
              value={hotelState}
              onChange={(event) => setHotelState(event.target.value)}
            />
          </label>
          <label className="w-full flex flex-col">
            <span>Hotel Country:</span>
            <input
              className="p-2 rounded-lg"
              type="text"
              name="email"
              value={hotelCountry}
              onChange={(event) => setHotelCountry(event.target.value)}
            />
          </label>
          <label className="w-full flex flex-col">
            <span>Hotel Seasonal Multiplier:</span>
            <input
              className="p-2 rounded-lg"
              type="number"
              step="0.01"
              name="email"
              value={hotelSeasonalMultiplier}
              onChange={(event) =>
                setHotelSeasonalMultiplier(event.target.value)
              }
            />
          </label>
          <label className="w-full flex flex-col">
            <span>Hotel Holiday Multiplier:</span>
            <input
              className="p-2 rounded-lg"
              type="number"
              step="0.01"
              name="email"
              value={hotelHolidayMultiplier}
              onChange={(event) =>
                setHotelHolidayMultiplier(event.target.value)
              }
            />
          </label>

          <label className="w-full flex flex-col">
            <span># Single Rooms:</span>
            <input
              className="p-2 rounded-lg"
              type="number"
              name="email"
              value={singleRoomNumber}
              onChange={(event) => setSingleRoomNumber(event.target.value)}
            />
          </label>
          <label className="w-full flex flex-col">
            <span># Double Rooms:</span>
            <input
              className="p-2 rounded-lg"
              type="number"
              name="email"
              value={doubleRoomNumber}
              onChange={(event) => setDoubleRoomNumber(event.target.value)}
            />
          </label>
          <label className="w-full flex flex-col">
            <span># Suit Rooms:</span>
            <input
              className="p-2 rounded-lg"
              type="number"
              name="email"
              value={suitNumber}
              onChange={(event) => setSuitNumber(event.target.value)}
            />
          </label>
          <label className="w-full flex flex-col">
            <span># Single Rooms Rate:</span>
            <input
              className="p-2 rounded-lg"
              type="number"
              name="email"
              value={singleRoomRate}
              onChange={(event) => setSingleRoomRate(event.target.value)}
            />
          </label>
          <label className="w-full flex flex-col">
            <span># Double Rooms Rate:</span>
            <input
              className="p-2 rounded-lg"
              type="number"
              name="email"
              value={doubleRoomRate}
              onChange={(event) => setDoubleRoomRate(event.target.value)}
            />
          </label>
          <label className="w-full flex flex-col">
            <span># Suit Rooms Rate:</span>
            <input
              className="p-2 rounded-lg"
              type="number"
              name="email"
              value={suitRoomRate}
              onChange={(event) => setSuitRoomRate(event.target.value)}
            />
          </label>
          <button onClick={() => addHotel()} className="mt-4 bg-button-color text-white px-8 py-2 rounded-full transition-all hover:shadow-button-shadow">SUBMIT</button>
        </div>
      ) : (
        <></>
      )}
    </div>
  );
}
