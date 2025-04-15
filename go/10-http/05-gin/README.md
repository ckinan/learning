# Gin Example: Public and Protected Routes with JWT Authentication

This project demonstrates how to create public and protected routes using the Gin framework in Go. It includes a login endpoint to generate JWT tokens and middleware to secure protected routes by verifying the tokens.

## What We Learned

1. **Gin Framework Basics**: Setting up routes and groups (`/public` and `/protected`).
2. **JWT Authentication**: Generating and validating JWT tokens.
3. **Middleware**: Using middleware to secure routes.
4. **HTTP Methods**: Handling `GET` and `POST` requests.

## How to Execute the Code

1. Navigate to the `05-gin` directory:
   ```bash
   cd ./learning/go/10-http/05-gin
   ```

2. Run the application:
   ```bash
   go run main.go
   ```

3. The server will start on `http://localhost:8080`.

## How to Test the Code

1. **Public Endpoint**:
   - Test the `/public/ping` endpoint:
     ```bash
     curl http://localhost:8080/public/ping
     ```

2. **Login Endpoint**:
   - Test the `/login` endpoint to get a JWT token:
     ```bash
     curl -X POST http://localhost:8080/login \
     -H "Content-Type: application/json" \
     -d '{"username": "user", "password": "password"}'
     ```

   - The response will include a `token` field.

3. **Protected Endpoint**:
   - Use the token from the login response to access the `/protected/data` endpoint:
     ```bash
     curl http://localhost:8080/protected/data \
     -H "Authorization: Bearer <your_token>"
     ```

## How to Generate `private.pem` and `public.pem`

To use RSA keys for signing and verifying JWT tokens, you can generate a private and public key pair:

1. Generate a private key:
   ```bash
   openssl genpkey -algorithm RSA -out private.pem -pkeyopt rsa_keygen_bits:2048
   ```

2. Extract the public key:
   ```bash
   openssl rsa -pubout -in private.pem -out public.pem
   ```

3. Place the `private.pem` and `public.pem` files in the project directory.

These keys can then be used to sign and verify JWT tokens securely.