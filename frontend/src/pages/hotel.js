import React, { useState, useEffect } from "react";
import Link from "next/link";

export default function Hotel() {
  const [type, setType] = useState("in");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };
  return (
    <div className="container">
      <div>
        <Link href="/hotelLogin">
        <button
          className="mr-4 bg-button-color text-white px-8 py-2 rounded-full transition-all hover:shadow-button-shadow"
        >
          Hotel SignIn
        </button>
        </Link>
        <Link href="/hotelSignup">
        <button
          className="bg-button-color text-white px-8 py-2 rounded-full transition-all hover:shadow-button-shadow"
        >
          Hotel SignUp
        </button>
        </Link>
      </div>
    </div>
  );
}