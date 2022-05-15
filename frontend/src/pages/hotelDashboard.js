import React, { useState, useEffect } from "react";

export default function HotelDashboard() {
  const [tab, setTab] = useState("bookings");
  return (
    <div className="container">
      <div>
        <button onClick={() => setTab("add")} className="bg-button-color text-white px-8 py-2 rounded-full transition-all hover:shadow-button-shadow">
          Add Hotel
        </button>
        <button onClick={() => setTab("bookings")} className="ml-4 bg-button-color text-white px-8 py-2 rounded-full transition-all hover:shadow-button-shadow">
          View Bookings
        </button>
      </div>
      {
        (tab == "bookings")?(<>BOOKINGS</>):(<>ADD</>)
      }
    </div>
  );
}