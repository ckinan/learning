package main

import (
	"fmt"
	"time"
)

func show(s string) {
	fmt.Println(s)
}

func main() {
	go show("hello from goroutine")
	time.Sleep(time.Second)
	show("hello from main")
}
