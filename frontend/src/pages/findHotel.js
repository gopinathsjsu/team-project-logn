import React, { useState, useEffect } from "react";
import getWithAuth from "../api/getWithAuth";
import Router, { useRouter } from "next/router";
import { da } from "date-fns/locale";
import { data } from "autoprefixer";

export default function FindHotel() {
  const router = useRouter();

  const [hotelDetails, setHotelDetails] = useState([]);
  const handleBookNow = (id) => {
    router.push({
      pathname: "/particularHotel",
      query: { id: id },
    });
  };

  const [searchInput, setSearchInput] = useState("");
  useEffect(async () => {
    const data = await getWithAuth("/hotel/all");
    setHotelDetails(data);
    console.log(data);
  }, [searchInput]);

  return (
    <div className="container flex flex-col items-center  mt-8">
      <div className="my-8 w-1/3">
        <input
          style={{ background: "rgba(255, 255, 255, 0.5)" }}
          placeholder="Search"
          className="w-full p-2 rounded-lg"
          type="text"
          onChange={(e) => setSearchInput(e.target.value)}
        />
      </div>
      {hotelDetails
        .filter((item) => {
          return (
            searchInput == "" ||
            item.name.toLowerCase().includes(searchInput.toLowerCase()) ||
            item.address.toLowerCase().includes(searchInput.toLowerCase()) ||
            item.city.toLowerCase().includes(searchInput.toLowerCase()) ||
            item.state.toLowerCase().includes(searchInput.toLowerCase()) ||
            item.country.toLowerCase().includes(searchInput.toLowerCase())
          );
        })
        .map((item) => {
          return (
            <div
              key={item.id}
              className="w-full flex justify-between items-start rounded-lg p-4 my-2"
              style={{ background: "rgba(255, 255, 255, 0.5)" }}
            >
              <div className="w-1/4">
                <div>{item.name}</div>
                {/* <div>{data}</div> */}
              </div>
              <div className="w-1/4">
                <div>Address:</div>
                <div>
                  {item.address}, {item.city}
                </div>
                <div>
                  {item.state}, {item.country}
                </div>
              </div>
              <div className="w-1/4 flex justify-end">
                <button
                  onClick={() => handleBookNow(item.id)}
                  className="bg-button-color text-white px-8 py-2 rounded-full transition-all hover:shadow-button-shadow"
                >
                  BookNow
                </button>
              </div>
            </div>
          );
        })}
    </div>
  );
}
