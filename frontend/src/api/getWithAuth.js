import config from "../config";

const articles_fetcher = async (
  endpoint,
  params = {}
) => {
  const res = await fetch(config['BACKEND_URL']+endpoint, {
    method: params.method || "GET",
    ...(Boolean(params.body) && {
      body: JSON.stringify(params.body),
    }),
    headers: {
      "Content-Type": "application/json",
      "access-control-allow-origin": "*",
      "Access-Control-Allow-Credentials": true,
      "Authorization": "Bearer " + localStorage.getItem('token'),
      ...params.headers,
    },
  });

  // If the status code is not in the range 200-299,
  // we still try to parse and throw it.
  if (!res.ok) {
    const error = new Error("An error occurred while fetching the data.");
    // Attach extra info to the error object.
    error.info = await res.json();
    error.status = res.status;

    throw error;
  }

  switch (params.type) {
    case "text":
      return res.text();
    default:
      return res.json();
  }
};

export default articles_fetcher;
