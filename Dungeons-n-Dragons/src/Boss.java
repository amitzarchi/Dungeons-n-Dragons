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
                    moveForwardToPlayer();
                }
            }
            else {
                combatTicks = 0;
                randomMove();
            }

        }
    }

    public void castAbility() {
        getObserver().BossAbilityCast(this, getAttackPoints());
    }

}
