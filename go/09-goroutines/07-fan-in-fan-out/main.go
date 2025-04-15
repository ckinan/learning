package main

import (
	"fmt"
	"sync"
)

// fanOut distributes numbers to multiple worker channels
func fanOut(numbers []int) []<-chan int {
	var chans []chan int
	for _, num := range numbers {
		ch := make(chan int)
		chans = append(chans, ch)
		go func(n int, c chan int) {
			defer close(c)
			fmt.Printf("Sending %d to worker channel\n", n) // Log the distribution
			c <- n
		}(num, ch)
	}

	// Convert to read-only channels
	var readOnlyChans []<-chan int
	for _, ch := range chans {
		readOnlyChans = append(readOnlyChans, ch)
	}
	return readOnlyChans
}

// fanIn merges multiple channels into one
func fanIn(chans []<-chan int) <-chan int {
	out := make(chan int)
	var wg sync.WaitGroup

	// Start a goroutine for each channel to forward its values to the output channel
	for _, ch := range chans {
		wg.Add(1)
		go func(c <-chan int) {
			defer wg.Done()
			for n := range c {
				fmt.Printf("Merging %d from worker channel\n", n) // Log the merging
				out <- n
			}
		}(ch)
	}

	// Close the output channel once all input channels are done
	go func() {
		wg.Wait()
		close(out)
	}()

	return out
}

func main() {
	// Input numbers to distribute
	numbers := []int{1, 2, 3, 4, 5}

	// Fan-out: distribute numbers to multiple channels
	fmt.Println("Starting fan-out...")
	workerChannels := fanOut(numbers)

	// Fan-in: merge all worker channels into one
	fmt.Println("Starting fan-in...")
	resultChannel := fanIn(workerChannels)

	// Print the merged results
	fmt.Println("Merged results:")
	for result := range resultChannel {
		fmt.Println(result)
	}
}
