import java.util.List;
import java.util.Random;

public abstract class Unit extends Tile {
    private String name;
    private Health health;
    private int attackPoints;
    private int defensePoints;

    public Unit(String name, int health, int attackPoints, int defensePoints, char tile) {

        super(tile);

        this.name = name;
        this.health = new Health(health);
        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;
    }

     public void initialize(Position position, GameObserver observer) {
        super.initialize(position, observer);
    }

    public String getName() {
        return this.name;
    }

    public Health getHealth() {
        return this.health;
    }

    public void moveUp() {
        interact(getObserver().getTile(getPosition().up()));
    }

    public void moveDown() {
        interact(getObserver().getTile(getPosition().down()));
    }

    public void moveLeft() {
        interact(getObserver().getTile(getPosition().left()));
    }

    public void moveRight() {
        interact(getObserver().getTile(getPosition().right()));
    }

    public void interact(Tile tile) {
        tile.interactAccept(this);
    }

    public void interactVisit(Empty e) {
        this.setPosition(e.getPosition());
    }

    public void interactVisit(Wall w) {
        // Do nothing
    }   
    
    public abstract void interactVisit(Player p);
    public abstract void interactVisit(Enemy e);


    public void battle(Unit u){
        int attackRoll = this.rollAttack();
        int defenseRoll = u.rollDefence();
        int damage = Math.max(0, attackRoll - defenseRoll);
        u.getHealth().reduce(damage);
        getObserver().battleInformation(this, u, attackRoll, defenseRoll, damage);
    }
    public void TakeAHit(int attackValue){
        int defenseRoll = rollDefence();
        int damage = Math.max(0, attackValue - defenseRoll);
        getHealth().reduce(damage);
    }

    public int rollAttack() {
        Random rand = new Random();
        int attackRoll = rand.nextInt(attackPoints);
        return attackRoll;
    }

    public int rollDefence() {
        Random rand = new Random();
        int defenseRoll = rand.nextInt(defensePoints);
        return defenseRoll;
    }

    public abstract void onDeath();

    public int getAttackPoints() {
        return this.attackPoints;
    }
    public int getDefensePoints() {
        return this.defensePoints;
    }

    public void increaseAttackPoints(int amount) {
        this.attackPoints += amount;
    }   
    public void increaseDefensePoints(int amount) {
        this.defensePoints += amount;
    }
    public abstract void addIfEnemy(List<Enemy> enemies);

    public String toString() {
        return name + " | " + health.toString() + " | attack: " + attackPoints + " | defense: " + defensePoints;
    }
    
}
