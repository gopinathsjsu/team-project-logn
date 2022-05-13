import React, { useState, useEffect } from "react";
import getWithAuth from "../api/getWithAuth";
import Router, { useRouter } from "next/router";

export default function findHotel() {
  const router = useRouter();

  const [hotelDetails, setHotelDetails] = useState([]);
  useEffect(async () => {
    const data = await getWithAuth("/hotel/search/?query=paseo");
    setHotelDetails(data);
    console.log(data);
  }, []);

  const handleBookNow = (id) => {
    router.push({
      pathname: "/particularHotel",
      query: { id: id },
    });
  };

  return (
    <div className="container mt-8">
      {hotelDetails.map((item) => {
        return (
          <div
            className="w-full flex justify-between items-start rounded-lg p-4"
            style={{ background: "rgba(255, 255, 255, 0.5)" }}
          >
            <div>{item.name}</div>
            <div>
              <div>Address:</div>
              <div>
                {item.address}, {item.city}
              </div>
              <div>
                {item.state}, {item.country}
              </div>
            </div>
            <div>
              <button
                onClick={() => handleBookNow(item.id)}
                className="bg-button-color text-white px-8 py-2 rounded-full transition-all hover:shadow-button-shadow"
              >
                Book Now
              </button>
            </div>
          </div>
        );
      })}
    </div>
  );
}
