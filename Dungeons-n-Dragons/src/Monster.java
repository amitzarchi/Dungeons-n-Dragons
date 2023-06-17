


public class Monster extends Enemy {
    
    private int visionRange;

    public Monster(String name, int healthPool, int attackPoints, int defensePoints, int experienceValue, int visionRange, char tile) {
        super(name, healthPool, attackPoints, defensePoints, experienceValue, tile);
        this.visionRange = visionRange;
    }
    
    public void initialize(Position position, BoardObserver observer) {
        super.initialize(position, observer);
    }

}
