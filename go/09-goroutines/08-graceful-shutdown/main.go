package main

import (
	"context"
	"fmt"
	"os"
	"os/signal"
	"syscall"
	"time"
)

func main() {
	// Create a context that is canceled on termination signals
	ctx, cancel := context.WithCancel(context.Background())

	// Set up a channel to listen for OS signals
	signalChan := make(chan os.Signal, 1)
	signal.Notify(signalChan, os.Interrupt, syscall.SIGTERM)

	// Start a goroutine to handle termination signals
	go func() {
		<-signalChan // Wait for a termination signal
		fmt.Println("\nTermination signal received. Shutting down gracefully...")
		cancel() // Cancel the context
	}()

	// Simulate an infinite task
	fmt.Println("Starting infinite task. Press Ctrl+C to terminate.")
	for {
		select {
		case <-ctx.Done():
			fmt.Println("Cleanup completed. Exiting program.")
			return
		default:
			// Simulate work
			fmt.Println("Working...")
			time.Sleep(1 * time.Second)
		}
	}
}
