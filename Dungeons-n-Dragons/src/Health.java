

public class Health {
    int HealthPool;
    int HealthAmount;

    public Health(int health) {
        HealthPool = health;
        HealthAmount = health;
    }

    public int getHealthPool() {
        return HealthPool;
    }
    public int getHealthAmount() {
        return HealthAmount;
    }

    public void reduce(int damage) {
        HealthAmount -= damage;
        if (HealthAmount < 0) {
            HealthAmount = 0;
        }
    }

    public void heal(int amount) {
        HealthAmount += amount;
        if (HealthAmount > HealthPool) {
            HealthAmount = HealthPool;
        }
    }

    public void increaseMaxHealth(int amount) {
        HealthPool += amount;
    }

    public void SetHealthAmountToMax() {
        HealthAmount = HealthPool;
    }

    public boolean isDead() {
        return HealthAmount <= 0;
    }

    public String toString() {
        return " Health: " + HealthAmount + "/" + HealthPool;
    }
}
