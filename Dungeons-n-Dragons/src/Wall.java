public class Wall extends Tile {
    public Wall() {
        super('#');
    }

    public void initialize(Position position, GameObserver observer) {
        super.initialize(position, observer);
    }

    public void interactAccept(Unit unit) {
        unit.interactVisit(this);
    }
}
