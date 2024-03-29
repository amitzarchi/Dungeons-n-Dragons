import java.util.Scanner;

public abstract class Player extends Unit implements HeroicUnit{

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
    public void initializeAndNotify(Position position, GameObserver observer) {
        super.initialize(position, observer);
        notifyNewTile();
        getObserver().playerSpawn(this);
    }

    public void onTurn() {
        onGameTick();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        switch (input) {
            case "w":
                moveUp();
                break;
            case "a":
                moveLeft();
                break;
            case "s":
                moveDown();
                break;
            case "d":
                moveRight();
                break;
            case "e":
                castAbility();
                break;
            case "q":
                break;
            default:
                onTurn();
        }
    }

    public void interactVisit(Enemy enemy) {
        battle(enemy);
        if (enemy.getHealth().isDead()) {
            increaseExperience(enemy.getExperienceValue());
            enemy.onDeath();
        }
    }

    public void interactVisit(Player player) {
        // Do nothing
    }

    public void interactAccept(Unit unit) {
        unit.interactVisit(this);
    }

    public void onDeath() {
        this.setChar('X');
        // GAME OVER
    }

    public void levelUp() {
        while (experience >= 50 * level) {
            experience -= 50 * level;
            level++;
            this.getHealth().increaseMaxHealth(10 * level);
            this.getHealth().SetHealthAmountToMax();
            this.increaseAttackPoints(4*level);
            this.increaseDefensePoints(level);
        }
        if (experience < 0) {
            experience = 0;
        }
        getObserver().playerLeveledUp(this);
    }

    public boolean levelUpCheck() {
        
        return experience >= 50 * level;
    }

    public abstract void castAbility();
    public String toString() {
        return super.toString() + " | experience: " + experience + "/" + 50 * level +" | level: " + level;
    }

    public int getLevel() {
        return level;
    }

    public void increaseExperience(int amount) {
        this.experience += amount;
        if (levelUpCheck()) {
            levelUp();
        }
    }

    public abstract void onGameTick();
}
