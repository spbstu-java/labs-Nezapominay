package lab1;

public class MoveByWalk implements MovementStrategy{


    @Override
    public void move() {
        System.out.println("Идёт пешком");
    }

    public String getDescription() {
        return "Идет пешком";
    }
}
