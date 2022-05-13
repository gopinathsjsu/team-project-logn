import config from "../../config";

const postRequest = async (endpoint, body) => {
  const requestOptions = {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify(body),
  };
  return await fetch(config["BACKEND_URL"] + endpoint, requestOptions).then(
    (data) => {
      data.json();
    }
  );
};

export default postRequest;