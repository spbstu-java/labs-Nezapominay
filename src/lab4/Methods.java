package lab4;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Methods {
    public static Map<Character, String> streamTransformToMap(List<String> list) {
        return list.stream()
                .collect(Collectors.toMap(str -> str.charAt(0), str -> str.substring(1)));
    }

    public static int streamEvenNumbers(int[] numbers) {
        return Arrays.stream(numbers)
                .filter(x -> x % 2 == 0)
                .reduce(0, Integer::sum);
    }

    public static <T> T streamLastElement(Collection<T> collection) {
        return collection.stream()
                .reduce((x, y) -> y)
                .orElseThrow(() -> new NoSuchElementException("Ни одного элемента не найдено"));
    }

    public static void streamToUpper(List<String> words) {
                words.stream()
                .map(str -> str.toUpperCase())
                .map(str -> str = "_new_" + str)
                .forEach(System.out::println);
    }

    public static double streamAverage(List<Integer> numbers) {
        return numbers.stream().mapToDouble(Integer::doubleValue)
                .reduce(0, (x, y) -> x + y) / numbers.stream().count();

    }

    public static void streamUniqueSquare(List<Integer> numbers) {
        numbers.stream()
                .filter(n -> Collections.frequency(numbers, n) == 1)
                .map(n -> n * n)
                .toList()
                .forEach(System.out::println);
    }
}
