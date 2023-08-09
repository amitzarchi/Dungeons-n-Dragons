import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private QuadNode root;
    private int boardSize;
    int width;
    int height;
    GameObserver observer;
    public Player player;
    public List<Enemy> enemies;

    public Board(File file, int playerNum, GameObserver observer) {
        this.observer = observer;
        try {
            Path path = Paths.get(file.getAbsolutePath());
            List<String> lines = Files.readAllLines(path);
            width = lines.get(0).length();
            height = lines.size();
            boardSize = Math.max(width, height);
            root = new QuadNode(1, 1, boardSize, observer);
            Player player = null;
            enemies = new ArrayList<Enemy>();
            for (int y = 0; y < height; y++) {
                String line = lines.get(y);
                for (int x = 0; x < width; x++) {
                    char c = line.charAt(x);
                    if (c == '@') {
                        player = TileFactory.SetChosenPlayer(playerNum, new Position(x + 1, y + 1), observer);
                        insert(x + 1, y + 1, player);
                        this.player = player;
                    }
                    else {
                        Tile tile = TileFactory.createTile(c, new Position(x + 1, y + 1), enemies, observer);
                        insert(x + 1, y + 1, tile);
                    }
                }
            }
        } catch (Exception e) {}
    }

    public Board(int width, int height, GameObserver observer) {
        this.observer = observer;
        this.width = width;
        this.height = height;
        boardSize = Math.max(width, height);
        root = new QuadNode(1, 1, boardSize, observer);
        enemies = new ArrayList<Enemy>();
    }


    public int getNumOfLines(File file) {
        int numOfLines = 0;
        try {
            Path path = Paths.get(file.getAbsolutePath());
            numOfLines = (int) Files.lines(path).count();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numOfLines;
    }

    public void updateNewTile(Tile tile) {
        insert(tile.getPosition().getX(), tile.getPosition().getY(), tile);
    }

    public void updatePosition(int oldX, int oldY, int newX, int newY) {
        Tile tile = get(oldX, oldY);
        delete(oldX, oldY);
        insert(newX, newY, tile);
    }

    public void updateDeleteTile(Tile tile) {
        delete(tile.getPosition().getX(), tile.getPosition().getY());
        if (enemies.contains(tile)) {
            enemies.remove(tile);
        }
        if (tile == player) {
            player = null;
        }
    }

    public void insert(int x, int y, Tile value) {
        if (x < 1 || x > width || y < 1 || y > height) {
            throw new IllegalArgumentException("Coordinates out of bounds");
        }
        root.insert(x, y, value);
    }

    public Tile get(int x, int y) {
        if (x < 1 || x > width || y < 1 || y > height) {
            throw new IllegalArgumentException("Coordinates out of bounds");
        }
        return root.get(x, y);
    }

    public char getChar(int x, int y) {
        Tile tile = get(x, y);
        char value = tile == null ? '.' : tile.getChar();
        return value ;
    }   

    public void delete(int x, int y) {
        if (x < 1 || x > width || y < 1 || y > height) {
            throw new IllegalArgumentException("Coordinates out of bounds");
        }
        Empty empty = new Empty();
        empty.initialize(new Position(x, y), observer);
        root.insert(x, y, empty);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 1; y <= height; y++) {
            for (int x = 1; x <= width; x++) {
                sb.append(getChar(x, y));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public List<Unit> getUnitsInRange(Position position, int range) {
        List<Unit> units = new ArrayList<>();
        int x = position.getX();
        int y = position.getY();
        for (int i = x - range; i <= x + range; i++) {
            for (int j = y - range; j <= y + range; j++) {
                Tile tile = get(i, j);
                if (tile instanceof Unit) {
                    units.add((Unit)tile);
                }
            }
        }
        return units;
    }


    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }   
}