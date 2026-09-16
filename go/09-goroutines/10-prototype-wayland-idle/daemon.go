package prototypewaylandidle

import (
	"fmt"
	"time"
)

func WaitForSignal(signal chan int) {
	fmt.Println("watching...")
	for {
		select {
		case s := <-signal:
			fmt.Printf("got signal %d\n", s)
		default:
			fmt.Println("waiting 1s")
			time.Sleep(time.Second)
		}
	}
}
