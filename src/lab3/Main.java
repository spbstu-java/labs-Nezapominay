package lab3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Translator translator = new Translator();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите путь к файлу со словарем:");
        String filePath = scanner.nextLine();

        try {
            translator.loadDictionary(filePath);
            System.out.println("Словарь успешно загружен.");

            System.out.println("Введите текст для перевода:");
            String text = scanner.nextLine();
            String translation = translator.translate(text);

            System.out.println("Перевод:");
            System.out.println(translation);

        } catch (InvalidFileFormatException | FileReadException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}
