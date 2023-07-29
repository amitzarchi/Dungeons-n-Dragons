


public class Trap extends Enemy {
    
    private int visibilityTime;
    private int invisibilityTime;
    private int ticksCount;
    private boolean visible;

    public Trap(String name, int healthPool, int attackPoints, int defensePoints, int experienceValue, int visibilityTime, int invisibilityTime, char tile) {
        super(name, healthPool, attackPoints, defensePoints, experienceValue, tile);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        this.ticksCount = 0;
        this.visible = true;
    }
    
    public void initialize(Position position, GameObserver observer) {
        super.initialize(position, observer);
    }

    public void onTurn() {
        visible = ticksCount < visibilityTime;
        if (ticksCount == visibilityTime + invisibilityTime) {
            ticksCount = 0;
        }
        else {
            ticksCount++;
        }
        if (getPosition().distance(getObserver().getPlayerPosition()) == 1) {
            battle(getObserver().getPlayer());
        }
    }
}

