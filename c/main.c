#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

// sum the two given numbers
int sum(int x, int y) {
	return x + y;
}

int main(void) {
	sum(1, 2);
	sum(1, 4);
	sum(2, 3);
	sum(1, 2);
	sum(1, 2);
	sum(1, 2);
	int i = 2;
	float f = 3.14;
	char *s = "Hello, world!";
	bool x = true;

	printf("%s i = %d and f = %f!\n", s, i, f);

	if (x) {
		printf("x is true!\n");
	}

	int i2 = 10;
	int j2 = 5 + i2++;

	printf("%d, %d\n", i2, j2);

	i2 = 10;
	j2 = 5 + ++i2;

	printf("%d, %d\n", i2, j2);

	int a = 999;

	printf("%zu\n", sizeof a);
	printf("%zu\n", sizeof(2 + 7));
	printf("%zu\n", sizeof 3.14);
	printf("%zu\n", sizeof(int));
	printf("%zu\n", sizeof(char));

	int r;

	do {
		r = rand() % 100;
		printf("%d\n", r);
	} while (r != 37);

	int xw = 1;

	switch (xw) {
	case 1:
		printf("-- 1\n");
	case 2:
		printf("-- 2\n");
		break;
	case 3:
		printf("-- 3\n");
		break;
	}
}
