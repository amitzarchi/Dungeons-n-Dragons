
public class App {
    public static void main(String[] args) throws Exception {
        Board quadTree = new Board(49, 19);
        Warrior warrior = new Warrior("Warrior", 100, 10, 5, 'W');
        warrior.initialize(new Position(7, 7), quadTree);
        Monster monster = new Monster("Monster", 100, 10, 5, 10, 5, 'M');
        monster.initialize(new Position(7, 6), quadTree);
        System.out.println(quadTree.toString());
        Tile enemy = quadTree.get(7, 6);
        Tile warriorTile = quadTree.get(7, 7);
        monster.interact(warriorTile);
        System.out.println(quadTree.toString());

    }
}
