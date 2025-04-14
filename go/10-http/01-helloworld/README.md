# Simple HTTP Server

This is a simple HTTP server implemented in Go using the standard library. It listens on port `8080` and provides the following functionality:

## Endpoints

- `GET /`: Responds with `Hello, World!`.
- Any other path will return a `404 Not Found` response.

## How to Run

1. Make sure you have [Go](https://golang.org/dl/) installed on your system.
2. Navigate to the `10-http` directory.
3. Run the following command to start the server:

   ```bash
   go run main.go
   ```

4. The server will start and listen on `http://localhost:8080`.

## How to Test

1. Open your browser or use a tool like `curl` or Postman.
2. Test the root endpoint:

   ```bash
   curl http://localhost:8080/
   ```

   You should see the response:

   ```
   Hello, World!
   ```

3. Test an invalid endpoint:

   ```bash
   curl http://localhost:8080/something
   ```

   You should see a `404 Not Found` response.