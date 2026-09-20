package main

import (
	"fmt"
	"time"
)

// 1. create first channel
// unbuffered channel
// optional integer default is 0 (unbuffered channel)
func f1() {
	fmt.Println("=== f1 ===")
	c := make(chan bool)
	go func() {
		fmt.Println("in a secondary goroutine... sleeping 1s")
		time.Sleep(time.Second)
		c <- true
	}()
	fmt.Println("in the main goroutine")
	<-c
	fmt.Println("after secondary goroutine ends...")
}

// 2. case: what happens if a buffered channel is "full"
// a value can be sent to a channel, that takes 1 space of the buffer
// until "something" retrieves the value. that "something" may or may
// not use the value retrieved from the channel
// ALSO... the concept of "channel synchronization" is introduced here
// by channel `quit`, which emits a notification to the main goroutine
// when the work is done, so main goroutine will wait for it
func f2() {
	fmt.Println("=== f2.1 ===")
	c := make(chan string, 1)
	quit := make(chan bool, 1)
	go func() {
		// this receiver waits until there is a value in the buffer
		fmt.Printf("received: %s\n", <-c)
		fmt.Println("first value received. buffer is ready to receive second value")
		fmt.Printf("received: %s\n", <-c)
		quit <- true
	}()
	// since the buffer is empty, this is sent to the channel and ready
	// for process
	c <- "first value"
	// this needs to wait (blocks) until value in channel is received and buffer
	// is available
	// note: if there is no receivers found by Go, it would fail hard with a:
	// `fatal error: all goroutines are asleep - deadlock!`
	c <- "second value"
	<-quit

	// another application is a semaphore
	fmt.Println("=== f2.2 ===")
	sem := make(chan bool, 1)

	f2Helper := func(s string) {
		sem <- true
		fmt.Printf("received: %s\n", s)
		// without `<-sem`, the second value would never be printed
		// because the buffer is full (1) and nothing would be receiving it
		<-sem
	}
	go f2Helper("sem value 1")
	go f2Helper("sem value 2")
	// using time.Sleep for now, not going to use wait groups yet
	time.Sleep(time.Second)
}

// 3. case: unbuffered channels only accept sends if there is a
// corresponding receiver ready to receive it
func f3() {
	fmt.Println("=== f3 ===")
	c := make(chan string)
	done := make(chan bool)
	go func() {
		fmt.Printf("received: %s\n", <-c)
		done <- true
	}()
	// note: without the above anonymous function, the program would fail with a:
	// `fatal error: all goroutines are asleep - deadlock!`
	// b/c there wouldn't be any receivers for the value being sent to channel `c`
	c <- "some value"
	<-done
}

// f4 shows channel directions increasing type-satefy of the program
func f4() {
	fmt.Println("=== f4 ===")
	c := make(chan int)
	done := make(chan bool)
	sender := func(ch chan<- int, val int) {
		ch <- val
		fmt.Printf("sent to channel %d\n", val)
	}
	go sender(c, 1)
	receiver := func(ch <-chan int) {
		fmt.Printf("received %d\n", <-ch)
		done <- true
	}
	go receiver(c)
	<-done
}

// f5 shows an example of how the `select` statement works
func f5() {
	fmt.Println("=== f5 ===")
	c1 := make(chan bool, 1)
	c2 := make(chan bool, 1)
	done := make(chan struct{})
	actualValues := 0
	expectedValues := 2

	go func() {
		c1 <- true
	}()
	go func() {
		c2 <- true
	}()
	go func() {
		for {
			if actualValues == expectedValues {
				close(done)
				return
			}
			fmt.Printf("not done yet, actualValues: %d\n", actualValues)
			time.Sleep(time.Second)
		}
	}()

	for {
		// `select` statement blocks until one of the cases is ready
		select {
		case <-c1:
			fmt.Println("c1 received")
			actualValues++
		case v := <-c2:
			fmt.Printf("c2 received %v\n", v)
			actualValues++
		case <-done:
			fmt.Println("done...")
			return
		}
	}
}

// f6 shows non-blocking channel operations
// source: https://gobyexample.com/non-blocking-channel-operations
// essentially, when channel is blocking because can't receive or
// sent a message, we can use `select` and `default` clause to
// unblock the process
func f6() {
	fmt.Println("=== f6 ===")
	// if below line were `c1 := make(chan bool, 1)`, then message
	// could be sent and received
	c1 := make(chan bool)
	c2 := make(chan bool)

	// no message in channel to receive
	select {
	case msg := <-c1:
		fmt.Println("message received from c1", msg)
	default:
		fmt.Println("no message received from c1")
	}

	// message can't be sent b/c channel is unbuffered
	// if channel was: `c1 := make(chan bool, 1)` (buffered), then
	// messsage could be sent without error:
	// `fatal error: all goroutines are asleep - deadlock!`
	select {
	case c1 <- true:
		fmt.Println("message sent to channel c1")
	default:
		fmt.Println("message not sent to channel c1")
	}

	// multiple blocked-channel-operations can be unblocked by a default
	// clause
	select {
	case <-c1:
		fmt.Println("message received from c1")
	case <-c2:
		fmt.Println("message received from c2")
	default:
		fmt.Println("no message received from c1 and c2")
	}
}

func main() {
	f1()
	f2()
	f3()
	f4()
	f5()
	f6()
}
