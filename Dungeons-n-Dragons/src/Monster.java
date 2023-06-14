


public class Monster extends Enemie {
    
    private int visionRange;

    public Monster(String name, int healthPool, int attackPoints, int defensePoints, int experienceValue, int visionRange, Position position, char tile) {
        super(name, healthPool, attackPoints, defensePoints, experienceValue, position, tile);
        this.visionRange = visionRange;
    }
    
}
