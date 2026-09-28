package main

import (
	"fmt"
	"sync"
	"time"
)

func f1Helper(s string) {
	fmt.Printf("s=%s\n", s)
}

func f1() {
	// not a goroutine, just a simple function invokation, to show
	// syntax difference between a call of goroutines and non-goroutine
	// functions
	f1Helper("z")
	// create a goroutine by running a function with `go` statement
	go f1Helper("x")
	// can also run anonymous functions as goroutine
	go func(s string) {
		fmt.Printf("s=%s\n", s)
	}("y")
	// needs to "wait" for a little so the main goroutine doesn't
	// exit, allowing the other goroutines to complete
	time.Sleep(2 * time.Second)
}

func f2Helper(i int) {
	fmt.Printf("worker %d started\n", i)
	time.Sleep(time.Second)
	fmt.Printf("worker %d done\n", i)
}

// source: https://gobyexample.com/waitgroups
func f2() {
	var wg sync.WaitGroup
	for i := range 5 {
		wg.Go(func() {
			f2Helper(i)
		})
	}
	wg.Wait()
}

func main() {
	f1()
	f2()
}
