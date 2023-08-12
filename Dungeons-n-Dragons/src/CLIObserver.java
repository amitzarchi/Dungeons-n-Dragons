import java.util.Map;

public interface CLIObserver {
    public void printBoard();
    public void printGameOver();
    public void printWin();
    public void printAbilityCastInfo(Unit attacker, Map<Unit, Integer> battleInformation);
    public void printAbilityFailed(String message);
    public void printPlayerStats();
    public void printBattleInformation(Unit attacker, Unit defender, int attackRoll, int defenseRoll, int damage);
    public void printPlayerLeveledUp(Player player);
    public void printEnemyDeath(Enemy enemy);
}
