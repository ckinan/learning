package main

import (
	"fmt"
	"math/rand"
	"time"
)

func main() {
	handler := make(chan chan int)

	go func() {
		for ch := range handler {
			go func(ch chan int) {
				fmt.Println("processing...")
				resp := rand.Intn(10)
				time.Sleep(time.Millisecond * time.Duration(rand.Intn(500)))
				ch <- resp
				fmt.Println("done")
			}(ch)
		}
	}()

	var jobs []chan int
	for range 5 {
		job := make(chan int)
		handler <- job
		jobs = append(jobs, job)
	}

	close(handler)

	for _, job := range jobs {
		result := <-job
		fmt.Println("result:", result)
	}
}
