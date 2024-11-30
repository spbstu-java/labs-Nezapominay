package lab3;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Translator {
    private Map<String, String> dictionary = new LinkedHashMap<>();
    private boolean isDictionaryLoaded = false;

    public void loadDictionary(String filePath) throws InvalidFileFormatException, FileReadException {
        dictionary.clear();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                String[] parts = line.split("\\|");
                if (parts.length != 2) {
                    throw new InvalidFileFormatException("Неверный формат строки в словаре: " + line);
                }
                dictionary.put(parts[0].trim().toLowerCase(), parts[1].trim());
            }
            isDictionaryLoaded = true;
        } catch (NoSuchFileException e) {
            throw new FileReadException("Файл не найден: " + filePath);
        } catch (IOException e) {
            throw new FileReadException("Ошибка при чтении файла: " + filePath);
        }
    }

    public boolean isDictionaryLoaded() {
        return isDictionaryLoaded;
    }

    public String translate(String text) {
        if (!isDictionaryLoaded) {
            return "";
        }

        String[] words = text.split("\\s+");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String longestMatch = null;
            String translation = words[i];

            for (String key : dictionary.keySet()) {
                String[] keyWords = key.split("\\s+");
                if (matches(words, i, keyWords)) {
                    if (longestMatch == null || keyWords.length > longestMatch.split("\\s+").length) {
                        longestMatch = key;
                    }
                }
            }

            if (longestMatch != null) {
                translation = dictionary.get(longestMatch);
                i += longestMatch.split("\\s+").length - 1;
            }

            result.append(translation).append(" ");
        }

        return result.toString().trim();
    }

    private boolean matches(String[] words, int start, String[] keyWords) {
        if (start + keyWords.length > words.length) return false;
        for (int j = 0; j < keyWords.length; j++) {
            if (!words[start + j].equalsIgnoreCase(keyWords[j])) {
                return false;
            }
        }
        return true;
    }
}
