import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameManager implements GameObserver {
    public List<Board> boards;
    public int level;
    public Board board;
    public Player player;

    public GameManager(int playerNum) {
        File folder = new File("C:\\Users\\amitz\\OneDrive\\Documents\\Newfolder\\levels_dir");
        File[] listOfFiles = folder.listFiles();
        boards = new ArrayList<Board>();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                Board board = new Board(file, playerNum, this);
                boards.add(board);
            }
        }
        level = 0;
        board = boards.get(level);
    }

    public void setPlayer(Player player) {
        this.player = player;
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
        return player;
    }
    public Position getPlayerPosition() {
        return player.getPosition();
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
        // implement with cli
    }
    




}
