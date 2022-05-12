import React from "react";
import Link from "next/link";

export default function Navbar() {
  return (
    <div className="pt-2 container h-16 flex justify-between items-center">
      <div>LOGO</div>
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
    </div>
  );
}
