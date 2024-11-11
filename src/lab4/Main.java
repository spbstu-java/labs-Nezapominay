package lab4;


import java.util.*;



public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<String> words = new ArrayList<>();
        words.add("Hello");
        words.add("Home");
        words.add("Dog");
        int[] array = {1, 2, 3, 4, 5, 6};
        List<String> listForMap = List.of("1hello", "2world", "3Cat", "4HOUSE");

        System.out.println("Стрим возвращающий среднее значение чисел в списке: ");
        double average = Methods.streamAverage(numbers);
        System.out.println(average);

        System.out.println("Стрим приводящий в верхний регистр:");
        Methods.streamToUpper();

        System.out.println("Стрим возвращающий " +
                "уникальные числа и возводящий их в квадрат:");
        Methods.streamUniqueSquare();

        System.out.println("Стрим выводящий последний элемент или исключение:");
        System.out.println(Methods.streamLastElement(words));

        System.out.println("Стрим суммы четных чисел:");
        System.out.println(Methods.streamEvenNumbers(array));

        System.out.println("Преобразование строк в Map:");
        System.out.println(Methods.streamTransformToMap(listForMap));

    }

}
