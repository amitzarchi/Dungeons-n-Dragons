import java.util.List;

public abstract class Enemy extends Unit {
    private int experienceValue;

    public Enemy(String name, int healthPool, int attackPoints, int defensePoints, int experienceValue, char tile) {
        super(name, healthPool, attackPoints, defensePoints, tile);
        this.experienceValue = experienceValue;
    }

    public void initialize(Position position, GameObserver observer) {
        super.initialize(position, observer);
    }

    public void onDeath() {
        this.delete();
        getObserver().enemyDeath(getName());
    }

    public void interactAccept(Unit unit) {
        unit.interactVisit(this);
    }

    public void interactVisit(Player player) {
        battle(player);
        if (player.getHealth().isDead()) {
            player.onDeath();
        }
    }
    public void addIfEnemy(List<Enemy> enemies) {
        enemies.add(this);
    }

    public void interactVisit(Enemy enemy) {
        // Do nothing
    }

    public int getExperienceValue() {
        return this.experienceValue;
    }

    public abstract void onTurn();
}
