import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

    public static void main(String[] args) {
        System.out.println("Stream Test");
        StreamTest _this = new StreamTest();
        _this.internalIteration();
    }

    void internalIteration() {
        System.out.println("--- Internal Iteration ---");

        // First example
        List<Integer> numbers = new ArrayList<>();

        for(int i = 0; i < 10; i++) {
            numbers.add(i);
        }

        long count = numbers.stream()
                .filter(number -> {
                    boolean isGreater = number > 5;
                    System.out.println("number: " + number + ", is it greater than 5? " + isGreater);
                    return isGreater;
                })
                .count();

        System.out.println("How many numbers in the list that are greater than 5: " + count);

        // Collect
        List<Integer> numbersGtFive = numbers.stream()
                .filter(number -> number > 5)
                .collect(Collectors.toList());

        System.out.println(numbersGtFive);

        // Map
        List<String> strings = Stream.of("barcelona", "madrid", "sevilla")
                .map(string -> string.toUpperCase())
                .collect(Collectors.toList());

        System.out.println("Map -> " + strings);

        // forEach
        strings.stream()
                .map(string -> string.toLowerCase())
                .forEach(System.out::println);

    }

}
