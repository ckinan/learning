source: https://www.boot.dev/lessons/ae21fb67-6443-4b43-b569-14b452872311
Assignment

At Textio we send a lot of network requests. Each email we send must go out over the internet. To serve our millions of customers, we need a single Go program to be capable of sending thousands of emails at once.

Edit the sendEmail() function to execute its anonymous function concurrently so that the "received" message prints after the "sent" message.
