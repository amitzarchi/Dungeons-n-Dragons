public class Empty extends Tile {
    public Empty() {
        super('.');
    }
     
    public void accept(Unit unit) {
        unit.visit(this);
    }
    
}
