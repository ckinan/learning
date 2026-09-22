source: https://www.boot.dev/lessons/04984711-09c4-4020-ac36-6d4214012d34

Assignment

Run the program. You'll see that it deadlocks and never exits. The sendIsOld function is trying to send on a channel, but no other goroutines are running that can accept the value from the channel.

Fix the deadlock by spawning a goroutine to send the "is old" values.
