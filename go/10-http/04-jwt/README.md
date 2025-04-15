# JWT Example

This folder contains a simple REST API implementation that demonstrates how to use JSON Web Tokens (JWT) for authentication and authorization. The API includes the following features:

- **Login Endpoint**: Accepts user credentials and generates a JWT signed with a private key.
- **Protected Endpoint**: Validates the JWT using a public key and allows access only if the token is valid.
- **Middleware**: Ensures that requests to the protected endpoint include a valid JWT.

## How to Test/Execute

1. **Generate Private and Public Keys**:
   - Run the following commands to generate the RSA keys:
     ```bash
     openssl genpkey -algorithm RSA -out private.pem -pkeyopt rsa_keygen_bits:2048
     openssl rsa -pubout -in private.pem -out public.pem
     ```
   - Place the `private.pem` and `public.pem` files in the `04-jwt` folder.

2. **Run the Server**:
   - Navigate to the `04-jwt` folder:
     ```bash
     cd ./learning/go/10-http/04-jwt
     ```
   - Start the server:
     ```bash
     go run main.go
     ```

3. **Test the API**:
   - Use a tool like `curl` or Postman to test the endpoints.

   - **Login Endpoint**:
     ```bash
     curl -X POST -H "Content-Type: application/json" -d '{"username":"user","password":"pass"}' http://localhost:8080/login
     ```
     This will return a JWT token.

   - **Protected Endpoint**:
     ```bash
     curl -X GET -H "Authorization: Bearer <your_token>" http://localhost:8080/protected
     ```
     Replace `<your_token>` with the JWT received from the login endpoint.

## What We Learned

1. **JWT Basics**:
   - JWTs are used to securely transmit information between parties.
   - They consist of three parts: Header, Payload, and Signature.

2. **Asymmetric Encryption**:
   - The private key is used to sign the JWT, ensuring only the key holder can create valid tokens.
   - The public key is used to verify the JWT, allowing anyone with the public key to validate tokens without exposing the private key.

3. **Middleware for Authorization**:
   - Middleware can be used to validate JWTs and restrict access to protected endpoints.

4. **Key Management**:
   - Properly managing private and public keys is crucial for security.
   - The private key must remain secret, while the public key can be distributed freely.

This example demonstrates how to implement secure authentication and authorization using JWTs in a Go application.