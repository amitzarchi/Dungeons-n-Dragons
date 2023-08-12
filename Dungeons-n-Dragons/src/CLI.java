import java.util.Map;
import java.util.Scanner;

public class CLI implements CLIObserver {
    public GameManager gameManager;
    public Scanner scanner;
    private String path;

    public CLI(String path) {
        this.path = path;
    }
    
    // For testing purposes
    public CLI() {
        this.gameManager = new GameManager(10, 10, this);
    }

    public void play() 
    {
        printChoosePlayer();
        scanner = new Scanner(System.in);
        int playerNum = 0;
        try {
            playerNum = scanner.nextInt();
            if (playerNum < 1 || playerNum > 7) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Invalid player number");
            play();
        }
        gameManager = new GameManager(playerNum, path, this);
        gameManager.play();
    }

    public void printChoosePlayer() {
        String output = "Choose player:\n" +
                        "1. Jon Snow - Health: 300/300, Attack: 30, Defense: 4, Level: 1, Experience: 0/50, Cooldown: 0/3\n" +
                        "2. The Hound - Health: 400/400, Attack: 20, Defense: 6, Level: 1, Experience: 0/50, Cooldown: 0/5\n" +
                        "3. Melisandre - Health: 100/100, Attack: 5, Defense: 1, Level: 1, Experience: 0/50, Mana: 75/300, Spell Power: 15, Cooldown: 0/5\n" +
                        "4. Thoros of Myr - Health: 250/250, Attack: 25, Defense: 4, Level: 1, Experience: 0/50, Mana: 37/150, Spell Power: 20, Cooldown: 0/3\n" +
                        "5. Arya Stark - Health: 150/150, Attack: 40, Defense: 2, Level: 1, Experience: 0/50, Energy: 100/100\n" +
                        "6. Bronn - Health: 250/250, Attack: 35, Defense: 3, Level: 1, Experience: 0/50, Energy: 100/100\n" +
                        "7. Ygritte - Health: 220/220, Attack: 30, Defense: 2, Level: 1, Experience: 0/50, Arrows: 10/10\n";
        System.out.println(createAsciiFrame(output));
        System.out.println();
    }


    public void printBoard() {
        String output = addLevelLine(gameManager.board.toString(), gameManager.currentLevel + 1);
        System.out.println(output);
        System.out.println();
    }

    public void printGameOver() {
        System.out.println(YouLostWithBorder);
        System.out.println();
    }

    public void printWin() {
        System.out.println(YouWonWithBorder);
        System.out.println();
    }

    public void printAbilityFailed(String message) {
        String output = "Cannot cast ability :( -- " + message;
        System.out.println(createAsciiFrame(output));
        System.out.println();
    }

    public void printPlayerStats() {
        String output = gameManager.board.player.toString();
        System.out.println(createAsciiFrame(output));
        System.out.println();
    }

    public void printBattleInformation(Unit attacker, Unit defender, int attackRoll, int defenseRoll, int damage) {
        String battleInformation = 
            attacker.getName() + " engaged in battle with " + defender.getName() + "!\n" +
            attacker.getName() + " rolled " + attackRoll + " attack points!\n" +
            defender.getName() + " rolled " + defenseRoll + " defense points!\n" +
            attacker.getName() + " hit " + defender.getName() + " for " + damage + " ability damage!\n" +
            defender.getName() + " health is " + defender.getHealth().getHealthAmount() + "/" + defender.getHealth().getHealthPool() + "\n";
        System.out.println(createAsciiFrame(battleInformation));
    }

    public void printAbilityCastInfo(Unit attacker, Map<Unit, Integer> battleInformation){
        String output = attacker.getName() + " Casted Abillity!\n";
        for (Unit defender : battleInformation.keySet()) {
            output += attacker.getName() + " hit " + defender.getName() + " for " + battleInformation.get(defender) + " ability damage!\n";
            output += defender.getName() + " health is " + defender.getHealth().getHealthAmount() + "/" + defender.getHealth().getHealthPool() + "\n";
        }
        System.out.println(createAsciiFrame(output));
    }

    public void printPlayerLeveledUp(Player player) {
        String output = player.getName() + " leveled up to Level: " + player.getLevel() + "!\n" +
                        "Improved stats:\n" +
                        "Health: " + player.getHealth().getHealthAmount() + "/" + player.getHealth().getHealthPool() + "\n" +
                        "Attack: " + player.getAttackPoints() + "\n" +
                        "Defense: " + player.getDefensePoints() + "\n";
        System.out.println(createAsciiFrame(output));
    }

    public void printEnemyDeath(Enemy enemy) {
        String output = enemy.getName() + " died!\n" +
                        gameManager.board.player.getName() + " gained " + enemy.getExperienceValue() + " experience points!\n";
        System.out.println(createAsciiFrame(output));
    }

    // Decorations for printing
    private static String createAsciiFrame(String input) {
        String[] lines = input.split("\n");
        int maxLength = 0;
        for (String line : lines) {
            if (line.length() > maxLength) {
                maxLength = line.length();
            }
        }
        
        StringBuilder frame = new StringBuilder();
        frame.append("." + "-".repeat(maxLength + 2) + ".\n");
        for (String line : lines) {
            frame.append("| " + padRight(line, maxLength) + " |\n");
        }
        frame.append("'" + "-".repeat(maxLength + 2) + "'\n");

        return frame.toString();
    }

    private static String padRight(String str, int length) {
        return String.format("%-" + length + "s", str);
    }


    private String YouWonWithBorder =
     " .--..--..--..--..--..--..--..--..--..--..--..--..--..--..--..--.\r\n" + //
             "/ .. \\.. \\.. \\.. \\.. \\.. \\.. \\.. \\.. \\.. \\.. \\.. \\.. \\.. \\.. \\.. \\\r\n" + //
             "\\ \\/\\ \\/\\ \\/\\ \\/\\ \\/\\ \\/\\ \\/\\ \\/\\ \\/\\ \\/\\ \\/\\ \\/\\ \\/\\ \\/\\ \\/\\ \\/ /\r\n" + //
             " \\/ /\\/ /\\/ /\\/ /\\/ /\\/ /\\/ /\\/ /\\/ /\\/ /\\/ /\\/ /\\/ /\\/ /\\/ /\\/ /\r\n" + //
             " / /\\/ /`' /`' /`' /`' /`' /`' /`' /`' /`' /`' /`' /`' /`' /\\/ /\\\r\n" + //
             "/ /\\ \\/`--'`--'`--'`--'`--'`--'`--'`--'`--'`--'`--'`--'`--'\\ \\/\\ \\\r\n" + //
             "\\ \\/\\ \\                                                    /\\ \\/ /\r\n" + //
             " \\/ /\\ \\                                                  / /\\/ /\r\n" + //
             " / /\\/ /                                                  \\ \\/ /\\\r\n" + //
             "/ /\\ \\/                      You Won!                      \\ \\/\\ \\\r\n" + //
             "\\ \\/\\ \\                                                    /\\ \\/ /\r\n" + //
             " \\/ /\\ \\                                                  / /\\/ /\r\n" + //
             " / /\\/ /                                                  \\ \\/ /\\\r\n" + //
             "/ /\\ \\/                                                    \\ \\/\\ \\\r\n" + //
             "\\ \\/\\ \\.--..--..--..--..--..--..--..--..--..--..--..--..--./\\ \\/ /\r\n" + //
             " \\/ /\\/ ../ ../ ../ ../ ../ ../ ../ ../ ../ ../ ../ ../ ../ /\\/ /\r\n" + //
             " / /\\/ /\\/ /\\/ /\\/ /\\/ /\\/ /\\/ /\\/ /\\/ /\\/ /\\/ /\\/ /\\/ /\\/ /\\/ /\\\r\n" + //
             "/ /\\ \\/\\ \\/\\ \\/\\ \\/\\ \\/\\ \\/\\ \\/\\ \\/\\ \\/\\ \\/\\ \\/\\ \\/\\ \\/\\ \\/\\ \\/\\ \\\r\n" + //
             "\\ `'\\ `'\\ `'\\ `'\\ `'\\ `'\\ `'\\ `'\\ `'\\ `'\\ `'\\ `'\\ `'\\ `'\\ `'\\ `' /\r\n" + //
             " `--'`--'`--'`--'`--'`--'`--'`--'`--'`--'`--'`--'`--'`--'`--'`--'\r\n" //
             ;

    private String YouLostWithBorder =
    " ___   _  _   ___   _  _   ___   _  _   ___   _  _   ___\r\n" + //
            "/___\\ | \\/ | /___\\ | \\/ | /___\\ | \\/ | /___\\ | \\/ | /___\\\r\n" + //
            "|___|  \\__/  |___|  \\__/  |___|  \\__/  |___|  \\__/  |___|\r\n" + //
            " _  _                                               _  _ \r\n" + //
            "| \\/ |                                             | \\/ |\r\n" + //
            " \\__/                                               \\__/ \r\n" + //
            " ___                                                 ___\r\n" + //
            "/___\\                You Lost!                      /___\\\r\n" + //
            "|___|                 GAME OVER                     |___|\r\n" + //
            " _  _                                               _  _   \r\n" + //
            "| \\/ |                                             | \\/ | \r\n" + //
            " \\__/                                               \\__/  \r\n" + //
            " ___   _  _   ___   _  _   ___   _  _   ___   _  _   ___\r\n" + //
            "/___\\ | \\/ | /___\\ | \\/ | /___\\ | \\/ | /___\\ | \\/ | /___\\\r\n" + //
            "|___|  \\__/  |___|  \\__/  |___|  \\__/  |___|  \\__/  |___|\r\n" ; 

    public static String addLevelLine(String board, int level) {
        String[] lines = board.split("\n"); 
        int width = lines[0].length();
        String paddedInput = "-" + "Level: " + level + "-";
        int paddingSize = width - paddedInput.length();
        int leftPadding = paddingSize / 2;
        int rightPadding = paddingSize - leftPadding;
        String firstLine = "-".repeat(leftPadding) + paddedInput + "-".repeat(rightPadding);
        StringBuilder result = new StringBuilder().append(firstLine).append("\n");
        for (int i = 0; i < lines.length; i++) {
            result.append(lines[i]).append("\n");
        }
        return result.toString();
    }
                    
}