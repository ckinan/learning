# Task Management REST API

This is a simple REST API implemented in Go using the standard library. It allows you to manage tasks with basic operations such as creating, updating, deleting, and listing tasks.

## What We Learned

- How to use the Go standard library to build a REST API.
- Handling HTTP methods like `GET`, `POST`, `PUT`, and `DELETE`.
- Using `json.NewEncoder` and `json.NewDecoder` for JSON serialization and deserialization.
- Managing shared resources safely with a `mutex` to prevent race conditions.
- Query parameter handling and basic error responses.

## Golang 1.22 Routing Enhancements

Starting with Go 1.22, the `http.ServeMux` now supports routing based on both the HTTP method and the path when using `HandleFunc`. This enhancement simplifies routing logic and makes it easier to define handlers for specific methods and paths without additional checks inside the handler functions.

### Benefits:

1. **Cleaner Code**:
   - You no longer need to manually check the HTTP method (`r.Method`) inside your handler functions. The `ServeMux` can route requests directly based on the method and path.

2. **Improved Readability**:
   - Handlers are explicitly tied to both the method and path, making the routing logic easier to understand and maintain.

3. **Reduced Boilerplate**:
   - Eliminates repetitive code for method validation, reducing the risk of errors.

### Example:

Instead of writing:

```go
mux.HandleFunc("/tasks", func(w http.ResponseWriter, r *http.Request) {
    if r.Method != http.MethodGet {
        http.Error(w, "Method not allowed", http.StatusMethodNotAllowed)
        return
    }
    // Handle GET /tasks
})
```

You can now write:

```go
mux.HandleFunc("GET /tasks", listTasks)
mux.HandleFunc("POST /tasks", createTask)
```

This pattern makes it clear which methods and paths are supported, and the `ServeMux` handles the routing for you.

Source: https://go.dev/blog/routing-enhancements

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