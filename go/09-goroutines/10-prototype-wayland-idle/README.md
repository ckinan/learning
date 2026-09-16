# prototyping a wayland idle daemon

this application is a daemon that waits for idle time and user activity signals from wayland.
the interaction with wayland can be emulated using "fake" backends.
what actually matters here is the concurrency to wait for different signals:
- signal #1: idle time (can actually be multiple timeouts)
- signal #2: user activity
- signal #3 (optional): sigterm or something indicating the daemon needs to close cleanly

