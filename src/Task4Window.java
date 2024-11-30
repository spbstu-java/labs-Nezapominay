import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import lab4.Methods;

public class Task4Window extends JFrame {

    private final JComboBox<String> methodSelector;
    private final JPanel inputPanel;
    private final JTextArea outputArea;

    public Task4Window() {
        setTitle("Задание 4: Вызов методов");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setLayout(new BorderLayout());

        methodSelector = new JComboBox<>(new String[]{
                "streamAverage",
                "streamToUpper",
                "streamUniqueSquare",
                "streamLastElement",
                "streamEvenNumbers",
                "streamTransformToMap"
        });

        inputPanel = new JPanel(new GridLayout(0, 2, 10, 10));

        JButton executeButton = new JButton("Выполнить");
        executeButton.addActionListener(e -> executeMethod());

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(new JLabel("Выберите метод:"), BorderLayout.WEST);
        topPanel.add(methodSelector, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(inputPanel, BorderLayout.NORTH);
        centerPanel.add(executeButton, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        methodSelector.addActionListener(e -> updateInputFields());
        updateInputFields();

        setVisible(true);
    }

    private void updateInputFields() {
        inputPanel.removeAll();

        String selectedMethod = (String) methodSelector.getSelectedItem();
        switch (selectedMethod) {
            case "streamAverage":
                inputPanel.add(new JLabel("Введите список чисел (через запятую):"));
                inputPanel.add(new JTextField());
                break;
            case "streamToUpper":
                inputPanel.add(new JLabel("Введите список слов (через запятую):"));
                inputPanel.add(new JTextField());
                break;
            case "streamUniqueSquare":
                inputPanel.add(new JLabel("Введите список чисел (через запятую):"));
                inputPanel.add(new JTextField());
                break;
            case "streamLastElement":
                inputPanel.add(new JLabel("Введите список строк (через запятую):"));
                inputPanel.add(new JTextField());
                break;
            case "streamEvenNumbers":
                inputPanel.add(new JLabel("Введите массив чисел (через запятую):"));
                inputPanel.add(new JTextField());
                break;
            case "streamTransformToMap":
                inputPanel.add(new JLabel("Введите список строк (через запятую):"));
                inputPanel.add(new JTextField());
                break;
        }

        inputPanel.revalidate();
        inputPanel.repaint();
    }

    private void executeMethod() {
        String selectedMethod = (String) methodSelector.getSelectedItem();
        Component[] components = inputPanel.getComponents();
        String input = "";

        for (Component comp : components) {
            if (comp instanceof JTextField) {
                input = ((JTextField) comp).getText();
                break;
            }
        }

        try {
            String result;

            switch (selectedMethod) {
                case "streamAverage":
                    List<Integer> numbers = Arrays.stream(input.split(","))
                            .map(String::trim)
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());
                    result = "Среднее значение: " + Methods.streamAverage(numbers);
                    break;
                case "streamToUpper":
                    List<String> words = Arrays.stream(input.split(","))
                            .map(String::trim)
                            .collect(Collectors.toList());
                    result = "Слова в верхнем регистре:\n" + Methods.streamToUpper(words);
                    break;
                case "streamUniqueSquare":
                    List<Integer> uniqueNumbers = Arrays.stream(input.split(","))
                            .map(String::trim)
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());
                    result = "Квадраты уникальных чисел:\n" + Methods.streamUniqueSquare(uniqueNumbers);
                    break;
                case "streamLastElement":
                    List<String> elements = Arrays.stream(input.split(","))
                            .map(String::trim)
                            .collect(Collectors.toList());
                    result = "Последний элемент: " + Methods.streamLastElement(elements);
                    break;
                case "streamEvenNumbers":
                    int[] array = Arrays.stream(input.split(","))
                            .map(String::trim)
                            .mapToInt(Integer::parseInt)
                            .toArray();
                    result = "Сумма чётных чисел: " + Methods.streamEvenNumbers(array);
                    break;
                case "streamTransformToMap":
                    List<String> listForMap = Arrays.stream(input.split(","))
                            .map(String::trim)
                            .collect(Collectors.toList());
                    result = "Преобразование в Map:\n" + Methods.streamTransformToMap(listForMap);
                    break;
                default:
                    result = "Неизвестный метод.";
                    break;
            }

            outputArea.setText(result);
        } catch (Exception e) {
            outputArea.setText("Ошибка при выполнении метода: " + e.getMessage());
        }
    }
}