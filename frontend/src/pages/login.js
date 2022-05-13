import React, { useState, useEffect } from "react";
import Link from "next/link";
import config from "../config";
import postRequest from "../api/postAPI";
import Router , {useRouter}  from 'next/router';

export default function Login() {
  const router = useRouter()
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleLogin = async (event) => {
    const data = await postRequest("/login", {
      body: {
        username: email,
        password: password,
      },
    });
    console.log("KKK: ", data);
    localStorage.setItem('token', data.jwtToken)
    localStorage.setItem('user_id', data.id)
    router.push('/')
  };

  return (
    <div className="container h-80 flex items-center justify-center">
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

        <button
          className="w-full border-2 border-black rounded-lg py-2"
          onClick={(event) => {
            handleLogin(event);
          }}
        >
          Log In
        </button>
        <div className="mt-4">
          Don't have an account?{" "}
          <Link href="/signup">
            <u className="cursor-pointer">Sign Up</u>
          </Link>
        </div>
      </div>
    </div>
  );
}
