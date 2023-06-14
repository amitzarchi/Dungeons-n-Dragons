package Backend;

public class Tile {
    private char tile;
    private Position<Integer, Integer> position;

    public Tile(char Tile, Position<Integer, Integer> position) {
        this.tile = Tile;
        this.position = position;
    }
    
    public char getTile() {
        return tile;
    }

    public Position<Integer, Integer> getPosition() {
        return position;
    }

    public void setPosition(Position<Integer, Integer> position) {
        this.position = position;
    }
}

