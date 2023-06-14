


public class Unit extends Tile{
    private String name;
    private Health health;
    private int attack;
    private int defense;

    public Unit(String name, int health, int attack, int defense, Position position, char tile) {

        super(tile, position);

        this.name = name;
        this.health = new Health(health);
        this.attack = attack;
        this.defense = defense;
    }
}
