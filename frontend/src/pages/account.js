import React, { useState, useEffect } from "react";
import getWithAuth from "../api/getWithAuth";

export default function account() {
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

  const handleSave = () => {
    console.log("SAVE");
    setEditMode(false);
  };

  return (
    <div className="container">
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
          </div>

          <div className="flex flex-col w-1/2 justify-center items-center">
            <label className="w-full flex flex-col my-2 mt-8">
              <span>Email:</span>
              <input
                type="text"
                readonly={editMode}
                value={userEmail}
                className="p-2 rounded-lg"
                onChange={(e) => setUserEmail(e.target.value)}
              />
            </label>
            <label className="w-full flex flex-col my-2">
              <span>Password:</span>
              <input
                type="text"
                readonly={editMode}
                value={userPassword}
                className="p-2 rounded-lg"
                onChange={(e) => setUserPassword(e.target.value)}
              />
            </label>
            <label className="w-full flex flex-col my-2">
              <span>Name:</span>
              <input
                type="text"
                readonly={editMode}
                value={userName}
                className="p-2 rounded-lg"
                onChange={(e) => setUserName(e.target.value)}
              />
            </label>
            <label className="w-full flex flex-col my-2">
              <span>Address:</span>
              <input
                type="text"
                readonly={editMode}
                value={userAddress}
                className="p-2 rounded-lg"
                onChange={(e) => setUserAddress(e.target.value)}
              />
            </label>
            <label className="w-full flex flex-col my-2">
              <span>Mobile:</span>
              <input
                type="text"
                readonly={editMode}
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
  );
}