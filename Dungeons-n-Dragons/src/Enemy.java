
public abstract class Enemy extends Unit {
    private int experienceValue;

    public Enemy(String name, int healthPool, int attackPoints, int defensePoints, int experienceValue, char tile) {
        super(name, healthPool, attackPoints, defensePoints, tile);
        this.experienceValue = experienceValue;
    }

    public void initialize(Position position, GameObserver observer) {
        super.initialize(position, observer);
    }

    public void initializeAndNotify(Position position, GameObserver observer) {
        super.initialize(position, observer);
        notifyNewTile();
        getObserver().enemySpawn(this);
    }

    public void onDeath() {
        this.delete();
        getObserver().enemyDeath(this);
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

    public void interactVisit(Enemy enemy) {
        // Do nothing
    }

    public int getExperienceValue() {
        return this.experienceValue;
    }

    public void moveForwardToPlayer() {
        Position playerPosition = getObserver().getPlayerPosition();
        int dx = this.getPosition().getX() - playerPosition.getX();
        int dy = this.getPosition().getY() - playerPosition.getY();
        int absDx = Math.abs(dx);
        int absDy = Math.abs(dy);
        if (absDx > absDy) {
            if (dx > 0) {
                this.moveLeft();
            }
            else {
                this.moveRight();
            }
        }
        else {
            if (dy > 0) {
                this.moveUp();
            }
            else {
                this.moveDown();
            }
        }

    }

    public void randomMove() {
        int direction = (int) (Math.random() * 5);
        switch (direction) {
            case 0:
                this.moveUp();
                break;
            case 1:
                this.moveDown();
                break;
            case 2:
                this.moveLeft();
                break;
            case 3:
                this.moveRight();
                break;
            case 4:
                break;
        }
    }

    public abstract void onTurn();
}
