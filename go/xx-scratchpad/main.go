package main

import (
	"fmt"
	"sync/atomic"
	"time"
)

func main() {
	fmt.Println(time.Now())
	var ops atomic.Uint64
	ops.Add(1)
	ops.Add(1)
	fmt.Println(ops.Load())
}
