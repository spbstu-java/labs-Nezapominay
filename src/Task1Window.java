import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import lab1.Hero;
import lab1.PlaceType;
import  lab1.MoveByWalk;
import  lab1.MoveByFly;
import  lab1.MoveBySwim;
import  lab1.MoveByHorse;

public class Task1Window extends JFrame {
    private Hero hero;
    private JComboBox<String> movementSelector;
    private JTextArea outputArea;

    public Task1Window() {
        setTitle("Задание 1: Герой");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        hero = new Hero("Иван", new MoveByFly(), PlaceType.Camp, PlaceType.Tower);

        String[] movements = {"Пешком", "На лошади", "Лететь", "Плыть"};
        movementSelector = new JComboBox<>(movements);
        add(movementSelector, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);


        JButton moveButton = new JButton("Переместить героя");
        add(moveButton, BorderLayout.SOUTH);

        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = movementSelector.getSelectedIndex();
                switch (selectedIndex) {
                    case 0:
                        hero.setMovement(new MoveByWalk());
                        break;
                    case 1:
                        hero.setMovement(new MoveByHorse());
                        break;
                    case 2:
                        hero.setMovement(new MoveByFly());
                        break;
                    case 3:
                        hero.setMovement(new MoveBySwim());
                        break;
                }
                updateOutput();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateOutput() {
        StringBuilder output = new StringBuilder();
        output.append("Герой по имени ").append(hero.getName()).append("\n");
        output.append(hero.getMovementDescription()).append("\n");
        output.append("Из ").append(hero.getPlaceFrom()).append(" в ").append(hero.getPlaceIn()).append("\n");
        outputArea.setText(output.toString());
    }
}