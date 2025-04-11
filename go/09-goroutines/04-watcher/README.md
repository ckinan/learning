# Watcher Example

This program demonstrates a progress monitoring system in Go. It uses goroutines, channels, and a worker pool to simulate a task processing system while periodically reporting the progress.

## How It Works

1. **Monitor Function**: 
   - A `monitor` function runs in a separate goroutine.
   - It periodically calculates and prints the progress percentage based on the number of completed tasks.
   - It listens to a `count` channel for updates on completed tasks and a `done` channel to terminate monitoring.

2. **Worker Pool**:
   - A buffered channel (`worker`) is used to limit the number of concurrent workers.
   - Each worker simulates a task by sleeping for a random duration and then reports completion through the `count` channel.

3. **Main Function**:
   - Spawns workers to process tasks.
   - Waits for all workers to complete using a `sync.WaitGroup`.
   - Signals the `monitor` function to stop once all tasks are done.

## Running the Program

To run the program, follow these steps:

1. Ensure you have Go installed on your system. You can download it from [golang.org](https://golang.org/).
2. Navigate to the `04-watcher` directory:
   ```bash
   cd ~/learning/go/09-goroutines/04-watcher
   ```
3. Run the program using the `go run` command:
   ```bash
   go run main.go
   ```

## Example Output

The output will vary because the program uses random delays. A typical output might look like this:

```
progress: 0.00%
progress: 5.00%
progress: 15.00%
progress: 25.00%
...
progress: 100.00%
done
```

The progress is updated every 500 milliseconds, and the program prints "done" when all tasks are completed.
