


public class Trap extends Enemie {
    
    private int visibilityTime;
    private int invisibilityTime;
    private int ticksCount;
    private boolean visible;

    public Trap(String name, int healthPool, int attackPoints, int defensePoints, int experienceValue, int visibilityTime, int invisibilityTime, Position position, char tile) {
        super(name, healthPool, attackPoints, defensePoints, experienceValue, position, tile);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        this.ticksCount = 0;
        this.visible = true;
    }
    
}
