import java.util.ArrayList;
import java.util.List;

public class Board implements BoardObserver{
    private QuadNode root;
    private int boardSize;
    int width;
    int height;

    public Board(int x, int y)  {

        boardSize = Math.max(x, y);
        width = x;
        height = y;
        root = new QuadNode(1, 1, boardSize, this);
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
        empty.initialize(new Position(x, y), this);
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

    public Board getRange(Position position, int range) {
        Board board = new Board(range * 2 + 1, range * 2 + 1);
        int x = position.getX();
        int y = position.getY();
        for (int i = x - range; i <= x + range; i++) {
            for (int j = y - range; j <= y + range; j++) {
                Tile tile = get(i, j);
                board.insert(i - x + range + 1, j - y + range + 1, tile);
            }
        }
        return board;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }   
}