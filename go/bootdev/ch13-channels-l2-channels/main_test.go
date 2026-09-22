package main

import (
	"fmt"
	"slices"
	"testing"
	"time"
)

func Test(t *testing.T) {
	type testCase struct {
		emails [3]email
		isOld  [3]bool
	}

	runCases := []testCase{
		{[3]email{
			{
				body: "Words are pale shadows of forgotten names. As names have power, words have power.",
				date: time.Date(2019, 2, 0, 0, 0, 0, 0, time.UTC),
			},
			{
				body: "It's like everyone tells a story about themselves inside their own head.",
				date: time.Date(2021, 3, 1, 0, 0, 0, 0, time.UTC),
			},
			{
				body: "Bones mend. Regret stays with you forever.",
				date: time.Date(2022, 1, 2, 0, 0, 0, 0, time.UTC),
			},
		}, [3]bool{true, false, false}},
		{[3]email{
			{
				body: "Music is a proud, temperamental mistress.",
				date: time.Date(2018, 0, 0, 0, 0, 0, 0, time.UTC),
			},
			{
				body: "Have you heard of that website Boot.dev?",
				date: time.Date(2017, 0, 0, 0, 0, 0, 0, time.UTC),
			},
			{
				body: "It's awesome honestly.",
				date: time.Date(2016, 0, 0, 0, 0, 0, 0, time.UTC),
			},
		}, [3]bool{true, true, true}},
	}

	submitCases := append(runCases, []testCase{
		{[3]email{
			{
				body: "I have stolen princesses back from sleeping barrow kings.",
				date: time.Date(2019, 0, 0, 0, 0, 0, 0, time.UTC),
			},
			{
				body: "I burned down the town of Trebon",
				date: time.Date(2019, 6, 6, 0, 0, 0, 0, time.UTC),
			},
			{
				body: "I have spent the night with Felurian and left with both my sanity and my life.",
				date: time.Date(2022, 7, 0, 0, 0, 0, 0, time.UTC),
			},
		}, [3]bool{true, true, false}},
	}...)

	testCases := runCases
	if withSubmit {
		testCases = submitCases
	}

	skipped := len(submitCases) - len(testCases)

	passCount := 0
	failCount := 0

	for _, test := range testCases {
		isOld := checkEmailAge(test.emails)
		if !slices.Equal(isOld[:], test.isOld[:]) {
			failCount++
			t.Errorf(`---------------------------------
Test Failed:
  input:
    * %v | %v
    * %v | %v
    * %v | %v
  expected: %v
  actual:   %v
`,
				test.emails[0].body, test.emails[0].date,
				test.emails[1].body, test.emails[1].date,
				test.emails[2].body, test.emails[2].date,
				test.isOld, isOld)
		} else {
			passCount++
			fmt.Printf(`---------------------------------
Test Passed:
  input:
    * %v | %v
    * %v | %v
    * %v | %v
  expected: %v
  actual:   %v
`,
				test.emails[0].body, test.emails[0].date,
				test.emails[1].body, test.emails[1].date,
				test.emails[2].body, test.emails[2].date,
				test.isOld, isOld)
		}
	}

	fmt.Println("---------------------------------")
	if skipped > 0 {
		fmt.Printf("%d passed, %d failed, %d skipped\n", passCount, failCount, skipped)
	} else {
		fmt.Printf("%d passed, %d failed\n", passCount, failCount)
	}
}

// withSubmit is set at compile time depending
// on which button is used to run the tests
var withSubmit = true
