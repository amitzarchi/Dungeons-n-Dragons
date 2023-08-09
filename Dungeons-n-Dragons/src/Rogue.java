


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
        if (currentEnergy >= cost) {
            currentEnergy -= cost;
            getObserver().AttackInRange(this, 2, getAttackPoints(), true);
        }
        else {
            getObserver().abilityFailed("Not enough energy");
        }
    }
}
