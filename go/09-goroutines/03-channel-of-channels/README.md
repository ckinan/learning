# Channel of Channels Example

This program demonstrates the use of a channel of channels in Go. It creates a handler channel that receives multiple job channels. Each job channel is processed concurrently in a separate goroutine, simulating a task that takes a random amount of time to complete. The results of the tasks are then collected and printed.

## How It Works

1. A `handler` channel is created to manage job channels.
2. A goroutine listens on the `handler` channel and spawns a new goroutine for each job channel it receives.
3. Each job goroutine simulates processing by generating a random number and sleeping for a random duration.
4. The main function sends job channels to the `handler` and collects the results from each job channel.

## Running the Program

To run the program, follow these steps:

1. Ensure you have Go installed on your system. You can download it from [golang.org](https://golang.org/).
2. Navigate to the `03-channel-of-channels` directory:
   ```bash
   cd ~/learning/go/09-goroutines/03-channel-of-channels
   ```
3. Run the program using the `go run` command:
   ```bash
   go run main.go
   ```

## Example Output

The output will vary because the program uses random numbers and delays. A typical output might look like this:

```
processing...
processing...
done
result: 7
done
result: 3
```

Each "processing..." message corresponds to a job being processed, and the "result" lines show the results of the jobs.
