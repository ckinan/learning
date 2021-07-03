const GREETING = process.env.GREETING;

exports.handler = async (event, context) => {
  return {
    statusCode: 200,
    body: GREETING
  };
};