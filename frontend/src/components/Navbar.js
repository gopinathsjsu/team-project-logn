import React, { useState, useEffect } from "react";
import Link from "next/link";

export default function Navbar() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  useEffect(() => {
    if (localStorage.getItem("token")) {
      setIsLoggedIn(true);
    }
  }, []);

  return (
    <div className="pt-2 container h-16 flex justify-between items-center">
      <div>LOGO</div>
      {!isLoggedIn ? (
        <div>
          <Link href="/login">
            <button className="mx-4 bg-button-color text-white px-8 py-2 rounded-full transition-all hover:shadow-button-shadow">
              Sign In
            </button>
          </Link>
          <Link href="/signup">
            <button className="bg-button-color text-white px-8 py-2 rounded-full transition-all hover:shadow-button-shadow">
              Sign Up
            </button>
          </Link>
        </div>
      ) : (
        <Link href="/account">
          <button className="bg-button-color text-white px-8 py-2 rounded-full transition-all hover:shadow-button-shadow">
            My Account
          </button>
        </Link>
      )}
    </div>
  );
}
