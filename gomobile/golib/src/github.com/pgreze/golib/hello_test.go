package golib

import "testing"

func TestGreetings(t *testing.T) {
	greetings := Greetings("Patrick")
	if greetings != "Hello, Patrick!" {
		t.Error("Illegal value: " + greetings)
	}
}
