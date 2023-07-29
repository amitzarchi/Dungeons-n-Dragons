import java.util.List;

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
    public void addIfEnemy(List<Enemy> enemies) {
        // Do nothing
    }
}
