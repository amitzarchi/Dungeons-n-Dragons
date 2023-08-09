public class Hunter extends Player {

    private int range;
    private int arrowsCount;
    private int ticksCount;

    public Hunter(String name, int healthPool, int attackPoints, int defensePoints, int range) {
        super(name, healthPool, attackPoints, defensePoints);
        this.range = range;
        this.arrowsCount = 10;
        this.ticksCount = 0;
    }

    public void initialize(Position position, GameObserver observer) {
        super.initialize(position, observer);
    }

    public void onGameTick() {
        if (ticksCount == 10) {
            arrowsCount++;
            ticksCount = 0;
        }
        else {
            ticksCount++;
        }
    }

    public void levelUp() {
        super.levelUp();
        arrowsCount += level;
        increaseAttackPoints(2*level);
        increaseDefensePoints(level);
    }

    public void castAbility() {
        if (!CanCast()) {
            getObserver().abilityFailed("No arrows left");
        }
        else {
            if (getObserver().HunterAbillityCast(this, range, getAttackPoints()))
                arrowsCount--;
        }
    }

    public String toString() {
        return super.toString() + " | arrows: " + arrowsCount;
    }     

    private boolean CanCast() {
        return arrowsCount > 0;
    }
}
