package prototypewaylandidle

import (
	"fmt"
	"time"
)

// WaitForSignal is an event loop that waits for idle events and user input
// to perform an action
func WaitForSignal(signal <-chan string, quit chan<- int) {
	fmt.Println("watching...")
	for s := range signal {
		fmt.Printf("got signal %s\n", s)
		if s == "userinput" {
			fmt.Println("exiting...")
			quit <- 1
		}
	}
}

func RegisterIdleEvent(signal chan<- string, idleTimeSec int) {
	time.Sleep(time.Duration(idleTimeSec) * time.Second)
	signal <- fmt.Sprintf("idle%ds", idleTimeSec)
}

func RegisterUserInputEvent(signal chan<- string) {
	time.Sleep(25 * time.Second)
	signal <- "userinput"
}
