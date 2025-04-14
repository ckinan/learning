package main

import (
	"fmt"
	"math/rand"
	"sync"
	"time"
)

func monitor(total int, count chan int, done chan bool) {
	ticker := time.NewTicker(time.Millisecond * 500)
	current := 0
	for {
		select {
		case <-ticker.C:
			p := float64(current) / float64(total) * 100
			fmt.Printf("progress: %.2f%%\n", p)
		case c := <-count:
			current += c
		case <-done:
			return
		}
	}
}

func main() {
	worker := make(chan struct{}, 10)
	total := 100
	count := make(chan int)
	done := make(chan bool)
	var wg sync.WaitGroup

	go monitor(total, count, done)

	for i := range total {
		wg.Add(1)
		go func(i int) {
			defer wg.Done()
			worker <- struct{}{}
			// fmt.Println("i:", i)
			time.Sleep(time.Millisecond * time.Duration(rand.Intn(500)))
			<-worker
			count <- 1
		}(i)
	}
	wg.Wait()
	done <- true
	fmt.Println("done")
}
