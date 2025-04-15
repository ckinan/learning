# Worker Pool Pattern Example

This folder contains a simple example of the **Worker Pool Pattern** implemented in Go. The worker pool pattern is a concurrency model where multiple workers process tasks from a shared queue, allowing efficient use of resources.

## Code Explanation

The example consists of the following components:

1. **Worker Function**:
   - Each worker reads tasks (jobs) from a shared `jobs` channel.
   - Processes the task (in this case, doubles the job value).
   - Sends the result to a `results` channel.

2. **Main Function**:
   - Creates a fixed number of workers (3 in this example).
   - Sends tasks (jobs) to the `jobs` channel.
   - Waits for all workers to finish using a `sync.WaitGroup`.
   - Collects and prints the results from the `results` channel.

### Code Flow
- The `main` function initializes the `jobs` and `results` channels.
- It starts 3 workers, each running in its own goroutine.
- Jobs (1 to 5) are sent to the `jobs` channel.
- Workers process the jobs concurrently and send the results to the `results` channel.
- The `main` function waits for all workers to finish and then prints the results.

## What We Learned

- **Concurrency in Go**: Using goroutines to run multiple workers concurrently.
- **Channels**: Sharing tasks and results between workers and the main function.
- **Worker Pool Pattern**: Efficiently distributing tasks among a fixed number of workers.
- **sync.WaitGroup**: Ensuring all workers complete their tasks before proceeding.

## Note on Worker Selection

It is up to the Go scheduler to determine which worker is available to take a job from the `jobs` channel. The order of job processing may vary depending on the scheduler's decisions.

## How to Test/Execute

1. Navigate to this folder in your terminal:
   ```bash
   cd ./learning/go/09-goroutines/06-worker-pool
   ```

2. Run the program using the `go run` command:
   ```bash
   go run main.go
   ```

3. You should see output similar to this:
   ```
   Worker 1 processing job 1
   Worker 2 processing job 2
   Worker 3 processing job 3
   Worker 1 processing job 4
   Worker 2 processing job 5
   Result: 2
   Result: 4
   Result: 6
   Result: 8
   Result: 10
   ```
   Note: The order of job processing may vary depending on the Go scheduler.