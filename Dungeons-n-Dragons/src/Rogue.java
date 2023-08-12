


public class Rogue extends Player {

    private int cost;
    private int currentEnergy;

    public Rogue(String name, int healthPool, int attackPoints, int defensePoints, int cost) {
        super(name, healthPool, attackPoints, defensePoints);
        this.cost = cost;
        this.currentEnergy = 100;
    }
    
    public void initialize(Position position, GameObserver observer) {
        super.initialize(position, observer);
    }

    public void onGameTick() {
        currentEnergy = Math.min(100, currentEnergy + 10);
    }

    public void castAbility() {
        if (canCast()) {
            currentEnergy -= cost;
            getObserver().AttackInRange(this, 2, getAttackPoints(), true);
        }
        else {
            getObserver().abilityFailed("Not enough energy");
        }
    }

    public void levelUp() {
        currentEnergy = 100;
        increaseAttackPoints(3*(level+1));
        super.levelUp();
    }

    private boolean canCast() {
        return currentEnergy >= cost;
    }

    public String toString() {
        return super.toString() + " | energy: " + currentEnergy + "/" + 100;
    }
}
