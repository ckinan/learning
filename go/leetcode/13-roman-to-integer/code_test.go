package main

import "testing"

func TestRomanToInt(t *testing.T) {
	type testCase struct {
		roman string
		want  int
	}

	runCases := []testCase{
		{
			roman: "III",
			want:  3,
		},
		{
			roman: "LVIII",
			want:  58,
		},
		{
			roman: "MCMXCIV",
			want:  1994,
		},
	}

	for _, test := range runCases {
		got := romanToInt(test.roman)
		if test.want != got {
			t.Errorf("wanted: %d, got %d", test.want, got)
		}
	}
}
