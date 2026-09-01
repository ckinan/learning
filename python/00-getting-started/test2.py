def print_msg(msg: str):
    print(msg)


# define a class with attibutes
class Person:
    name: str
    age: int

    def __init__(self, name: str, age: int) -> None:
        self.name = name
        self.age = age


p = Person("jose", 41)
print_msg(f"hello {p.name}! your age is {p.age}")
