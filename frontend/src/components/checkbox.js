import React, { useState, useEffect } from "react";

export default function checkbox({ label, rate, getData, i, id }) {
  const [isChecked, setIsChecked] = useState(false);

  useEffect(() => {
    if (isChecked) {
      getData(label, rate, id);
    } else {
      getData("", 0, null);
    }
  }, [isChecked]);

  return (
    <div>
      <label className="mx-2">
        <input
          onChange={(e) => {
            setIsChecked(!isChecked);
          }}
          value={isChecked}
          type="checkbox"
          className="mx-1"
        />
        Select {label}
      </label>
    </div>
  );
}
