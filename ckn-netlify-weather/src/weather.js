const fetch = require("node-fetch");
const API_KEY = process.env.API_KEY;

exports.handler = async function (event, context, callback) {
  const lat = event.queryStringParameters.lat;
  const lon = event.queryStringParameters.lon;

  const url = `http://api.weatherstack.com/current?access_key=${API_KEY}&query=${lat},${lon}&units=m`;
  console.log(url);

  try {
    const response = await fetch(url);
    const json = await response.json();
    console.log(json);
    callback(null, {
      statusCode: 200,
      body: JSON.stringify(json),
    });
  } catch (error) {
    console.log(error);
    callback(null, {
      statusCode: 200,
      body: JSON.stringify(error),
    });
  }
};
