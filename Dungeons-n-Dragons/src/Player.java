import java.util.List;

public class Player extends Unit {

    protected int experience;
    protected int level;

    public Player(String name, int healthPool, int attackPoints, int defensePoints) {
        super(name, healthPool, attackPoints, defensePoints, '@');
        this.experience = 0;
        this.level = 1;
    }

    public void initialize(Position position, GameObserver observer) {
        super.initialize(position, observer);
    }

    public void interactVisit(Enemy enemy) {
        battle(enemy);
        if (enemy.getHealth().isDead()) {
            experience += enemy.getExperienceValue();
            enemy.onDeath();
            if (levelUpCheck()) {
                levelUp();
            }
        }
    }

    public void interactVisit(Player player) {
        // Do nothing
    }

    public void interactAccept(Unit unit) {
        unit.interactVisit(this);
    }
    
    public void addIfEnemy(List<Enemy> enemies) {
        // Do nothing
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
