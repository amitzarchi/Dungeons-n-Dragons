
public class Warrior extends Player{
    private int abilityCooldown;
    private int remainingCooldown;

    public Warrior(String name, int healthPool, int attackPoints, int defensePoints, int abilityCooldown) {
        super(name, healthPool, attackPoints, defensePoints);
        this.abilityCooldown = abilityCooldown;
        this.remainingCooldown = 0;
    }

    public void initialize(Position position, GameObserver observer) {
        super.initialize(position, observer);
    }

    public void onGameTick() {
        if (remainingCooldown > 0) {
            remainingCooldown--;
        }
    }

    public void levelUp() {
        this.remainingCooldown = 0;
        getHealth().increaseMaxHealth(5*(level+1));
        increaseAttackPoints(2*(level+1));
        increaseDefensePoints((level+1));
        super.levelUp();
    }


    public void castAbility() {
        if (canCast()) {
            remainingCooldown = abilityCooldown;
            getHealth().heal(10*getDefensePoints());
            int damage =(int)((0.1)*getHealth().getHealthPool());
            getObserver().AttackInRange(this, 3, damage, false);
        }
        else {
            getObserver().abilityFailed( "Wait " + remainingCooldown + " turns for ability to cooldown");
        }
    }

    private boolean canCast() {
        return remainingCooldown == 0;
    }

    public String toString() {
        return super.toString() + " | ability cooldown: " + abilityCooldown + " | remaining cooldown: " + remainingCooldown;
    }

}
