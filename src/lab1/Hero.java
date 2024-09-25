package lab1;

public class Hero {

    String name = "";
    MovementStrategy movement;
    private final PlaceType placeFrom;
    private final PlaceType placeIn;
    public Hero(String name, MovementStrategy movement, PlaceType placeFrom, PlaceType placeIn)
    {
        this.placeFrom = placeFrom;
        this.placeIn = placeIn;
        this.name = name;
        this.movement = movement;
    }

    public void move(){
        System.out.println("Герой по имени " + name);
        movement.move();
        System.out.println("Из " + placeFrom + " в " + placeIn);
    }

    public void setMovement(MovementStrategy movement) {
        this.movement = movement;
    }
}
