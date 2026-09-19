package prototypewaylandidle

import (
	"testing"
)

func TestWaitForSignal(t *testing.T) {
	signal := make(chan string)
	quit := make(chan int)
	go WaitForSignal(signal, quit)
	go RegisterIdleEvent(signal, 5)
	go RegisterIdleEvent(signal, 10)
	go RegisterIdleEvent(signal, 15)
	go RegisterIdleEvent(signal, 20)
	go RegisterUserInputEvent(signal)
	<-quit
}
