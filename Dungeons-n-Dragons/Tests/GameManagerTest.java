import junit.framework.TestCase;

import java.util.List;

public class GameManagerTest extends TestCase {

    public void testGetEnemiesInRange() {
        // Arrange
        CLI cli = new CLI();
        Player player = new Mage("TestPlayer", 2000, 100, 100, 1000, 1, 1000, 5, 1);
        player.initializeAndNotify(new Position(5, 5), cli.gameManager);
        Enemy enemy1 = new Monster("TestEnemy1", 100, 100, 1, 100, 100, 'M');
        enemy1.initializeAndNotify(new Position(6, 4), cli.gameManager);
        Enemy enemy2 = new Monster("TestEnemy2", 100, 100, 1, 100, 100, 'M');
        enemy2.initializeAndNotify(new Position(6, 5), cli.gameManager);
        Enemy enemy3 = new Monster("TestEnemy3", 100, 100, 1, 100, 100, 'M');
        enemy3.initializeAndNotify(new Position(6, 6), cli.gameManager);
        Enemy enemy4 = new Monster("TestEnemy4", 100, 100, 1, 100, 100, 'M');
        enemy4.initializeAndNotify(new Position(5, 4), cli.gameManager);
        Enemy enemy5 = new Monster("TestEnemy5", 100, 100, 1, 100, 100, 'M');
        enemy5.initializeAndNotify(new Position(5, 6), cli.gameManager);
        Enemy enemy6 = new Monster("TestEnemy6", 100, 100, 1, 100, 100, 'M');
        enemy6.initializeAndNotify(new Position(4, 4), cli.gameManager);
        Enemy enemy7 = new Monster("TestEnemy7", 100, 100, 1, 100, 100, 'M');
        enemy7.initializeAndNotify(new Position(4, 5), cli.gameManager);
        Enemy enemy8 = new Monster("TestEnemy8", 100, 100, 1, 100, 100, 'M');
        enemy8.initializeAndNotify(new Position(4, 6), cli.gameManager);
        Enemy enemy9 = new Monster("TestEnemy9", 100, 100, 1, 100, 100, 'M');
        enemy9.initializeAndNotify(new Position(7, 5), cli.gameManager);
        // Act
        List<Enemy> enemies = cli.gameManager.getEnemiesInRange(player.getPosition(), 1);
        // Assert
        assert(enemies.size() == 8);
    }

    public void testGetClosestEnemy() {
        // Arrange
        CLI cli = new CLI();
        Player player = new Mage("TestPlayer", 2000, 100, 100, 1000, 1, 1000, 5, 1);
        player.initializeAndNotify(new Position(5, 5), cli.gameManager);
        Enemy enemy1 = new Monster("TestEnemy1", 100, 100, 1, 100, 100, 'M');
        enemy1.initializeAndNotify(new Position(6, 4), cli.gameManager);
        Enemy enemy9 = new Monster("TestEnemy9", 100, 100, 1, 100, 100, 'M');
        enemy9.initializeAndNotify(new Position(7, 5), cli.gameManager);
        // Act
        Enemy enemy = cli.gameManager.getClosestEnemy(player.getPosition(), 1);
        // Assert
        assert(enemy == enemy1);
    }
    public void testGetClosestEnemy_2_Options() {
        // Arrange
        CLI cli = new CLI();
        Player player = new Mage("TestPlayer", 2000, 100, 100, 1000, 1, 1000, 5, 1);
        player.initializeAndNotify(new Position(5, 5), cli.gameManager);
        Enemy enemy1 = new Monster("TestEnemy1", 100, 100, 1, 100, 100, 'M');
        enemy1.initializeAndNotify(new Position(6, 4), cli.gameManager);
        Enemy enemy2 = new Monster("TestEnemy2", 100, 100, 1, 100, 100, 'M');
        enemy2.initializeAndNotify(new Position(5, 4), cli.gameManager);
        Enemy enemy9 = new Monster("TestEnemy9", 100, 100, 1, 100, 100, 'M');
        enemy9.initializeAndNotify(new Position(7, 5), cli.gameManager);
        // Act
        Enemy enemy = cli.gameManager.getClosestEnemy(player.getPosition(), 1);
        // Assert
        System.out.println(enemy);
        assert(enemy == enemy1 || enemy == enemy2);
    }

    public void testAttackInRangeHitAll() {
        // Arrange
        CLI cli = new CLI();
        Player player = new Mage("TestPlayer", 2000, 100, 100, 1000, 1, 1000, 5, 1);
        player.initializeAndNotify(new Position(5, 5), cli.gameManager);
        Enemy enemy1 = new Monster("TestEnemy1", 100, 100, 1, 100, 100, 'M');
        enemy1.initializeAndNotify(new Position(6, 4), cli.gameManager);
        Enemy enemy2 = new Monster("TestEnemy2", 100, 100, 1, 100, 100, 'M');
        enemy2.initializeAndNotify(new Position(5, 4), cli.gameManager);
        Enemy enemy9 = new Monster("TestEnemy9", 100, 100, 1, 100, 100, 'M');
        enemy9.initializeAndNotify(new Position(7, 5), cli.gameManager);
        // Act
        cli.gameManager.AttackInRange(player, 1, 101, true);
        // Assert
        assert(enemy1.getHealth().HealthAmount == 0 && enemy2.getHealth().getHealthAmount() == 0 &&
                enemy9.getHealth().HealthAmount == 100);
    }
    public void testAttackInRangeHitOne() {
        // Arrange
        CLI cli = new CLI();
        Player player = new Mage("TestPlayer", 2000, 100, 100, 1000, 1, 1000, 5, 1);
        player.initializeAndNotify(new Position(5, 5), cli.gameManager);
        Enemy enemy1 = new Monster("TestEnemy1", 100, 100, 1, 100, 100, 'M');
        enemy1.initializeAndNotify(new Position(6, 4), cli.gameManager);
        Enemy enemy2 = new Monster("TestEnemy2", 100, 100, 1, 100, 100, 'M');
        enemy2.initializeAndNotify(new Position(5, 4), cli.gameManager);
        Enemy enemy9 = new Monster("TestEnemy9", 100, 100, 1, 100, 100, 'M');
        enemy9.initializeAndNotify(new Position(7, 5), cli.gameManager);
        // Act
        cli.gameManager.AttackInRange(player, 1, 101, false);
        // Assert
        assert((enemy1.getHealth().HealthAmount == 100 && enemy2.getHealth().getHealthAmount() == 0 && enemy9.getHealth().HealthAmount == 100) ||
                (enemy1.getHealth().HealthAmount == 0 && enemy2.getHealth().getHealthAmount() == 100 && enemy9.getHealth().HealthAmount == 100));
    }

    public void testMageAttack() {
        // Arrange
        CLI cli = new CLI();
        Player player = new Mage("TestPlayer", 2000, 100, 100, 1000, 1, 1000, 2, 1);
        player.initializeAndNotify(new Position(5, 5), cli.gameManager);
        Enemy enemy1 = new Monster("TestEnemy1", 200, 100, 1, 100, 100, 'M');
        enemy1.initializeAndNotify(new Position(6, 4), cli.gameManager);
        Enemy enemy2 = new Monster("TestEnemy2", 200, 100, 1, 100, 100, 'M');
        enemy2.initializeAndNotify(new Position(5, 4), cli.gameManager);
        Enemy enemy9 = new Monster("TestEnemy9", 200, 100, 1, 100, 100, 'M');
        enemy9.initializeAndNotify(new Position(7, 5), cli.gameManager);
        // Act
        cli.gameManager.MageAttack(player, 1, 2, 100);
        // Assert
        assert((enemy1.getHealth().HealthAmount == 100 && enemy2.getHealth().getHealthAmount() == 100 && enemy9.getHealth().HealthAmount == 200) ||
                (enemy1.getHealth().HealthAmount == 0 && enemy2.getHealth().getHealthAmount() == 200 && enemy9.getHealth().HealthAmount == 200) ||
                (enemy1.getHealth().HealthAmount == 200 && enemy2.getHealth().getHealthAmount() == 0 && enemy9.getHealth().HealthAmount == 200));
    }

    public void testHunterAbillityCast() {
        // Arrange
        CLI cli = new CLI();
        Player player = new Hunter("TestPlayer", 2000, 100, 100, 5);
        player.initializeAndNotify(new Position(5, 5), cli.gameManager);
        Enemy enemy1 = new Monster("TestEnemy1", 100, 100, 1, 100, 100, 'M');
        enemy1.initializeAndNotify(new Position(6, 4), cli.gameManager);
        Enemy enemy2 = new Monster("TestEnemy2", 100, 100, 1, 100, 100, 'M');
        enemy2.initializeAndNotify(new Position(5, 4), cli.gameManager);
        Enemy enemy9 = new Monster("TestEnemy9", 100, 100, 1, 100, 100, 'M');
        enemy9.initializeAndNotify(new Position(7, 5), cli.gameManager);
        // Act
        cli.gameManager.HunterAbillityCast(player, 2, 100);
        // Assert
        assert((enemy1.getHealth().HealthAmount == 100 && enemy2.getHealth().getHealthAmount() == 0 && enemy9.getHealth().HealthAmount == 100) ||
                (enemy1.getHealth().HealthAmount == 0 && enemy2.getHealth().getHealthAmount() == 100 && enemy9.getHealth().HealthAmount == 100));
    }
    public void testHunterAbillityCastNotInRange() {
        // Arrange
        CLI cli = new CLI();
        Player player = new Hunter("TestPlayer", 2000, 100, 100, 1);
        player.initializeAndNotify(new Position(5, 5), cli.gameManager);
        Enemy enemy9 = new Monster("TestEnemy9", 100, 100, 1, 100, 100, 'M');
        enemy9.initializeAndNotify(new Position(7, 5), cli.gameManager);
        // Act
        boolean inRange = cli.gameManager.HunterAbillityCast(player, 1, 100);
        // Assert
        assert(!inRange && enemy9.getHealth().HealthAmount == 100);
    }

    public void testBossAbilityCast() {
        CLI cli = new CLI();
        Player player = new Hunter("TestPlayer", 2000, 100, 1, 1);
        player.initializeAndNotify(new Position(5, 5), cli.gameManager);
        // Act
        cli.gameManager.BossAbilityCast(player,1000);
        // Assert
        assert(player.getHealth().HealthAmount == 1000);
    }
    public void testBossAbilityCastKillPlayer() {
        CLI cli = new CLI();
        Player player = new Hunter("TestPlayer", 2000, 100, 100, 1);
        player.initializeAndNotify(new Position(5, 5), cli.gameManager);
        // Act
        cli.gameManager.BossAbilityCast(player,3000);
        // Assert
        assert(player.getHealth().HealthAmount == 0 && player.getHealth().isDead());
    }




}