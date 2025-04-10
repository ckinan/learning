package main

import (
	"fmt"
	"sync"
	"time"
)

func main() {
	maxConcurrentWorker := 2
	sem := make(chan int, maxConcurrentWorker)
	var wg sync.WaitGroup

	for i := range 5 {
		wg.Add(1)
		go func(i int) {
			defer wg.Done()
			sem <- 1
			fmt.Printf("processing task %d...\n", i)
			time.Sleep(time.Second * 2)
			<-sem
		}(i)
	}

	wg.Wait()
	fmt.Println("done")
}
