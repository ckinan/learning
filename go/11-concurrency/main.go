package main

import (
	"fmt"
	"time"
)

func f(s string) {
	fmt.Printf("s=%s\n", s)
}

func main() {
	f("z")
	go f("x")
	go func(s string) {
		fmt.Printf("s=%s\n", s)
	}("y")
	time.Sleep(2 * time.Second)
}
