# Semaphore Example: Limiting Concurrent Goroutines

This program demonstrates how to use a semaphore to limit the number of concurrent goroutines in Go. It processes tasks concurrently but ensures that no more than a specified number of tasks run at the same time.

## How It Works

- A buffered channel (`sem`) acts as a semaphore to limit the number of concurrent workers.
- The `sync.WaitGroup` ensures the program waits for all goroutines to complete before exiting.
- Each task simulates work by sleeping for 2 seconds.

## How to Run

1. Ensure you have [Go installed](https://golang.org/doc/install) on your system.
2. Navigate to the directory containing the program:
   ```bash
   cd ~/learning/go/09-goroutines/02-semaphore
   ```
3. Run the program using the `go run` command:
   ```bash
   go run main.go
   ```

## Expected Output

The program will process 5 tasks, with a maximum of 2 tasks running concurrently. You should see output similar to the following:

```
processing task 0...
processing task 1...
processing task 2...
processing task 3...
processing task 4...
done
```

- The order of task processing may vary due to the concurrent nature of goroutines.
- The program ensures that no more than 2 tasks are processed at the same time.
