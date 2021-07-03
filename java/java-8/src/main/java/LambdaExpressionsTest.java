public class LambdaExpressionsTest {

    public static void main(String args[]) {
        System.out.println("Lambda expressions");

        LambdaExpressionsTest _this = new LambdaExpressionsTest();

        // First example
        PrintService printService = message ->
                System.out.println("Message: " + message);

        printService.print("This is my message");

        MathOperation sum = (x, y) -> x + y;

        MathOperation multiply = (x, y) -> {
            System.out.println("Here inside my Lambda Expression...!");
            return x * y;
        };

        System.out.println("Sum: " + sum.execute(1, 2));
        System.out.println("Multiply: " + multiply.execute(1, 2));
        // ./First example

        PrintService myPrintService = text -> System.out.println("Printing this -> " + text);
        myPrintService.print("Here we are");

        String outVariable = "My text created outside my lambda expression";
        PrintService myPrintService2 = text -> System.out.println("Printing this -> " + text + ". And my outVariable is: " + outVariable);
        myPrintService2.print("Here we are");

        // Method references
        PrintService myPrintService3 = System.out::println;
        myPrintService3.print("Here we are (method reference)");

        _this.testSingleMethodInterface();
        _this.testParameterType();

        PrintService myPrintService4 = (message) -> {
            someMethod(message);
            System.out.println(message);
        };

        myPrintService4.print("my print service 4");

    }

    static void someMethod(String someText) {
        System.out.println("Executing some method: " + someText);
    }

    void testSingleMethodInterface() {
        // Single method interface
        ListenerRepo repo = new ListenerRepo();
        repo.addListener(
                (oldState, newState) -> System.out.println("State changed: " + oldState + newState)
        );
    }

    void testParameterType() {
        IPrintPerson printPerson = (Person person) -> {
            System.out.println("Person Name: " + person.getName() + ", Person Age: " + person.getAge());
        };
        Person person = new Person();
        person.setName("Cesar");
        person.setAge(27);
        printPerson.printPerson(person);
    }

    interface PrintService {
        void print(String message);
    }

    interface MathOperation {
        int execute(int x, int y);
    }

    interface MyListener {
        void listen(String olsState, String newState);
    }

    class ListenerRepo {
        public void addListener(MyListener listener) {
            System.out.println("Adding new listener");
            listener.listen("a", "b");
        }
    }

    interface IPrintPerson {
        void printPerson(Person person);
    }

    class Person {
        String name;
        int age;
        public String getName() { return this.name; }
        public int getAge() { return this.age; }
        public void setName(String name) { this.name = name; }
        public void setAge(int age) { this.age = age; }
    }

}
