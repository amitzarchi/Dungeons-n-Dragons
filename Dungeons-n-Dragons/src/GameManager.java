import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameManager {
    private Board board;

    public GameManager() {
        board = new Board(10, 10);
    }

    public void PlayerAbillityCast(Position position, int range, int damage, boolean hitEveryone) {
        Board miniBoard = board.getRange(position, range);
        List<Enemy> enemies = getEnemiesInBoard(miniBoard);
        for (Enemy enemy : enemies) {
            if (hitEveryone) {
                enemy.getHealth().reduce(damage);
            } else {
                Random rand = new Random();
                Enemy RandomEnemy = enemies.get(rand.nextInt(enemies.size()));
                RandomEnemy.getHealth().reduce(damage);
            }                
        }
    }

    public List<Enemy> getEnemiesInBoard(Board board) {
        List<Enemy> enemies = new ArrayList<>();
        for (int i = 1; i <= board.getWidth(); i++) {
            for (int j = 1; j <= board.getHeight(); j++) {
                Tile tile = board.get(i, j);
                if (tile instanceof Enemy) {
                    enemies.add((Enemy)tile);
                }
            }
        }
        return enemies;
    }

                
    






}
