package main

import (
	"fmt"
	"net/http"
)

func greet(w http.ResponseWriter, r *http.Request) {
	if r.URL.Path != "/" {
		http.NotFound(w, r)
		return
	}
	fmt.Fprintln(w, "Hello, World!")
}

func main() {
	http.HandleFunc("/", greet)
	fmt.Println("Starting server on :8080")
	http.ListenAndServe(":8080", nil)
}
