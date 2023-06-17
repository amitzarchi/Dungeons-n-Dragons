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

     public void initialize(Position position, BoardObserver observer) {
        super.initialize(position, observer);
    }

    public Health getHealth() {
        return this.health;
    }

    public void interact(Tile tile) {
        tile.accept(this);
    }

    public void visit(Empty e) {
        this.setPosition(e.getPosition());
    }

    public void visit(Wall w) {
        // Do nothing
    }   
    
    public abstract void visit(Player p);
    public abstract void visit(Enemy e);


    public void battle(Unit u){
        int attackRoll = this.attack();
        int defenseRoll = u.defend();
        int damage = Math.max(0, attackRoll - defenseRoll);
        u.getHealth().reduce(damage);
    }

    public int attack() {
        Random rand = new Random();
        int attackRoll = rand.nextInt(attackPoints);
        return attackRoll;
    }

    public int defend() {
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

    
}
