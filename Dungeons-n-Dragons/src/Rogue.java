


public class Rogue extends Player {

    private int cost;
    private int currentEnergy;

    public Rogue(String name, int healthPool, int attackPoints, int defensePoints, int cost, Position position) {
        super(name, healthPool, attackPoints, defensePoints, position);
        this.cost = cost;
        this.currentEnergy = 100;
    }
    
}
