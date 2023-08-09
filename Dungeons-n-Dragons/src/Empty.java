
public class Empty extends Tile {
    public Empty() {
        super('.');
    }
    
    public void initialize(Position position, GameObserver observer) {
        super.initialize(position, observer);
    }
     
    public void interactAccept(Unit unit) {
        unit.interactVisit(this);
    }
    
}
