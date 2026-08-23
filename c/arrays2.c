#include <stdio.h>

void times2(int *a, int len) {
	for (int i = 0; i < len; i++) {
		printf("%d\n", a[i] * 2);
	}
}

void times3(int a[], int len) {
	for (int i = 0; i < len; i++) {
		printf("%d\n", a[i] * 3);
	}
}

void times4(int a[5], int len) {
	for (int i = 0; i < len; i++) {
		printf("%d\n", a[i] * 4);
	}
}

int main(void) {
	int x[5] = {11, 22, 33, 44, 55};

	times2(x, 5);
	printf("===\n");
	times3(x, 5);
	printf("===\n");
	times4(x, 5);
}
