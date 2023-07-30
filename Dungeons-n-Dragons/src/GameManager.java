import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
            board = boards.get(currentLevel);
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

    public void battleInformation(Unit attacker, Unit defender, int attackRoll, int defenseRoll, int damage) {
        CLIObserver.printBattleInformation(attacker, defender, attackRoll, defenseRoll, damage);
    }

    public void playerAbillityCast(Position position, int range, int damage, boolean hitEveryone) {
        List<Enemy> enemies = getEnemiesInRange(position, range);
        if (enemies.size() > 0) {
            if(hitEveryone) {
                for (Enemy enemy : enemies) {
                    enemy.TakeAHit(damage);
                }
            }
            else {
                Random rand = new Random();
                int index = rand.nextInt(enemies.size());
                enemies.get(index).TakeAHit(damage);
            }
        }
    }

    public List<Enemy> getEnemiesInRange(Position position, int range) {
        List<Enemy> enemies = new ArrayList<Enemy>();
        for (int i = position.getX() - range; i <= position.getX() + range; i++) {
            for (int j = position.getY() - range; j <= position.getY() + range; j++) {
                if (i >= 1 && i <= board.width && j >= 1 && j <= board.height) {
                    Tile tile = board.get(i, j);
                    tile.addIfEnemy(enemies);
                }
            }
        }
        return enemies;
    }
    
    public void abilityFailed() {
        CLIObserver.printAbilityFailed();
    }

    public void playerLeveledUp(Player player) {
        CLIObserver.printPlayerLeveledUp(player);
    }
    
    public void enemyDeath(String name) {
        CLIObserver.printEnemyDeath(name);
    }
}
