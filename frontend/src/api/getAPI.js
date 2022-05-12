import config from "../../config";

const getRequest = async (endpoint) => {
  return await fetch(config['BACKEND_URL'] + endpoint).then((data) => {
    data.json();
  });
};

export default getRequest;