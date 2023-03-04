package main

import (
	"fmt"

	"github.com/spf13/cobra"
)

const version = "0.1.0"

func main() {
	rootCmd := &cobra.Command{
		Use:   "hello3",
		Short: "A simple CLI that greets the user",
	}

	rootCmd.AddCommand(&cobra.Command{
		Use: "greet",
		Run: func(cmd *cobra.Command, args []string) {
			fmt.Printf("Hello, %s!\n", args[0])
		},
	})

	rootCmd.CompletionOptions.HiddenDefaultCmd = true

	if err := rootCmd.Execute(); err != nil {
		fmt.Println(err)
	}
}
