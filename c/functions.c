#include <stdio.h>

int foo(void); // prototype

int plus_one(int n) {
	return n + 1;
}

void increment(int a){
	a++;
}

int main(void) {
	int i = 10, j;
	j = plus_one(i);
	printf("i + 1 is %d\n", j);

	int x = 10;
	increment(x);
	printf("x == %d\n", x);

	int y;
	y = foo();
	printf("%d from foo()\n", y);
}

int foo(void) {
	return 3490;
}
