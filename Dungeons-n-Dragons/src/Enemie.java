

public class Enemie extends Unit {
    private int experienceValue;

    public Enemie(String name, int healthPool, int attackPoints, int defensePoints, int experienceValue, Position position, char tile) {
        super(name, healthPool, attackPoints, defensePoints, position, tile);
        this.experienceValue = experienceValue;
    }
}
