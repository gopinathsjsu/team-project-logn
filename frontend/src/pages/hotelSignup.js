import React, { useState, useEffect } from "react";
import Link from "next/link";
import { add } from "date-fns";
import config from "../config";
import postRequest from "../api/postAPI";
import Router, { useRouter } from "next/router";

export default function HotelSignup() {
  const router = useRouter();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [name, setName] = useState("");
  const [address, setAddress] = useState("");
  const [mobile, setMobile] = useState("");

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleNameChange = (event) => {
    setName(event.target.value);
  };

  const handleAddressChange = (event) => {
    setAddress(event.target.value);
  };

  const handleMobileChange = (event) => {
    setMobile(event.target.value);
  };

  const handleSignup = async (event) => {
    const response = await postRequest("/signup", {
      body: {
        email: email,
        password: password,
        name: name,
        address: address,
        mobile: mobile,
        role: "ADMIN",
      },
    });
    console.log("INSIDE");
    router.push("/hotelLogin");
  };

  return (
    <div className="container flex items-center justify-center">
      <div
        className="flex flex-col items-center justify-center h-full w-1/3 rounded-lg p-4"
        style={{ background: "rgba(255, 255, 255, 0.5)" }}
      >
        <label className="w-full flex flex-col">
          <span>Email:</span>
          <input
            className="p-2 rounded-lg"
            type="text"
            name="email"
            value={email}
            onChange={(event) => handleEmailChange(event)}
          />
        </label>
        <label className="w-full flex flex-col my-8">
          <span>Password:</span>
          <input
            className="p-2 rounded-lg"
            type="password"
            name="password"
            value={password}
            onChange={(event) => handlePasswordChange(event)}
          />
        </label>
        <label className="w-full flex flex-col mb-8">
          <span>Employee name:</span>
          <input
            className="p-2 rounded-lg"
            type="text"
            name="name"
            value={name}
            onChange={(event) => handleNameChange(event)}
          />
        </label>
        <label className="w-full flex flex-col">
          <span>Address:</span>
          <input
            className="p-2 rounded-lg"
            type="text"
            name="address"
            value={address}
            onChange={(event) => handleAddressChange(event)}
          />
        </label>
        <label className="w-full flex flex-col my-8">
          <span>Phone number:</span>
          <input
            className="p-2 rounded-lg"
            type="text"
            name="mobile"
            value={mobile}
            onChange={(event) => handleMobileChange(event)}
          />
        </label>

        <button
          className="w-full border-2 border-black rounded-lg py-2"
          onClick={(event) => {
            handleSignup(event);
          }}
        >
          SignUp
        </button>
      </div>
    </div>
  );
}
