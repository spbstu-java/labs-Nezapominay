import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApp extends JFrame {
    private JComboBox<String> appSelector;
    private JButton runButton;

    public MainApp() {
        setTitle("Выбор приложения");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new BorderLayout());

        String[] apps = {"Задание 1", "Задание 2", "Задание 3", "Задание 4"};
        appSelector = new JComboBox<>(apps);
        add(appSelector, BorderLayout.CENTER);

        runButton = new JButton("Запустить");
        add(runButton, BorderLayout.SOUTH);

        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedApp = (String) appSelector.getSelectedItem();
                launchApp(selectedApp);
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void launchApp(String appName) {
        switch (appName) {
            case "Задание 1":
                new Task1Window();
                break;
            case "Задание 2":
                new Task2Window();
                break;
            case "Задание 3":
                new Task3Window();
                break;
            case "Задание 4":
                new Task4Window();
                break;
            default:
                JOptionPane.showMessageDialog(this, "Неизвестное задание");
        }
    }

    public static void main(String[] args) {
        new MainApp();
    }
}