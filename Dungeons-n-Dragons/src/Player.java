


public class Player extends Unit {

    private int experience;
    private int level;

    public Player(String name, int healthPool, int attackPoints, int defensePoints, Position position) {
        super(name, healthPool, attackPoints, defensePoints, position, '@');
        this.experience = 0;
        this.level = 1;
    }
}
