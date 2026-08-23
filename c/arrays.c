#include <stdio.h>

int main() {
	printf("hello\n");

	float f[4];
	f[0] = 1.1;
	f[1] = 1.2;
	f[2] = 1.3;
	f[3] = 1.4;

	for (int i = 0; i < 4; i++) {
		printf("%f\n", f[i]);
	}

	printf("===\n");

	int a[6] = {22, 37, 3490, 18, 95};
	// array size is 6, but initialized with 5 element
	// the 6th element is initialized with 0 value

	for (int i = 0; i < 6; i++) {
		// last element (6th) is 0
		printf("%d\n", a[i]);
	}

	// compiler won't warn/error if you try to access arrays out of bounds
	printf("%d, %d, %d...\n", a[5], a[6], a[7]);
}
