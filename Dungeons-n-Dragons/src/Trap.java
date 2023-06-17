


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
    
    public void initialize(Position position, BoardObserver observer) {
        super.initialize(position, observer);
    }
}
