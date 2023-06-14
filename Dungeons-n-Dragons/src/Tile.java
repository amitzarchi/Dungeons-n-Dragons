


public class Tile {
    private char tile;
    private Position position;

    public Tile(char Tile, Position position) {
        this.tile = Tile;
        this.position = position;
    }
    
    public char getTile() {
        return tile;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}

