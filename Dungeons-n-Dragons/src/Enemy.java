

public class Enemy extends Unit {
    private int experienceValue;

    public Enemy(String name, int healthPool, int attackPoints, int defensePoints, int experienceValue, char tile) {
        super(name, healthPool, attackPoints, defensePoints, tile);
        this.experienceValue = experienceValue;
    }

    public void initialize(Position position, BoardObserver observer) {
        super.initialize(position, observer);
    }

    public void onDeath() {
        this.delete();
    }

    public void accept(Unit unit) {
        unit.visit(this);
    }

    public void visit(Player player) {
        battle(player);
        if (player.getHealth().isDead()) {
            player.onDeath();
        }
    }

    public void visit(Enemy enemy) {
        // Do nothing
    }

    public int getExperienceValue() {
        return this.experienceValue;
    }
}
