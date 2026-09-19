package main

import (
	"fmt"
	"time"
)

func f(s string) {
	fmt.Printf("s=%s\n", s)
}

func main() {
	// not a goroutine, just a simple function invokation, to show
	// syntax difference between a call of goroutines and non-goroutine
	// functions
	f("z")
	// create a goroutine by running a function with `go` statement
	go f("x")
	// can also run anonymous functions as goroutine
	go func(s string) {
		fmt.Printf("s=%s\n", s)
	}("y")
	// needs to "wait" for a little so the main goroutine doesn't
	// exit, allowing the other goroutines to complete
	time.Sleep(2 * time.Second)
}
