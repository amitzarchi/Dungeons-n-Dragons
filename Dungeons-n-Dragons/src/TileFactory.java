import java.util.List;

public class TileFactory {
    public static Tile createTile(char c, Position position, List<Enemy> enemies, GameObserver observer) {
        switch (c) {
            case '#':
            {
                Wall wall = new Wall();
                wall.initialize(position, observer);
                return wall;
            }
                
            case '.':
            {
                Empty empty = new Empty();
                empty.initialize(position, observer);
                return empty;
            }
             case '@':
            {
                Empty empty = new Empty();
                empty.initialize(position, observer);
                return empty;
            }
            case 's':
            {
                Monster monster = new Monster("Lannister Solider", 80, 8, 3, 25, 3, 's');
                monster.initialize(position, observer);
                enemies.add(monster);
                return monster;
            }
            case 'k':
            {
                Monster monster = new Monster("Lannister Knight", 200, 14, 8, 50, 4, 'k');
                monster.initialize(position, observer);
                enemies.add(monster);
                return monster;
            }
            case 'q':
            {
                Monster monster = new Monster("Queen's Guard", 400, 20, 15, 100, 5, 'q');
                monster.initialize(position, observer);
                enemies.add(monster);
                return monster;
            }
            case 'z':
            {
                Monster monster = new Monster("Wright", 600, 30, 15, 100, 3, 'z');
                monster.initialize(position, observer);
                enemies.add(monster);
                return monster;
            }
            case 'b':
            {
                Monster monster = new Monster("Bear-Wright", 1000, 75, 30, 250, 4, 'b');
                monster.initialize(position, observer);
                enemies.add(monster);
                return monster;
            }
            case 'g':
            {
                Monster monster = new Monster("Giant-Wright", 1500, 100, 40, 500, 5, 'g');
                monster.initialize(position, observer);
                enemies.add(monster);
                return monster;
            }
            case 'w':
            {
                Monster monster = new Monster("White Walker", 2000, 150, 50, 1000, 6, 'w');
                monster.initialize(position, observer);
                enemies.add(monster);
                return monster;
            }
            case 'M':
            {
                Monster monster = new Monster("The Mountain", 1000, 60, 25, 500, 6, 'M');
                monster.initialize(position, observer);
                enemies.add(monster);
                return monster;
            }
            case 'C':
            {
                Monster monster = new Monster("Queen Cersei", 100, 10, 10, 1000, 1, 'C');
                monster.initialize(position, observer);
                enemies.add(monster);
                return monster;
            }
            case 'K':
            {
                Monster monster = new Monster("Night's King", 5000, 300, 150, 5000, 8, 'K');
                monster.initialize(position, observer);
                enemies.add(monster);
                return monster;
            }
            case 'B':
            {
                Trap trap = new Trap("Bonus Trap", 1, 1, 1, 250, 1, 5, 'B');
                trap.initialize(position, observer);
                enemies.add(trap);
                return trap;
            }
            case 'Q':
            {
                Trap trap = new Trap("Queen's Trap", 250, 50, 10, 100, 3, 7, 'Q');
                trap.initialize(position, observer);
                enemies.add(trap);
                return trap;
            }
            case 'D':
            {
                Trap trap = new Trap("Death Trap", 500, 100, 20, 250, 1, 10, 'D');
                trap.initialize(position, observer);
                enemies.add(trap);
                return trap;
            }
        }
        return null;
    }

    public static Player SetChosenPlayer(int chosenPlayer, Position position, GameObserver observer) {
        switch (chosenPlayer) {
            case 1:
            {
                Warrior warrior = new Warrior("Jon Snow", 300, 30, 4, 3);
                warrior.initialize(position, observer);
                return warrior;
            }
            case 2:
            {
                Warrior warrior = new Warrior("The Hound", 400, 20, 6, 5);
                warrior.initialize(position, observer);
                return warrior;
            }
            case 3:
            {
                Mage mage = new Mage("Melisandre", 100, 5, 1, 300, 30, 15, 5, 6);
                mage.initialize(position, observer);
                return mage;
            }
            case 4:
            {
                Mage mage = new Mage("Thoros of Myr", 250, 25, 4, 150, 20, 20, 3, 4);
                mage.initialize(position, observer);
                return mage;
            }
            case 5:
            {
                Rogue rogue = new Rogue("Arya Stark", 150, 40, 2, 20);
                rogue.initialize(position, observer);
                return rogue;
            }
            case 6:
            {
                Rogue rogue = new Rogue("Bronn", 250, 35, 3, 50);
                rogue.initialize(position, observer);
                return rogue;
            }
        }
        return null;
    }
}
