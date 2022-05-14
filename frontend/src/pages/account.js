import React, { useState, useEffect } from "react";
import getWithAuth from "../api/getWithAuth";
import putWithAuth from "../api/putWithAuth";
import getCancelAPI from "../api/getForCancelAPI"
import Router, { useRouter } from "next/router";
import moment from "moment";

export default function Account() {
  // const moment= require('moment')
  const router = useRouter();
  const [userDetails, setUserDetails] = useState({});
  const [editMode, setEditMode] = useState(false);

  const [userEmail, setUserEmail] = useState("");
  const [userPassword, setUserPassword] = useState("");
  const [userAddress, setUserAddress] = useState("");
  const [userMobile, setUserMobile] = useState("");
  const [userName, setUserName] = useState("");

  const getUserDetails = async () => {
    const data = await getWithAuth(
      "/customer/profile/" + localStorage.getItem("user_id")
    );
    setUserDetails(data);

    setUserEmail(data.email);
    setUserPassword("");
    setUserAddress(data.address);
    setUserMobile(data.mobile);
    setUserName(data.name);
  };

  useEffect(() => {
    getUserDetails();
  }, []);

  const handleSave = async () => {
    console.log("SAVE");
    let temp_password = "";
    if (userPassword == "") {
      temp_password = userDetails.password;
    } else {
      temp_password = userPassword;
    }
    setEditMode(false);
    const data = await putWithAuth(
      "/customer/update/" + localStorage.getItem("user_id"),
      {
        body: {
          email: userEmail,
          password: temp_password,
          name: userName,
          address: userAddress,
          mobile: userMobile,
        },
      }
    );
    window.location.reload();
  };

  const [bookings, setBookings] = useState([]);
  useEffect(async () => {
    const data = await getWithAuth(
      "/booking/getall/customer/" + localStorage.getItem("user_id")
    );
    setBookings(data);
  }, []);

  const [bookingFilter, setBookingFilter] = useState("all");

  const handleCancel = async (id) => {
    const data = await getCancelAPI("/booking/cancel/" + id);
    window.location.reload();
  };

  const getDate = (epoch) => {
    return moment.unix(epoch / 1000).format("dddd, MMMM Do, YYYY");
  };

  return (
    <div className="container w-full flex">
      <div className="w-1/2 mr-4">
        {userDetails.email != null ? (
          <>
            <div>
              <button
                onClick={() => {
                  if (editMode) {
                    window.location.reload();
                  }
                  setEditMode(!editMode);
                }}
                className="mt-4 bg-button-color text-white px-8 py-2 rounded-full transition-all hover:shadow-button-shadow"
              >
                {editMode ? "Exit edit mode" : "Edit"}
              </button>
              {editMode ? (
                <button
                  onClick={handleSave}
                  className="mt-4 ml-4 bg-button-color text-white px-8 py-2 rounded-full transition-all hover:shadow-button-shadow"
                >
                  Save
                </button>
              ) : (
                <></>
              )}
              <button
                onClick={() => router.push("/")}
                className="ml-4 bg-button-color text-white px-8 py-2 rounded-full transition-all hover:shadow-button-shadow"
              >
                Home
              </button>
            </div>

            <div className="flex flex-col w-full justify-center items-center">
              <label className="w-full flex flex-col my-2 mt-8">
                <span>Email:</span>
                <input
                  type="text"
                  readOnly={editMode}
                  value={userEmail}
                  className="p-2 rounded-lg"
                  onChange={(e) => setUserEmail(e.target.value)}
                />
              </label>
              <label className="w-full flex flex-col my-2">
                <span>Password:</span>
                <input
                  type="text"
                  readOnly={editMode}
                  value={userPassword}
                  className="p-2 rounded-lg"
                  onChange={(e) => setUserPassword(e.target.value)}
                />
              </label>
              <label className="w-full flex flex-col my-2">
                <span>Name:</span>
                <input
                  type="text"
                  readOnly={editMode}
                  value={userName}
                  className="p-2 rounded-lg"
                  onChange={(e) => setUserName(e.target.value)}
                />
              </label>
              <label className="w-full flex flex-col my-2">
                <span>Address:</span>
                <input
                  type="text"
                  readOnly={editMode}
                  value={userAddress}
                  className="p-2 rounded-lg"
                  onChange={(e) => setUserAddress(e.target.value)}
                />
              </label>
              <label className="w-full flex flex-col my-2">
                <span>Mobile:</span>
                <input
                  type="text"
                  readOnly={editMode}
                  value={userMobile}
                  className="p-2 rounded-lg"
                  onChange={(e) => setUserMobile(e.target.value)}
                />
              </label>
            </div>
          </>
        ) : (
          <></>
        )}
      </div>
      <div className="mt-4 ml-4 w-1/2 flex flex-col items-center">
        <div className="flex justify-between w-full">
          <button
            onClick={() => setBookingFilter("all")}
            className="w-1/4 bg-button-color text-white px-8 py-2 rounded-full transition-all hover:shadow-button-shadow"
          >
            All
          </button>
          <button
            onClick={() => setBookingFilter("past")}
            className="w-1/4 bg-button-color text-white px-8 py-2 rounded-full transition-all hover:shadow-button-shadow"
          >
            Past
          </button>
          <button
            onClick={() => setBookingFilter("upcoming")}
            className="w-1/4 bg-button-color text-white px-8 py-2 rounded-full transition-all hover:shadow-button-shadow"
          >
            Upcoming
          </button>
        </div>
        <div className="my-4">My Bookings</div>
        <div className="w-full">
          {bookings.map((item) => {
            return (
              <div
                className=" flex justify-between w-full px-2 py-4 my-2 rounded-lg"
                style={{ background: "rgba(255, 255, 255, 0.5)" }}
              >
                <div>
                  <div>{item.hotel.name}</div>
                  <div>From: {getDate(item.stayFrom)}</div>
                  <div>To:{getDate(item.stayUpto)}</div>
                  <div>{item.hotel.address}</div>
                </div>
                <div>
                  {item.cancelled ? (
                    <>Cancelled</>
                  ) : (
                    <button
                      onClick={() => handleCancel(item.id)}
                      style={{ background: "#AA4A44" }}
                      className="px-2 py-1 rounded text-white"
                    >
                      Cancel Booking
                    </button>
                  )}
                </div>
              </div>
            );
          })}
        </div>
      </div>
    </div>
  );
}
