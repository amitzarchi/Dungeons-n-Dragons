
public interface BoardObserver {
    public void updatePosition(int oldX, int oldY, int newX, int newY);
    public void updateNewTile(Tile tile);
    public void updateDeleteTile(Tile tile);
    //public void attackNeighbours(Position position, int damage);
}
