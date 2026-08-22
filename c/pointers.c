#include <stdio.h>

void print_int_bytes(int value) {
	unsigned char *byte_ptr = (unsigned char *)&value;

	printf("Bytes of %d: ", value);

	for (size_t i = 0; i < sizeof(int); i++) {
		printf("%02X ", byte_ptr[i]);
	}

	printf("\n");
}

void print_int_bits(int value) {
	printf("Bits of %d: ", value);

	// loop 32 times, once for each bit (from left to right)
	for (int i = 31; i >= 0; i--) {
		// shift the bit to the right and check if it is 1 or 0
		int bit = (value >> i) & 1;
		printf("%d", bit);

		// add a space after every 8 bits (1 byte) to make it easy to
		// read
		if (i % 8 == 0) {
			printf(" ");
		}
	}
	printf("\n");
}

void increment(int *value) {
	// *value = *value + 1; // valid
	// *value++; // invalid, the compiler reads *value++ as *(value++), so
	// 	what increments is the memory address, not the value itself
	(*value)++;
}

int main() {
	int number = 2022;
	print_int_bytes(number);
	print_int_bits(number);

	// %zu is the format specifier for type size_t
	printf("an int uses %zu bytes of memory\n", sizeof(int));
	// That prints "4" for me, but can vary by system.

	printf("The value of number is %d ... And its address is %p\n", number,
	       &number);

	int a;
	int *b;
	b = &a;
	a = 10;
	// remember: b is a int-pointer, not the value itself
	// the actual value is in variable `a`, not `b`
	printf("step 1 :: a = %d, b = %d\n", a, *b);
	*b = 20;
	printf("step 2 :: a = %d, b = %d\n", a, *b);
	printf("step 3 :: operate b + 10 = %d\n", (*b + 10));

	increment(b);
	printf("step 4 :: a-incremented = %d\n", a);
	increment(&a);
	printf("step 5 :: a-incremented = %d\n", a);

	// int *bad = NULL;
	// *bad = 12; // produces segmentation fault

	printf("sizeof int = %zu :: sizeof int* = %zu :: sizeof int = %zu\n", sizeof(int),
	       sizeof(b), sizeof(*b));
}
