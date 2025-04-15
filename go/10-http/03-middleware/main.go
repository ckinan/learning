package main

import (
	"fmt"
	"log"
	"net/http"
	"time"
)

func accessLogMiddleware(next http.Handler) http.Handler {
	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		start := time.Now()
		log.Printf("%s %s %s", r.Method, r.URL.Path, time.Since(start))
		next.ServeHTTP(w, r)
	})
}

func jwtValidationMiddleware(next http.Handler) http.Handler {
	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		token := r.Header.Get("Authorization")
		if token == "" { // TODO: Do some real JWT validation here...
			http.Error(w, "Unauthorized", http.StatusUnauthorized)
			return
		}
		next.ServeHTTP(w, r)
	})
}

// Middleware chaining function
func chainMiddlewares(handler http.Handler, middlewares ...func(http.Handler) http.Handler) http.Handler {
	for i := len(middlewares) - 1; i >= 0; i-- {
		handler = middlewares[i](handler)
	}
	return handler
}

func rootHandler(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintln(w, "Welcome to the root handler!")
}

func main() {
	mux := http.NewServeMux()
	// Chain the middlewares and set the root handler
	mux.Handle("/task", chainMiddlewares(
		http.HandlerFunc(rootHandler),
		accessLogMiddleware,
		jwtValidationMiddleware,
	))
	log.Println("Starting server on :8080")
	http.ListenAndServe(":8080", mux)
}
