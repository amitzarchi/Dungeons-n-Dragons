import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GameManager implements GameObserver {
    public List<Board> boards;
    public int currentLevel;
    public int numOfLevels;
    public Board board;
    public CLIObserver CLIObserver;

    public GameManager(int playerNum, String path, CLIObserver CLIObserver) {
        File folder = new File("C:\\Users\\amitz\\OneDrive\\Documents\\Newfolder\\levels_dir");
        File[] listOfFiles = folder.listFiles();
        boards = new ArrayList<Board>();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                Board board = new Board(file, playerNum, this);
                boards.add(board);
            }
        }
        currentLevel = 0;
        numOfLevels = boards.size();
        board = boards.get(currentLevel);
        this.CLIObserver = CLIObserver;
    }

    public GameManager(int width, int height, CLIObserver CLIObserver) {
        boards = new ArrayList<Board>();
        Board board = new Board(width, height, this);
        boards.add(board);
        currentLevel = 0;
        numOfLevels = boards.size();
        this.board = boards.get(currentLevel);
        this.CLIObserver = CLIObserver;
    }

    public void play() {
        while (board.enemies.size() > 0) {
            CLIObserver.printBoard();
            CLIObserver.printPlayerStats();
            board.player.onTurn();
            for (Enemy enemy : board.enemies) {
                enemy.onTurn();
            }
            if (board.player.getHealth().isDead()) {
                board.player.onDeath();
                CLIObserver.printBoard();
                CLIObserver.printGameOver();
                return;
            }
        }
        if (currentLevel < numOfLevels - 1) {
            currentLevel++;
            Player player = board.player;
            board = boards.get(currentLevel);
            Player newPlayer = board.player;
            player.initialize(newPlayer.getPosition(), this);
            board.player = player;
            board.insert(player.getPosition().getX(), player.getPosition().getY(), player);
            play();
        }
        else {
            CLIObserver.printBoard();
            CLIObserver.printWin();
        }
    }

    public void updatePosition(int oldX, int oldY, int newX, int newY) {
        board.updatePosition(oldX, oldY, newX, newY);
    }

    public void updateNewTile(Tile tile) {
        board.updateNewTile(tile);
    }

    public void updateDeleteTile(Tile tile) {
        board.updateDeleteTile(tile);
    }

    public Tile getTile(Position position) {
        return board.get(position.getX(), position.getY());
    }

    public Player getPlayer() {
        return board.player;
    }
    public Position getPlayerPosition() {
        return board.player.getPosition();
    }
    public void enemySpawn(Enemy enemy) {
        board.enemies.add(enemy);
    }
    public void playerSpawn(Player player) {
        board.player = player;
    }

    public void battleInformation(Unit attacker, Unit defender, int attackRoll, int defenseRoll, int damage) {
        CLIObserver.printBattleInformation(attacker, defender, attackRoll, defenseRoll, damage);
    }

    public void AttackInRange(Unit attacker, int range, int attackPoints, boolean hitEveryone) {
        List<Enemy> enemies = getEnemiesInRange(attacker.getPosition(), range);
        if (enemies.size() > 0) {
            Map<Unit, Integer> battleInformation = new HashMap<Unit, Integer>();
            if(hitEveryone) {
                for (Enemy enemy : enemies) {
                    int damage = enemy.TakeAHit(attackPoints);
                    battleInformation.put(enemy, damage);
                }
            }
            else {
                Random rand = new Random();
                int index = rand.nextInt(enemies.size());
                int damage = enemies.get(index).TakeAHit(attackPoints);
                battleInformation.put(enemies.get(index), damage);
            }
            AbilityCastInfo(attacker, battleInformation);
        }
    }

    public void MageAttack(Unit attacker, int range, int hits, int attackPoints) {
        List<Enemy> enemies = getEnemiesInRange(attacker.getPosition(), range);
        if (enemies.size() > 0) {
            Map<Unit, Integer> battleInformation = new HashMap<Unit, Integer>();
            Random rand = new Random();
            for (int i = 0; i < hits; i++) {
                int index = rand.nextInt(enemies.size());
                int damage = enemies.get(index).TakeAHit(attackPoints);
                battleInformation.put(enemies.get(index), damage);
            }
            AbilityCastInfo(attacker, battleInformation);
        }
    }
         

    public boolean HunterAbillityCast(Unit attacker, int range, int attackPoints) {
        Enemy enemy = getClosestEnemy(attacker.getPosition(), range);
            Map<Unit, Integer> battleInformation = new HashMap<Unit, Integer>();
        if (enemy != null) {
            int damage = enemy.TakeAHit(attackPoints);
            battleInformation.put(enemy, damage);
            AbilityCastInfo(attacker, battleInformation);
            return true;
        }
        else {
            abilityFailed("No enemy in range");
            return false;
        }
    }

    public void BossAbilityCast(Unit attacker, int attackPoints) {
        int damage = board.player.TakeAHit(attackPoints);
        Map<Unit, Integer> battleInformation = new HashMap<Unit, Integer>();
        battleInformation.put(board.player, damage);
        AbilityCastInfo(attacker, battleInformation);
    }

    public Enemy getClosestEnemy(Position position, int range) {
        for (int i = 1; i <= range; i++) {
            List<Enemy> enemies = getEnemiesInRange(position, i);
            if (enemies.size() > 0) {
                Random rand = new Random();
                int index = rand.nextInt(enemies.size());
                return enemies.get(index);
            }
        }
        return null;
    }

    public List<Enemy> getEnemiesInRange(Position position, int range) {
        List<Enemy> enemies = new ArrayList<Enemy>();
        for (int i = position.getX() - range; i <= position.getX() + range; i++) {
            for (int j = position.getY() - range; j <= position.getY() + range; j++) {
                if (i >= 1 && i <= board.width && j >= 1 && j <= board.height) {
                    Tile tile = board.get(i, j);
                    if (board.enemies.contains(tile)) {
                        enemies.add((Enemy)tile);
                    }
                }
            }
        }
        return enemies;
    }
    
    public void abilityFailed(String message) {
        CLIObserver.printAbilityFailed(message);
    }

    public void AbilityCastInfo(Unit attacker, Map<Unit, Integer> battleInformation) {
        CLIObserver.printAbilityCastInfo(attacker, battleInformation);
    }

    public void playerLeveledUp(Player player) {
        CLIObserver.printPlayerLeveledUp(player);
    }
    
    public void enemyDeath(String name) {
        CLIObserver.printEnemyDeath(name);
    }
}
