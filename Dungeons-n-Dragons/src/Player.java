


public class Player extends Unit {

    protected int experience;
    protected int level;

    public Player(String name, int healthPool, int attackPoints, int defensePoints) {
        super(name, healthPool, attackPoints, defensePoints, '@');
        this.experience = 0;
        this.level = 1;
    }

    public void initialize(Position position, BoardObserver observer) {
        super.initialize(position, observer);
    }

    public void visit(Enemy enemy) {
        battle(enemy);
        if (enemy.getHealth().isDead()) {
            experience += enemy.getExperienceValue();
            enemy.onDeath();
            if (levelUpCheck()) {
                levelUp();
            }
        }
    }

    public void visit(Player player) {
        // Do nothing
    }

    public void accept(Unit unit) {
        unit.visit(this);
    }

    public void onDeath() {
        this.setChar('X');
        // GAME OVER
    }

    public void levelUp() {
        while (experience >= 50 * level) {
            level++;
            experience -= 50 * level;
            this.getHealth().increaseMaxHealth(10 * level);
            this.getHealth().SetHealthAmountToMax();
            this.increaseAttackPoints(4*level);
            this.increaseDefensePoints(level);
        }
    }

    public boolean levelUpCheck() {
        return experience >= 50 * level;
    }

}
