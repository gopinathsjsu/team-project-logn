import Head from "next/head";
import Image from "next/image";
import { useEffect, useState } from "react";
import GooglePlacesAutocomplete from "react-google-places-autocomplete";
import BasicDateRangePicker from "../components/DateRangePicker";
import Router, { useRouter } from "next/router";

export default function Home() {
  const [dateRange, setDateRange] = useState([null, null]);
  const router = useRouter();

  useEffect(() => {
    if (dateRange[0] === dateRange[0] && dateRange[1] === dateRange[1]) {
      localStorage.setItem("startDate", dateRange[0]);
      localStorage.setItem("endDate", dateRange[1]);
    }
  }, [dateRange]);

  const handleClick = () => {
    if (dateRange[0] === dateRange[0] && dateRange[1] === dateRange[1]) {
      router.push("/findHotel");
    } else {
      alert("Date not valid");
    }
  };

  return (
    <div
      className="container flex flex-col justify-center"
      style={{ height: "90vh" }}
    >
      <div className="bg-white bg-opacity-75 rounded-lg flex px-4 justify-between items-center py-4 w-full shadow-button-shadow">
        <div className=" w-1/4">
          <div className="flex items-center mb-2 text-gray-600">
            <div style={{ width: "20px", height: "20px" }}>
              <Image
                width={20}
                height={20}
                src={require("../assets/location-pin.svg")}
              />
            </div>
            <div>Destination</div>
          </div>
          <GooglePlacesAutocomplete
            apiKey="AIzaSyAsZM9CsKqwulp8WISaXimzmH5bzqVbl4E"
            className="outline-0	"
          />
        </div>
        <div className="vl"></div>
        <div className=" w-1/4 flex justify-center">
          <BasicDateRangePicker setDateRange={setDateRange} />
        </div>
        <div className="vl"></div>
        <div className=" w-1/4">
          <button
            onClick={handleClick}
            className="bg-button-color text-white px-4 py-2 rounded-lg w-full transition-all hover:shadow-button-shadow"
          >
            Find Hotels
          </button>
        </div>
      </div>
    </div>
  );
}
