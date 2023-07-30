
public interface GameObserver {
    public void updatePosition(int oldX, int oldY, int newX, int newY);
    public void updateNewTile(Tile tile);
    public void updateDeleteTile(Tile tile);
    public void playerAbillityCast(Position position, int range, int damage, boolean hitEveryone);
    public void abilityFailed();
    public Tile getTile(Position position);
    public Player getPlayer();
    public Position getPlayerPosition();
    public void battleInformation(Unit attacker, Unit defender, int attackRoll, int defenseRoll, int damage);
    public void playerLeveledUp(Player player);
    public void enemyDeath(String name);
}
