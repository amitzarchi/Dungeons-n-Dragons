


public class Rogue extends Player {

    private int cost;
    private int currentEnergy;

    public Rogue(String name, int healthPool, int attackPoints, int defensePoints, int cost) {
        super(name, healthPool, attackPoints, defensePoints);
        this.cost = cost;
        this.currentEnergy = 100;
    }
    
    public void initialize(Position position, BoardObserver observer) {
        super.initialize(position, observer);
    }

}
