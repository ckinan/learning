# Hello World HTTP Server

This is a simple HTTP server implemented in Go that responds with "Hello, World!" when accessed at the root endpoint (`/`).

## Why Use a Custom `ServeMux` Instead of the Default Mux?

In this program, we use a custom `http.ServeMux` instead of the default `http.DefaultServeMux`. Here are the reasons:

1. **Isolation and Avoiding Global State**:
   - The default mux (`http.DefaultServeMux`) is a global variable. Using it can lead to conflicts if multiple parts of the application or external libraries register handlers to it.
   - A custom `ServeMux` provides better isolation and avoids unintended side effects.

2. **Flexibility**:
   - A custom mux allows you to define and manage routes explicitly within your application, making the code easier to understand and maintain.

3. **Testing**:
   - Using a custom mux makes it easier to test specific routes or handlers by passing the mux directly to the HTTP server or test utilities.

4. **Scalability**:
   - For larger applications, using a custom mux allows you to create multiple muxes for different parts of the application, improving modularity and organization.

## How to Run

1. Make sure you have [Go](https://golang.org/dl/) installed on your system.
2. Navigate to the `01-helloworld` directory.
3. Run the following command to start the server:

   ```bash
   go run main.go
   ```

4. The server will start and listen on `http://localhost:8080`.

## How to Test

1. Open your browser or use a tool like `curl`.
2. Access the root endpoint:

   ```bash
   curl http://localhost:8080/
   ```

   You should see the response:

   ```
   Hello, World!
   ```

3. Access an invalid endpoint:

   ```bash
   curl http://localhost:8080/something
   ```

   You should see a `404 Not Found` response.