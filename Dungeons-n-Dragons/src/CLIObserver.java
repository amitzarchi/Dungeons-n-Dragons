public interface CLIObserver {
    public void printBoard();
    public void printGameOver();
    public void printWin();
    public void printAbilityFailed();
    public void printPlayerStats();
    public void printBattleInformation(Unit attacker, Unit defender, int attackRoll, int defenseRoll, int damage);
    public void printPlayerLeveledUp(Player player);
    public void printEnemyDeath(String name);
}
