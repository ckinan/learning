# Graceful Shutdown in Go

This program demonstrates how to implement a graceful shutdown mechanism in Go using `context` and OS signal handling. It ensures that the program can clean up resources and exit properly when interrupted by termination signals like `Ctrl+C` or `SIGTERM`.

## What We Learned
- How to use the `context` package to manage cancellation signals.
- How to listen for OS signals using the `os/signal` package.
- How to handle cleanup tasks before exiting a program.
- The importance of graceful shutdown in long-running applications.

## How to Test/Execute the Program
1. Navigate to the `08-graceful-shutdown` directory.
2. Run the program using the following command:
   ```bash
   go run main.go
   ```
3. The program will start an infinite task, printing "Working..." every second.
4. Press `Ctrl+C` to send a termination signal. The program will handle the signal, print a shutdown message, and exit gracefully after cleanup.

## Real-World Scenarios
This approach is useful in scenarios such as:
- Web servers that need to close connections and release resources before shutting down.
- Background workers or services that need to save state or finish processing tasks before exiting.
- Any long-running application that requires proper cleanup to avoid resource leaks or data corruption.