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
                this.moveForwardToPlayer();
            }
            else {
                this.randomMove();
            }
        }
    }
}
