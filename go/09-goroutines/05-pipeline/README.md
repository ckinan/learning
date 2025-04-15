# Pipeline Pattern Example

This folder contains a simple example of the **Concurrency Pipeline Pattern** implemented in Go. The pipeline pattern is a way to structure concurrent programs by chaining stages, where each stage performs a specific task and passes its output to the next stage via channels.

## Code Explanation

The example consists of three main components:

1. **Generator Stage**:
   - Produces a stream of integers and sends them to a channel.
   - Implemented in the `generator` function.

2. **Square Stage**:
   - Consumes integers from the input channel, squares them, and sends the results to another channel.
   - Implemented in the `square` function.

3. **Main Function**:
   - Connects the stages to form a pipeline.
   - Consumes the final output and prints the squared numbers.

### Code Flow
- The `generator` function creates a channel and sends numbers (1, 2, 3, 4, 5) to it.
- The `square` function reads numbers from the generator's channel, squares them, and sends the results to another channel.
- The `main` function reads the squared numbers from the final channel and prints them.

## What We Learned

- **Concurrency in Go**: Using goroutines to run functions concurrently.
- **Channels**: Passing data between stages in a thread-safe manner.
- **Pipeline Pattern**: Structuring concurrent programs into stages for better modularity and scalability.

## How to Test/Execute

1. Navigate to this folder in your terminal:
   ```bash
   cd ../learning/go/09-goroutines/05-pipeline
   ```

2. Run the program using the `go run` command:
   ```bash
   go run main.go
   ```

3. You should see the squared numbers printed as output:
   ```
   1
   4
   9
   16
   25
   ```