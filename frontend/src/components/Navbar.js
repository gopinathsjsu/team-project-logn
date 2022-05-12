import React from "react";

export default function Navbar() {
  return (
    <div className="pt-2 container h-16 flex justify-between items-center">
      <div>LOGO</div>
      <div>
        <button className="mx-4 bg-button-color text-white px-8 py-2 rounded-full transition-all hover:shadow-button-shadow">
          Sign In
        </button>
        <button className="bg-button-color text-white px-8 py-2 rounded-full transition-all hover:shadow-button-shadow">
          Sign Up
        </button>
      </div>
    </div>
  );
}
