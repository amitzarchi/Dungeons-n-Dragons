


public class Mage extends Player {
    private int manaPool;
    private int currentMana;
    private int manaCost;
    private int spellPower;
    private int hitsCount;
    private int abilityRange;

    public Mage(String name, int healthPool, int attackPoints, int defensePoints, int manaPool, int manaCost, int spellPower, int hitsCount, int abilityRange) {
        super(name, healthPool, attackPoints, defensePoints);
        this.manaPool = manaPool;
        this.currentMana = manaPool / 4;
        this.manaCost = manaCost;
        this.spellPower = spellPower;
        this.hitsCount = hitsCount;
        this.abilityRange = abilityRange;
    }
    
    public void initialize(Position position, GameObserver observer) {
        super.initialize(position, observer);
    }

    public void onGameTick() {
        currentMana = Math.min(manaPool, currentMana + level);
    }

    public void castAbility(){
       if (canCast()) {
            currentMana -= manaCost;
            getObserver().MageAttack(this, abilityRange, hitsCount, spellPower);
            }
        
        else {
            getObserver().abilityFailed("Not enough mana");
        }
    }

    public void levelUp() {
        manaPool += 25 * (level+1);
        currentMana = Math.min(manaPool, currentMana + (manaPool / 4));
        spellPower += 10 * (level+1);
        super.levelUp();
    }
    
    private boolean canCast() {
        return currentMana >= manaCost;
    }

    public String toString() {
        return super.toString() + " | mana: " + currentMana + "/" + manaPool;
    }

}
