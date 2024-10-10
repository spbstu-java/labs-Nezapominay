package lab2;

public class MyClass {

    @TestAnnotation(1)
    public void methodPublic1(int a, int b)
    {
        System.out.println("Первый публичный метод с параметрами: " + a + " " + b);
    }

    public void methodPublic2(int a, int b)
    {
        System.out.println("Второй публичный метод с параметрами: " + a + " " + b);
    }

    private void methodPrivate1(int a, int b)
    {
        System.out.println("Первый приватный метод с параметрами: " + a + " " + b);
    }

    @TestAnnotation(2)
    private void methodPrivate2(int a, double b)
    {
        System.out.println("Второй приватный метод с параметрами: " + a + " " + b);
    }

    @TestAnnotation(1)
    protected void methodProtected1(int a, int b)
    {
        System.out.println("Первый защищённый метод с параметрами: " + a + " " + b);
    }

    @TestAnnotation(5)
    protected void methodProtected2(boolean a, int b)
    {
        System.out.println("Второй защищённый метод с параметрами: " + a + " " + b);
    }
}
