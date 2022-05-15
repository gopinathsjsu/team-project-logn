import React, { useState, useEffect } from "react";
import Link from "next/link";
import Router, { useRouter } from "next/router";

export default function Navbar() {
  const router = useRouter();
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  useEffect(() => {
    if (localStorage.getItem("token")) {
      setIsLoggedIn(true);
    } else {
      setIsLoggedIn(false);
    }
  }, [isLoggedIn]);

  useEffect(() => {
    let interval = setInterval(() => {
      if (localStorage.getItem("token")) {
        setIsLoggedIn(true);
      } else {
        setIsLoggedIn(false);
      }
    }, 1000);
    return () => {
      clearInterval(interval)
    };
  }, []);

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("user_id");
    router.push("/");
    // window.location.reload();
  };

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
          <Link href="/hotel">
            <button className="ml-4 bg-button-color text-white px-8 py-2 rounded-full transition-all hover:shadow-button-shadow">
              Hotel
            </button>
          </Link>
        </div>
      ) : (
        <div>
          <Link href="/account">
            <button className="mx-4 bg-button-color text-white px-8 py-2 rounded-full transition-all hover:shadow-button-shadow">
              My Account
            </button>
          </Link>
          <button
            className="bg-button-color text-white px-8 py-2 rounded-full transition-all hover:shadow-button-shadow"
            onClick={(e) => handleLogout(e)}
          >
            Log out
          </button>
        </div>
      )}
    </div>
  );
}