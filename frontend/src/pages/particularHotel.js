import React, { useState, useEffect } from "react";
import Router, { useRouter } from "next/router";
import getWithAuth from "../api/getWithAuth";
import Image from "next/image";

export default function particularHotel() {
  const router = useRouter();
  const id = router.query;
  const [hotelDetails, setHotelDetails] = useState([]);

  const [allMeals, setAllMeals] = useState(false);
  const [breakfast, setBreakfast] = useState(false);
  const [lunch, setLunch] = useState(false);
  const [dinner, setDinner] = useState(false);
  const [parking, setParking] = useState(false);
  const [gym, setGym] = useState(false);
  const [pool, setPool] = useState(false);

  useEffect(async () => {
    if (id.id != undefined) {
      const data = await getWithAuth("/hotel/get/" + id.id);
      setHotelDetails(data);
      console.log(data);
    }
  }, [id]);
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
            <button className="bg-button-color text-white px-8 py-2 rounded-full transition-all hover:shadow-button-shadow">
              Book Now
            </button>
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
