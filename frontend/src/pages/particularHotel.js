import React, { useState, useEffect } from "react";
import Router, { useRouter } from "next/router";
import getWithAuth from "../api/getWithAuth";
import Image from "next/image";

export default function particularHotel() {
  const router = useRouter();
  const id = router.query;
  const [hotelDetails, setHotelDetails] = useState([]);

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
        className="w-full flex justify-between items-start rounded-lg p-4"
        style={{ background: "rgba(255, 255, 255, 0.5)" }}
      >
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
    </div>
  );
}
