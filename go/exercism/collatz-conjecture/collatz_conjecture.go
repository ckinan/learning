package collatzconjecture

import "errors"

func CollatzConjecture(n int) (int, error) {
	var steps int
	if n <= 0 {
		return 0, errors.New("n should be positive number")
	}
	for n != 1 {
		if n%2 == 0 {
			n = n / 2
		} else {
			n = n*3 + 1
		}
		steps += 1
	}
	return steps, nil
}
