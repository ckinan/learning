// Package twofer implements a function that simulates a dialog.
package twofer

import "fmt"

// The template of the dialogue where a name or pronoun should be specified.
const dialogueTemplate = "One for %s, one for me."

// ShareWith generates a sentence that a person has to say when receives the
// good he/she is buying. The dialog may or may not contain the name of another
// person.
func ShareWith(name string) string {
	if name == "" {
		name = "you"
	}
	return fmt.Sprintf(dialogueTemplate, name)
}
