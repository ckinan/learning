package main

import (
	"encoding/json"
	"fmt"
	"net/http"
	"slices"
	"strconv"
	"sync"
)

type Task struct {
	ID   int    `json:"id"`
	Name string `json:"name"`
}

var (
	tasks  = []Task{}
	taskID = 1
	mutex  = &sync.Mutex{}
)

func listTasks(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")
	json.NewEncoder(w).Encode(tasks)
}

func createTask(w http.ResponseWriter, r *http.Request) {
	var task Task
	if err := json.NewDecoder(r.Body).Decode(&task); err != nil {
		http.Error(w, "Invalid request payload", http.StatusBadRequest)
		return
	}

	mutex.Lock()
	task.ID = taskID
	taskID++
	tasks = append(tasks, task)
	mutex.Unlock()

	w.WriteHeader(http.StatusCreated)
	json.NewEncoder(w).Encode(task)
}

func updateTask(w http.ResponseWriter, r *http.Request) {
	idStr := r.PathValue("id")
	id, err := strconv.Atoi(idStr)
	if err != nil {
		http.Error(w, "Invalid task ID", http.StatusBadRequest)
		return
	}

	var updatedTask Task
	if err := json.NewDecoder(r.Body).Decode(&updatedTask); err != nil {
		http.Error(w, "Invalid request payload", http.StatusBadRequest)
		return
	}

	mutex.Lock()
	defer mutex.Unlock()
	for i, task := range tasks {
		if task.ID == id {
			tasks[i].Name = updatedTask.Name
			json.NewEncoder(w).Encode(tasks[i])
			return
		}
	}

	http.Error(w, "Task not found", http.StatusNotFound)
}

func deleteTask(w http.ResponseWriter, r *http.Request) {
	idStr := r.PathValue("id")
	id, err := strconv.Atoi(idStr)
	if err != nil {
		http.Error(w, "Invalid task ID", http.StatusBadRequest)
		return
	}

	mutex.Lock()
	defer mutex.Unlock()
	for i, task := range tasks {
		if task.ID == id {
			tasks = slices.Delete(tasks, i, i+1)
			w.WriteHeader(http.StatusNoContent)
			return
		}
	}

	http.Error(w, "Task not found", http.StatusNotFound)
}

func main() {
	mux := http.NewServeMux()
	mux.HandleFunc("GET /tasks", listTasks)
	mux.HandleFunc("POST /tasks/create", createTask)
	mux.HandleFunc("PUT /tasks/update/{id}", updateTask)
	mux.HandleFunc("DELETE /tasks/delete/{id}", deleteTask)

	fmt.Println("Starting server on :8080")
	http.ListenAndServe(":8080", mux)
}
