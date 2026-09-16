package prototypewaylandidle

import "testing"

func TestWatch(t *testing.T) {
	signal := make(chan int)
	go WaitForSignal(signal)
	signal <- 1
	signal <- 2
}
