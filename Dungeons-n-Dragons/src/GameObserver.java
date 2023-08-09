import java.util.Map;

public interface GameObserver {
    public void updatePosition(int oldX, int oldY, int newX, int newY);
    public void updateNewTile(Tile tile);
    public void updateDeleteTile(Tile tile);
    public void AttackInRange(Unit attacker, int range, int damage, boolean hitEveryone);
    public void MageAttack(Unit attacker, int range, int hits, int attackPoints);
    public boolean HunterAbillityCast(Unit attacker, int range, int damage);
    public void BossAbilityCast(Unit attacker, int damage);
    public void AbilityCastInfo(Unit attacker, Map<Unit, Integer> battleInformation);
    public void abilityFailed(String message);
    public Tile getTile(Position position);
    public Player getPlayer();
    public Position getPlayerPosition();
    public void battleInformation(Unit attacker, Unit defender, int attackRoll, int defenseRoll, int damage);
    public void playerLeveledUp(Player player);
    public void playerSpawn(Player player);
    public void enemySpawn(Enemy enemy);
    public void enemyDeath(String name);
}
