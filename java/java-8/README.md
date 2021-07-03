**Links**

- https://www.tutorialspoint.com/java8/java8_method_references.htm
- http://tutorials.jenkov.com/java/lambda-expressions.html

**Book: Java 8 Lambdas**

Concepts

- The difference is that object-oriented programming is mostly about abstracting over data, while functional programming is mostly about abstracting over behavior.
- The biggest language change in Java 8 is the introduction of lambda expressions - a compact way of passing around behavior.
- A functional interface is an interface with a single abstract method that is used as the type of a lambda expression.
- A lambda expression is a method without a name that is used to pass around behavior as if it were data.

Stream

- The Stream interface contains a series of functions .. allow us to write collections-processing code at a higher level of abstraction.
- It’s very easy to figure out whether an operation is eager or lazy: look at what it returns. If it gives you back a Stream, it’s lazy; if it gives you back another value or void, then it’s eager.
