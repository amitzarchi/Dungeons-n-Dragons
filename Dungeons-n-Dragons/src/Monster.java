


public class Monster extends Enemy {
    
    private int visionRange;

    public Monster(String name, int healthPool, int attackPoints, int defensePoints, int experienceValue, int visionRange, char tile) {
        super(name, healthPool, attackPoints, defensePoints, experienceValue, tile);
        this.visionRange = visionRange;
    }
    
    public void initialize(Position position, GameObserver observer) {
        super.initialize(position, observer);
    }

    public void onTurn() {
        Position playerPosition = getObserver().getPlayerPosition();
        if (playerPosition != null) {
            int distance = this.getPosition().distance(playerPosition);
            if (distance <= visionRange) {
                int dx = playerPosition.getX() - this.getPosition().getX();
                int dy = playerPosition.getY() - this.getPosition().getY();
                int absDx = Math.abs(dx);
                int absDy = Math.abs(dy);
                if (absDx > absDy) {
                    if (dx > 0) {
                        this.moveRight();
                    }
                    else {
                        this.moveLeft();
                    }
                }
                else {
                    if (dy > 0) {
                        this.moveDown();
                    }
                    else {
                        this.moveUp();
                    }
                }
            }
            else {
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
        }
    }


}
