# Goroutines Example: Hello World

This is a simple Go program demonstrating the use of goroutines and channels. The program creates a goroutine to send a "Hello world" message through a channel, which is then received and printed in the main function.

## How to Run

1. Ensure you have [Go installed](https://golang.org/doc/install) on your system.
2. Navigate to the directory containing the program:
   ```bash
   cd ~/learning/go/09-goroutines/01-helloworld
   ```
3. Run the program using the `go run` command:
   ```bash
   go run main.go
   ```
4. You should see the following output:
   ```
   Hello world
   ```

## Key Concepts

- **Goroutines**: Lightweight threads managed by the Go runtime.
- **Channels**: Used for communication between goroutines, ensuring safe data exchange.

This example is a great starting point for learning concurrency in Go.
