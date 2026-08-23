#include <stdio.h>

void double_array1(int *a, int len) {
	for (int i = 0; i < len; i++) {
		a[i] *= 2;
	}
}
void double_array2(int a[], int len) {
	for (int i = 0; i < len; i++) {
		a[i] *= 2;
	}
}

int main() {
	int x[5] = {1, 2, 3, 4, 5};

	double_array1(x, 5);
	for (int i = 0; i < 5; i++) {
		printf("%d\n", x[i]);
	}

	printf("---\n");

	int y[5] = {1, 2, 3, 4, 5};

	double_array2(y, 5);
	for (int i = 0; i < 5; i++) {
		printf("%d\n", y[i]);
	}
}
