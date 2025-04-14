# Task Management REST API

This is a simple REST API implemented in Go using the standard library. It allows you to manage tasks with basic operations such as creating, updating, deleting, and listing tasks.

## What We Learned

- How to use the Go standard library to build a REST API.
- Handling HTTP methods like `GET`, `POST`, `PUT`, and `DELETE`.
- Using `json.NewEncoder` and `json.NewDecoder` for JSON serialization and deserialization.
- Managing shared resources safely with a `mutex` to prevent race conditions.
- Query parameter handling and basic error responses.

## Endpoints

- `GET /tasks`: Lists all tasks.
- `POST /tasks/create`: Creates a new task. Requires a JSON body with a `name` field.
- `PUT /tasks/update?id={id}`: Updates an existing task by ID. Requires a JSON body with a `name` field.
- `DELETE /tasks/delete?id={id}`: Deletes a task by ID.

## How to Run

1. Make sure you have [Go](https://golang.org/dl/) installed on your system.
2. Navigate to the `02-methods` directory.
3. Run the following command to start the server:

   ```bash
   go run main.go
   ```

4. The server will start and listen on `http://localhost:8080`.

## How to Test

1. Use a tool like `curl`, Postman, or your browser to interact with the API.

### Examples:

- **List tasks**:

  ```bash
  curl http://localhost:8080/tasks
  ```

- **Create a task**:

  ```bash
  curl -X POST -H "Content-Type: application/json" -d '{"name": "New Task"}' http://localhost:8080/tasks/create
  ```

- **Update a task**:

  ```bash
  curl -X PUT -H "Content-Type: application/json" -d '{"name": "Updated Task"}' http://localhost:8080/tasks/update?id=1
  ```

- **Delete a task**:

  ```bash
  curl -X DELETE http://localhost:8080/tasks/delete?id=1
  ```