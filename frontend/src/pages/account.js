import React, { useState, useEffect } from "react";
import getWithAuth from "../api/getWithAuth";

export default function account() {
  const [userDetails, setUserDetails] = useState({});

  const getUserDetails = async () => {
    const data = await getWithAuth(
      "/customer/profile/" + localStorage.getItem("user_id")
    );
    console.log(data);
    setUserDetails(data);
  };

  useEffect(() => {
    getUserDetails();
  }, []);

  return (
    <div className="container">
      {userDetails.email != null ? (
        <>
          <button>Edit</button>
          <div className="flex flex-col">
            <input type="text" readonly="readonly" value={userDetails.email} />
            <input type="text" readonly="readonly" value={userDetails.name} />
            <input
              type="text"
              readonly="readonly"
              value={userDetails.address}
            />
            <input type="text" readonly="readonly" value={userDetails.mobile} />
          </div>
        </>
      ) : (
        <></>
      )}
    </div>
  );
}

address: "abs";
email: "r@gmail.com";
id: 13;
mobile: "9819394";
name: "raj";
password: "$2a$10$ow3ehGccasZ10Kk7stOKHezp/XMS0HcINZJDZsbJyMKT.Oxq4NKCi";
role: "USER";
