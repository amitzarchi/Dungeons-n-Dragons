public class Boss extends Enemy implements HeroicUnit {

    private int visionRange;
    private int abilityFrequency;
    private int combatTicks;
    
    public Boss(String name, int healthPool, int attackPoints, int defensePoints, int experienceValue, char tile, int visionRange, int abilityFrequency) {
        super(name, healthPool, attackPoints, defensePoints, experienceValue, tile);
        this.visionRange = visionRange;
        this.abilityFrequency = abilityFrequency;
        this.combatTicks = 0;
    }

    public void initialize(Position position, GameObserver observer) {
        super.initialize(position, observer);
    }

    public void onTurn() {
        Position playerPosition = getObserver().getPlayerPosition();
        if (playerPosition != null) {
            int distance = this.getPosition().distance(playerPosition);
            if (distance <= visionRange) {
                if (combatTicks == abilityFrequency) {
                    castAbility();
                    combatTicks = 0;
                }
                else {
                    combatTicks++;
                    int dx = playerPosition.getX() - this.getPosition().getX();
                    int dy = playerPosition.getY() - this.getPosition().getY();
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
            }
            else {
                combatTicks = 0;
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

    public void castAbility() {
        getObserver().BossAbilityCast(this, getAttackPoints());
    }

}
