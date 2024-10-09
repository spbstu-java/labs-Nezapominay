package lab2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) {

        MyClass myClass = new MyClass();

        Method[] methods = MyClass.class.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(TestAnnotation.class)) {
                TestAnnotation annotation = method.getAnnotation(TestAnnotation.class);
                int repeatCount = annotation.value();
                method.setAccessible(true);

                for (int i = 0; i < repeatCount; i++) {
                    try {
                        method.invoke(myClass, 10, 20);
                    } catch (IllegalAccessException |
                             InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }




    }
}
