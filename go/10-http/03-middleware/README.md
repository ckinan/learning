# Middleware Example in Go

This project demonstrates how to implement and chain middlewares in a Go HTTP server. The example includes two middlewares:

1. **Access Log Middleware**: Logs the HTTP method, URL path, and request duration for each incoming request.
2. **JWT Validation Middleware**: Validates the presence of an `Authorization` token in the request header and denies access if the token is missing.

## How the Code Works

- The `accessLogMiddleware` logs details about each request before passing it to the next middleware or handler.
- The `jwtValidationMiddleware` checks for an `Authorization` token in the request header. If the token is missing, it responds with a `401 Unauthorized` status and stops further processing.
- The `chainMiddlewares` function allows multiple middlewares to be composed in a clean and modular way.
- The `rootHandler` is the final handler that responds with a welcome message if all middlewares pass.

## How to Test/Execute

1. **Run the Server**:
   ```bash
   go run main.go
   ```
   The server will start on `http://localhost:8080`.

2. **Test the Endpoints**:
   - Without an `Authorization` header:
     ```bash
     curl -i http://localhost:8080
     ```
     Response:
     ```
     HTTP/1.1 401 Unauthorized
     Unauthorized
     ```
   - With an `Authorization` header:
     ```bash
     curl -i -H "Authorization: Bearer some-token" http://localhost:8080
     ```
     Response:
     ```
     HTTP/1.1 200 OK
     Welcome to the root handler!
     ```

## What We Learned

- How to implement middleware in Go.
- How to chain multiple middlewares using a helper function.
- The importance of middleware order in request and response flows.
- How to validate request headers and handle unauthorized access.

## Real-World Use Cases

- **Access Logging**: Useful for monitoring and debugging HTTP requests in production systems.
- **Authentication and Authorization**: Middleware like the JWT validation example can enforce security policies.
- **Request/Response Transformation**: Middlewares can modify requests (e.g., adding headers) or responses (e.g., compressing data).
- **Rate Limiting**: Middleware can limit the number of requests from a client to prevent abuse.

This example provides a foundation for building robust and modular HTTP servers in Go.