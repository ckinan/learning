from __future__ import print_function

# this is a comment
print("Hello world")

# variables
myString = "Cesar"
myInt = 28
myBoolean = False
myChar = 'A'
myFloat = 0.0123

print("My String: " + myString)

# cast
print("Print My Int: " + str(myInt))

# conditionals
if myInt >= 0 and myInt < 20 :
    print("I am between 0 and 20")
elif myInt >= 20 and myInt < 30 :
    print("I am between 20 and 30")
else :
    print("I am greater or equal than 30")

# loop
for n in range(5) :
    print(n, end=',')
print("")

# methods / functions
def add(x=5, y=10):
    return x + y

print("str(add(2))=" + str(add(1,2)))
print("str(add(2))=" + str(add(2)))
print("str(add(y=2))=" + str(add(y=2)))

# List
myList = []
myList.append("A")
myList.append("B")
myList.append("C")

for s in myList:
    print(s)

print("List size: " + str(len(myList)))

# Set
mySet = set()
mySet.add("A")
mySet.add("B")
mySet.add("C")
mySet.add("A") # only 1 "A" for this list
for s in mySet :
    print(s)

print("Set size: " + str(len(mySet)))

# Map
myMap = {}
myMap = {"keyA": "valueA", "keyB": "valueB"}
print(myMap["keyA"])
print(myMap["keyB"])
myMap["key1"] = "value1"
myMap["key2"] = "value2"
print(myMap["key1"])
print(myMap["key2"])
print(myMap.keys())
print(myMap.values())
print(myMap.items())

class Person(object) :
    name = ""
    age = 0

    def __init__(self, name, age):
        self.name = name
        self.age = age

    def printMe(self):
        print("My name is " + self.name + " and I am " + str(self.age) + " years old")

    def showMyPhone(self):
        try:
            print("My phone number is " + self.phone) # I can use variables that were created outside the class
        except: # Using try-catch block to identify when phone number is not set at the time when the above
                # print instruction is invoked
            print("I do not have any phone number yet")

person = Person("Cesar", 28)
person.showMyPhone()
person.phone = "999-888-777" # it is not necessary to declare the name of the attribute inside the class
person.printMe()
person.showMyPhone()

class Student(Person):

    def __init__(self, name, age, classroom):
       super(Student, self).__init__(name, age)
       self.classroom = classroom

student = Student("Cesar", 28, "Language Classroom")
print("Hi, I'm {}, {} years old and my classroom is {}.".format(student.name, student.age, student.classroom))
