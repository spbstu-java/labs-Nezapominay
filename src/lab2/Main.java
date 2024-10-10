package lab2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main {

    public static void main(String[] args) {

        MyClass myClass = new MyClass();

        Method[] methods = MyClass.class.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(TestAnnotation.class)) {
                TestAnnotation annotation = method.getAnnotation(TestAnnotation.class);
                int mod = method.getModifiers();
                if (Modifier.isPrivate(mod) || Modifier.isProtected(mod)) {
                    int repeatCount = annotation.value();
                    method.setAccessible(true);

                    for (int i = 0; i < repeatCount; i++) {
                        try {
                            if (method.getParameterCount() == 0) {
                                method.invoke(myClass);
                            } else {
                                Object[] param = new Object[method.getParameterCount()];
                                Class<?>[] paramTypes = method.getParameterTypes();

                                // Заполнение параметрами по умолчанию
                                for (int j = 0; j < paramTypes.length; j++) {
                                    if (paramTypes[j] == int.class) {
                                        param[j] = 0;
                                    } else if (paramTypes[j] == double.class) {
                                        param[j] = 0.0;
                                    } else if (paramTypes[j] == boolean.class) {
                                        param[j] = false;
                                    } else {
                                        param[j] = null;
                                    }
                                }

                                method.invoke(myClass, param);
                            }
                        } catch (IllegalAccessException |
                                 InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }


    }
}
