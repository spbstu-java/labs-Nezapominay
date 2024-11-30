import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import lab3.Translator;
import lab3.FileReadException;
import lab3.InvalidFileFormatException;

public class Task3Window extends JFrame {
    private JTextField dictionaryPathField;
    private JTextField textPathField;
    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    private Translator translator;

    public Task3Window() {
        setTitle("Задание 3: Перевод текста");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 600);
        setLayout(new BorderLayout());

        translator = new Translator();

        JPanel topPanel = new JPanel(new GridLayout(2, 1));

        JPanel dictionaryPanel = new JPanel(new BorderLayout());
        dictionaryPathField = new JTextField();
        dictionaryPathField.setEditable(false);
        JButton chooseDictionaryButton = new JButton("Выбрать словарь");
        chooseDictionaryButton.addActionListener(e -> chooseDictionaryFile());
        dictionaryPanel.add(new JLabel("Словарь: "), BorderLayout.WEST);
        dictionaryPanel.add(dictionaryPathField, BorderLayout.CENTER);
        dictionaryPanel.add(chooseDictionaryButton, BorderLayout.EAST);
        topPanel.add(dictionaryPanel);

        JPanel textPanel = new JPanel(new BorderLayout());
        textPathField = new JTextField();
        textPathField.setEditable(false);
        JButton chooseTextButton = new JButton("Выбрать текст");
        chooseTextButton.addActionListener(e -> chooseTextFile());
        textPanel.add(new JLabel("Текст: "), BorderLayout.WEST);
        textPanel.add(textPathField, BorderLayout.CENTER);
        textPanel.add(chooseTextButton, BorderLayout.EAST);
        topPanel.add(textPanel);

        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        inputTextArea = new JTextArea();
        inputTextArea.setLineWrap(true);
        inputTextArea.setWrapStyleWord(true);
        JScrollPane inputScroll = new JScrollPane(inputTextArea);
        inputScroll.setBorder(BorderFactory.createTitledBorder("Введите текст для перевода"));

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setLineWrap(true);
        outputTextArea.setWrapStyleWord(true);
        JScrollPane outputScroll = new JScrollPane(outputTextArea);
        outputScroll.setBorder(BorderFactory.createTitledBorder("Переведённый текст"));

        centerPanel.add(inputScroll);
        centerPanel.add(outputScroll);
        add(centerPanel, BorderLayout.CENTER);

        JButton translateButton = new JButton("Перевести");
        translateButton.addActionListener(e -> translateText());
        add(translateButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void chooseDictionaryFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Выберите файл словаря");

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            dictionaryPathField.setText(selectedFile.getAbsolutePath());

            try {
                translator.loadDictionary(selectedFile.getAbsolutePath());
                JOptionPane.showMessageDialog(this, "Словарь успешно загружен.", "Успех", JOptionPane.INFORMATION_MESSAGE);
            } catch (InvalidFileFormatException | FileReadException e) {
                JOptionPane.showMessageDialog(this, "Ошибка: " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void chooseTextFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Выберите файл текста");

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            textPathField.setText(selectedFile.getAbsolutePath());

            try {
                String text = Files.readString(selectedFile.toPath());
                inputTextArea.setText(text);
                JOptionPane.showMessageDialog(this, "Текст успешно загружен.", "Успех", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Ошибка при чтении файла: " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void translateText() {
        String inputText = inputTextArea.getText().trim();
        if (inputText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Введите текст для перевода или загрузите файл текста.", "Ошибка", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!translator.isDictionaryLoaded()) {
            JOptionPane.showMessageDialog(this, "Словарь не загружен.", "Ошибка", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String translatedText = translator.translate(inputText);
        outputTextArea.setText(translatedText);
    }
}