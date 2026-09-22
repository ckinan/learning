package main

import (
	"time"
)

type email struct {
	body string
	date time.Time
}

func checkEmailAge(emails [3]email) [3]bool {
	// one possible solution is to make this channel
	// as buffered channel, so that `sendIsOld` won't
	// block sending the msg to the channel
	// isOldChan := make(chan bool, 3)
	isOldChan := make(chan bool)

	// another possible fix: run `sendIsOld` concurrently
	// as a goroutine, so that the emails can send without
	// blocking the `main` goroutine, which will be
	// blocking/waiting until emails are sent via `isOldChan`
	// channel
	// since the Assignment asks for this goroutine, i'll
	// state this as the solution of this exercise
	go sendIsOld(isOldChan, emails)

	isOld := [3]bool{}
	isOld[0] = <-isOldChan
	isOld[1] = <-isOldChan
	isOld[2] = <-isOldChan
	return isOld
}

// don't touch below this line

func sendIsOld(isOldChan chan<- bool, emails [3]email) {
	for _, e := range emails {
		if e.date.Before(time.Date(2020, 0, 0, 0, 0, 0, 0, time.UTC)) {
			isOldChan <- true
			continue
		}
		isOldChan <- false
	}
}
