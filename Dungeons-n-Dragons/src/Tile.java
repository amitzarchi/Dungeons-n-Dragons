public abstract class Tile {
    private char tile;
    private Position position;
    private GameObserver observer;


    public Tile(char Tile) {
        this.tile = Tile;
    }
    
    public void initialize(Position position, GameObserver observer) {
        this.position = position;
        this.observer = observer;
    }

    public void initializeAndNotify(Position position, GameObserver observer) {
        this.position = position;
        this.observer = observer;
        notifyNewTile();
    }

    public char getChar() {
        return tile;
    }

    public void setChar(char tile) {
        this.tile = tile;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        int oldX = this.position.getX();
        int oldY = this.position.getY();
        this.position.setX(position.getX());
        this.position.setY(position.getY());
        notifyPositionChanged(oldX, oldY, position.getX(), position.getY());
    }

    public void delete() {
        observer.updateDeleteTile(this);
    }

    public void setPositionWithoutNotify(Position position) {
        this.position = position;
    }
    
    public void notifyNewTile() {
        observer.updateNewTile(this);
    }
    public void notifyPositionChanged(int oldX, int oldY, int newX, int newY) {
        observer.updatePosition(oldX, oldY, newX, newY);
    }

    public abstract void interactAccept(Unit unit);

    /// <summary>
    /// Compares the relative position of this position to another position.
    /// [0] = x difference, positive if other is east of this
    /// [1] = y difference, positive if other is south of this
    /// </summary>
    public int[] compareTo(Tile other) {
        return this.position.compareTo(other.position);
    }

    public GameObserver getObserver() {
        return observer;
    }

    public void setObserver(GameObserver observer) {
        this.observer = observer;
    }


}

