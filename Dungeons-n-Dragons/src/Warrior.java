import java.util.Random;

public class Warrior extends Player{
    private int abilityCooldown;
    private int remainingCooldown;

    public Warrior(String name, int healthPool, int attackPoints, int defensePoints, int abilityCooldown) {
        super(name, healthPool, attackPoints, defensePoints);
        this.abilityCooldown = abilityCooldown;
        this.remainingCooldown = 0;
    }

    public void initialize(Position position, BoardObserver observer) {
        super.initialize(position, observer);
    }

    public void levelUp() {
        super.levelUp();
        this.remainingCooldown = 0;
        getHealth().increaseMaxHealth(5*level);
        increaseAttackPoints(2*level);
        increaseDefensePoints(level);
    }

    public void castAbility() {
        if (remainingCooldown == 0) {
            remainingCooldown = abilityCooldown;
            getHealth().heal(10*getDefensePoints());
            Random rand = new Random();
            int damage = rand.nextInt((int)0.1*getHealth().getHealthPool());
            BoardObserver o = getObserver();
            o.attackNeighbours(getPosition(), damage);

        }

    
    }


}
