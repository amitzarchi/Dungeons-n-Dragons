


public class Warrior extends Player{
    private int abilityCooldown;
    private int remainingCooldown;

    public Warrior(String name, int healthPool, int attackPoints, int defensePoints, int abilityCooldown, Position position) {
        super(name, healthPool, attackPoints, defensePoints, position);
        this.abilityCooldown = abilityCooldown;
        this.remainingCooldown = 0;
    }
}
