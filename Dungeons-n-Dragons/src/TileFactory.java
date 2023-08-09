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
                Enemy monster = new Monster("Lannister Solider", 80, 8, 3, 25, 3, 's');
                monster.initialize(position, observer);
                enemies.add(monster);
                return monster;
            }
            case 'k':
            {
                Enemy monster = new Monster("Lannister Knight", 200, 14, 8, 50, 4, 'k');
                monster.initialize(position, observer);
                enemies.add(monster);
                return monster;
            }
            case 'q':
            {
                Enemy monster = new Monster("Queen's Guard", 400, 20, 15, 100, 5, 'q');
                monster.initialize(position, observer);
                enemies.add(monster);
                return monster;
            }
            case 'z':
            {
                Enemy monster = new Monster("Wright", 600, 30, 15, 100, 3, 'z');
                monster.initialize(position, observer);
                enemies.add(monster);
                return monster;
            }
            case 'b':
            {
                Enemy monster = new Monster("Bear-Wright", 1000, 75, 30, 250, 4, 'b');
                monster.initialize(position, observer);
                enemies.add(monster);
                return monster;
            }
            case 'g':
            {
                Enemy monster = new Monster("Giant-Wright", 1500, 100, 40, 500, 5, 'g');
                monster.initialize(position, observer);
                enemies.add(monster);
                return monster;
            }
            case 'w':
            {
                Enemy monster = new Monster("White Walker", 2000, 150, 50, 1000, 6, 'w');
                monster.initialize(position, observer);
                enemies.add(monster);
                return monster;
            }
            case 'M':
            {
                Enemy boss = new Boss("The Mountain", 1000, 60, 25, 500, 'M', 6, 5);
                boss.initialize(position, observer);
                enemies.add(boss);
                return boss;
            }
            case 'C':
            {
                Enemy boss = new Boss("Queen Cersei", 100, 10, 10, 1000, 'C', 1, 8);
                boss.initialize(position, observer);
                enemies.add(boss);
                return boss;
            }
            case 'K':
            {
                Enemy boss = new Boss("Night's King", 5000, 300, 150, 5000, 'K', 8, 3);
                boss.initialize(position, observer);
                enemies.add(boss);
                return boss;
            }
            case 'B':
            {
                Enemy trap = new Trap("Bonus Trap", 1, 1, 1, 250, 1, 5, 'B');
                trap.initialize(position, observer);
                enemies.add(trap);
                return trap;
            }
            case 'Q':
            {
                Enemy trap = new Trap("Queen's Trap", 250, 50, 10, 100, 3, 7, 'Q');
                trap.initialize(position, observer);
                enemies.add(trap);
                return trap;
            }
            case 'D':
            {
                Enemy trap = new Trap("Death Trap", 500, 100, 20, 250, 1, 10, 'D');
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
                Player warrior = new Warrior("Jon Snow", 300, 30, 4, 3);
                warrior.initialize(position, observer);
                return warrior;
            }
            case 2:
            {
                Player warrior = new Warrior("The Hound", 400, 20, 6, 5);
                warrior.initialize(position, observer);
                return warrior;
            }
            case 3:
            {
                Player mage = new Mage("Melisandre", 100, 5, 1, 300, 30, 15, 5, 6);
                mage.initialize(position, observer);
                return mage;
            }
            case 4:
            {
                Player mage = new Mage("Thoros of Myr", 250, 25, 4, 150, 20, 20, 3, 4);
                mage.initialize(position, observer);
                return mage;
            }
            case 5:
            {
                Player rogue = new Rogue("Arya Stark", 150, 40, 2, 20);
                rogue.initialize(position, observer);
                return rogue;
            }
            case 6:
            {
                Player rogue = new Rogue("Bronn", 250, 35, 3, 50);
                rogue.initialize(position, observer);
                return rogue;
            }
            case 7:
            {
                Player hunter = new Hunter("Ygritte", 220, 30, 2, 6);
                hunter.initialize(position, observer);
                return hunter;
            }
        }
        return null;
    }
}
