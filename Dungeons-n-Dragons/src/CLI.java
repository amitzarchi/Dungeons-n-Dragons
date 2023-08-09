import java.util.Map;
import java.util.Scanner;

public class CLI implements CLIObserver {
    public GameManager gameManager;
    public Scanner scanner;
    
    public CLI(String path)
    {
        printChoosePlayer();
        scanner = new Scanner(System.in);
        int playerNum = scanner.nextInt();
        gameManager = new GameManager(playerNum, path, this);
        gameManager.play();
    }

    public void printChoosePlayer() {
        System.out.println();
        System.out.println("====================================================================================================================================");
        System.out.println("Choose player:");
        System.out.println("1. Jon Snow - Health: 300/300, Attack: 30, Defense: 4, Level: 1, Experience: 0/50, Cooldown: 0/3");
        System.out.println("2. The Hound - Health: 400/400, Attack: 20, Defense: 6, Level: 1, Experience: 0/50, Cooldown: 0/5");
        System.out.println("3. Melisandre - Health: 100/100, Attack: 5, Defense: 1, Level: 1, Experience: 0/50, Mana: 75/300, Spell Power: 15, Cooldown: 0/5");
        System.out.println("4. Thoros of Myr - Health: 250/250, Attack: 25, Defense: 4, Level: 1, Experience: 0/50, Mana: 37/150, Spell Power: 20, Cooldown: 0/3");
        System.out.println("5. Arya Stark - Health: 150/150, Attack: 40, Defense: 2, Level: 1, Experience: 0/50, Energy: 100/100");
        System.out.println("6. Bronn - Health: 250/250, Attack: 35, Defense: 3, Level: 1, Experience: 0/50, Energy: 100/100");
        System.out.println("7. Ygritte - Health: 220/220, Attack: 30, Defense: 2, Level: 1, Experience: 0/50, Arrows: 10/10");
        System.out.println("====================================================================================================================================");
        System.out.println();
    }

    public void printBoard() {
        System.out.println(gameManager.board);
    }

    public void printGameOver() {
        System.out.println("==========");
        System.out.println("Game Over!");
        System.out.println("==========");
        System.out.println();
    }

    public void printWin() {
        System.out.println("========");
        System.out.println("You Won!");
        System.out.println("========");
        System.out.println();
    }

    public void printAbilityFailed(String message) {
        System.out.println("Cannot Cast Abillity :( " + message );
    }

    public void printPlayerStats() {
        System.out.println(gameManager.board.player);
        System.out.println();
    }

    public void printBattleInformation(Unit attacker, Unit defender, int attackRoll, int defenseRoll, int damage) {
        System.out.println("==========================================================");
        System.out.println(attacker.getName() + " engaged in battle with " + defender.getName() + "!");
        System.out.println(attacker.getName() + " rolled " + attackRoll + " attack points!");
        System.out.println(defender.getName() + " rolled " + defenseRoll + " defense points!");
        System.out.println(attacker.getName() + " hit " + defender.getName() + " for " + damage + " ability damage!");
        System.out.println(defender.getName() + " health is " + defender.getHealth().getHealthAmount() + "/" + defender.getHealth().getHealthPool());
        System.out.println("==========================================================");
        System.out.println();
    }

    public void printAbilityCastInfo(Unit attacker, Map<Unit, Integer> battleInformation){
        System.out.println("==========================================================");
        System.out.println(attacker.getName() + " Casted Abillity!");
        for (Unit defender : battleInformation.keySet()) {
            System.out.println(attacker.getName() + " hit " + defender.getName() + " for " + battleInformation.get(defender) + " ability damage!");
            System.out.println(defender.getName() + " health is " + defender.getHealth().getHealthAmount() + "/" + defender.getHealth().getHealthPool());
        }
        System.out.println("==========================================================");
    }


    public void printPlayerLeveledUp(Player player) {
        System.out.println("==================================");
        System.out.println(player.getName() + " leveled up to Level: " + player.getLevel() + "!");
        System.out.println("Improved stats:");
        System.out.println("Health: " + player.getHealth().getHealthAmount() + "/" + player.getHealth().getHealthPool());
        System.out.println("Attack: " + player.getAttackPoints());
        System.out.println("Defense: " + player.getDefensePoints());
        System.out.println("==================================");
        System.out.println();
    }

    public void printEnemyDeath(String name) {
        System.out.println("======================");
        System.out.println(name + " died!");
        System.out.println("======================");
        System.out.println();
    }
}

