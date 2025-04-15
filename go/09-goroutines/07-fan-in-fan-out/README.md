# Fan-In and Fan-Out Pattern in Go

This directory demonstrates the **Fan-In** and **Fan-Out** concurrency patterns in Go. These patterns are used to distribute work across multiple goroutines (fan-out) and then aggregate the results into a single channel (fan-in).

## What It Does

1. **Fan-Out**: Distributes a set of numbers to multiple worker goroutines, each processing its own data independently.
2. **Fan-In**: Merges the results from multiple worker goroutines into a single output channel for further processing or aggregation.

The example includes:
- A `fanOut` function to distribute numbers to multiple channels.
- A `fanIn` function to merge multiple channels into one.
- Logs and inline comments to help understand the flow of data.

## How to Test/Execute

1. Navigate to this directory in your terminal:
   ```bash
   cd ./learning/go/09-goroutines/07-fan-in-fan-out
   ```

2. Run the program using the Go command:
   ```bash
   go run main.go
   ```

3. Observe the output in the terminal. You will see logs for:
   - Numbers being distributed to worker channels (fan-out).
   - Numbers being merged back from worker channels (fan-in).
   - The final aggregated results.

## What We Learned

- **Fan-Out**: How to distribute work across multiple goroutines using channels.
- **Fan-In**: How to merge results from multiple channels into one.
- **Concurrency in Go**: Leveraging goroutines and channels to handle tasks concurrently.
- **Channel Closing**: The importance of closing channels to signal completion and avoid deadlocks.

## Real-World Examples

1. **Web Scraping**:
   - **Fan-Out**: Distribute a list of URLs to multiple worker goroutines, each fetching data from a URL.
   - **Fan-In**: Merge the fetched data from all workers into a single channel for further processing.

2. **Data Processing Pipelines**:
   - **Fan-Out**: Split a large dataset into smaller chunks and process them concurrently.
   - **Fan-In**: Combine the processed results into a single output for aggregation or storage.

3. **Task Queues**:
   - **Fan-Out**: Distribute tasks from a queue to multiple workers for parallel execution.
   - **Fan-In**: Collect the results or logs from all workers into a single channel for monitoring or reporting.

This example provides a foundational understanding of these patterns, which can be extended to more complex real-world scenarios.