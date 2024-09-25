package lab1;

public class Main {

    public static void main(String[] args) {

        Hero hero1 = new Hero("Иван", new MoveByFly(), PlaceType.Camp, PlaceType.Tower);
        hero1.move();
        hero1.setMovement(new MoveByWalk());
        hero1.move();
        hero1.setMovement(new MoveByHorse());
        hero1.move();
        Hero hero2 = new Hero("Петя", new MoveBySwim(), PlaceType.River, PlaceType.Town);
        hero2.move();
    }
}
