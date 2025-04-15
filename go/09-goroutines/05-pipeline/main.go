package main

import (
	"fmt"
)

func generator(nums ...int) <-chan int {
	out := make(chan int)
	go func() {
		for _, n := range nums {
			out <- n
		}
		close(out)
	}()
	return out
}

func square(in <-chan int) <-chan int {
	out := make(chan int)
	go func() {
		for n := range in {
			out <- n * n
		}
		close(out)
	}()
	return out
}

func main() {
	numbers := generator(1, 2, 3, 4, 5)
	squared := square(numbers)

	for result := range squared {
		fmt.Println(result)
	}
}
