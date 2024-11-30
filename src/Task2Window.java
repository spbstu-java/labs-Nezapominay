import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import lab2.MyClass;
import lab2.TestAnnotation;

public class Task2Window extends JFrame {
    private JTextArea outputArea;

    public Task2Window() {
        setTitle("Задание 2: Вызов аннотированных методов");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton executeButton = new JButton("Выполнить");
        executeButton.addActionListener(e -> executeAnnotatedMethods());
        add(executeButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void executeAnnotatedMethods() {
        outputArea.setText("");
        MyClass myClass = new MyClass();

        Method[] methods = MyClass.class.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(TestAnnotation.class)) {
                TestAnnotation annotation = method.getAnnotation(TestAnnotation.class);
                int repeatCount = annotation.value();
                int modifiers = method.getModifiers();

                if (Modifier.isProtected(modifiers) || Modifier.isPrivate(modifiers)) {
                    method.setAccessible(true);

                    for (int i = 0; i < repeatCount; i++) {
                        try {
                            Object[] parameters = generateDefaultParameters(method);

                            outputArea.append("Вызов метода: " + method.getName() + "\n");
                            method.invoke(myClass, parameters);

                        } catch (IllegalAccessException | InvocationTargetException ex) {
                            outputArea.append("Ошибка при вызове метода: " + ex.getMessage() + "\n");
                        }
                    }
                }
            }
        }
    }

    private Object[] generateDefaultParameters(Method method) {
        Class<?>[] paramTypes = method.getParameterTypes();
        Object[] parameters = new Object[paramTypes.length];

        for (int i = 0; i < paramTypes.length; i++) {
            if (paramTypes[i] == int.class) {
                parameters[i] = 0;
            } else if (paramTypes[i] == double.class) {
                parameters[i] = 0.0;
            } else if (paramTypes[i] == boolean.class) {
                parameters[i] = false;
            } else {
                parameters[i] = null;
            }
        }

        return parameters;
    }
}