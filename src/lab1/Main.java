package lab1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Hero hero1 = new Hero("Иван", new MoveByFly(), PlaceType.Camp, PlaceType.Tower);

        System.out.println("Выберите как герой будет перемещаться " +
                "(ввод 0 прерывает программу): \n" +
                "1. Пешком \n" +
                "2. На лошади \n" +
                "3. Лететь \n" +
                "4. Плыть \n");
        Scanner scan = new Scanner(System.in);
        int changeStrategy = scan.nextInt();
        while (changeStrategy != 0)
        {
            switch (changeStrategy)
            {
                case 1:
                    hero1.setMovement(new MoveByWalk());
                    hero1.move();
                    break;
                case 2:
                    hero1.setMovement(new MoveByHorse());
                    hero1.move();
                    break;
                case 3:
                    hero1.setMovement(new MoveByFly());
                    hero1.move();
                    break;
                case 4:
                    hero1.setMovement(new MoveBySwim());
                    hero1.move();
                    break;
                default:
                    System.out.println("Введите корректное число " +
                            "(1-4 или 0 чтобы завершить): ");
            }
            changeStrategy = scan.nextInt();

        }
    }
}
