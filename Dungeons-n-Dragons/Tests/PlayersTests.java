import org.junit.Test;


public class PlayersTests {

    ////////////////////////////////////
    // Movement and Interaction Tests //
    ////////////////////////////////////

    @Test 
    public void playerMoveUpTest() {
        // Arrange
        CLI cli = new CLI();
        Player player = new Warrior("TestPlayer", 2000, 2000, 100, 1);
        player.initializeAndNotify(new Position(5, 5), cli.gameManager);
        // Act
        player.moveUp();
        // Assert
        assert(player.getPosition().equals(new Position(5, 4)));
    }

    @Test
    public void playerMoveDownTest() {
        // Arrange
        CLI cli = new CLI();
        Player player = new Warrior("TestPlayer", 2000, 2000, 100, 1);
        player.initializeAndNotify(new Position(5, 5), cli.gameManager);
        // Act
        player.moveDown();
        // Assert
        assert(player.getPosition().equals(new Position(5, 6)));
    }

    @Test
    public void playerMoveLeftTest() {
        // Arrange
        CLI cli = new CLI();
        Player player = new Warrior("TestPlayer", 2000, 2000, 100, 1);
        player.initializeAndNotify(new Position(5, 5), cli.gameManager);
        // Act
        player.moveLeft();
        // Assert
        assert(player.getPosition().equals(new Position(4, 5)));
    }

    @Test
    public void playerMoveRightTest() {
        // Arrange
        CLI cli = new CLI();
        Player player = new Warrior("TestPlayer", 2000, 2000, 100, 1);
        player.initializeAndNotify(new Position(5, 5), cli.gameManager);
        // Act
        player.moveRight();
        // Assert
        assert(player.getPosition().equals(new Position(6, 5)));
    }

    @Test
    public void playerInteractWithEnemyTest() {
        // Arrange
        CLI cli = new CLI();
        Player player = new Warrior("TestPlayer", 2000, 2000, 100, 1);
        player.initializeAndNotify(new Position(5, 5), cli.gameManager);
        Enemy enemy = new Monster("TestEnemy", 1, 100, 1, 100, 100, 'M');
        enemy.initializeAndNotify(new Position(5, 6), cli.gameManager);
        // Act
        player.moveDown();
        // Assert
        assert(enemy.getHealth().getHealthAmount() == 0);
    }
    @Test
    public void playerInteractWithEnemyNotKillTest() {
        // Arrange
        CLI cli = new CLI();
        Player player = new Warrior("TestPlayer", 2000, 100, 100, 1);
        player.initializeAndNotify(new Position(5, 5), cli.gameManager);
        Enemy enemy = new Monster("TestEnemy", 101, 100, 1, 100, 100, 'M');
        enemy.initializeAndNotify(new Position(5, 6), cli.gameManager);
        // Act
        player.moveDown();
        // Assert
        assert(enemy.getHealth().getHealthAmount() != 0 && enemy.getHealth().getHealthAmount() != 101 &&
                player.getPosition().equals(new Position(5, 5)));
    }

    @Test
    public void playerInteractWithWallTest() {
        // Arrange
        CLI cli = new CLI();
        Player player = new Warrior("TestPlayer", 2000, 2000, 100, 1);
        player.initializeAndNotify(new Position(5, 5), cli.gameManager);
        Wall wall = new Wall();
        wall.initializeAndNotify(new Position(5, 6), cli.gameManager);
        // Act
        player.moveDown();
        // Assert
        assert(player.getPosition().equals(new Position(5, 5)));
    }

    ////////////////////////
    // Cast Ability Tests //
    ////////////////////////            

    // Warrior Tests

    @Test
    public void warriorCastAbilityTest() {
        // Arrange
        CLI cli = new CLI();
        Player player = new Warrior("TestPlayer", 2000, 100, 100, 1);
        player.initializeAndNotify(new Position(5, 5), cli.gameManager);
        Enemy enemy = new Monster("TestEnemy", 100, 100, 1, 100, 100, 'M');
        enemy.initializeAndNotify(new Position(5, 6), cli.gameManager);
        // Act
        player.castAbility();
        // Assert
        assert(enemy.getHealth().getHealthAmount() == 0);
    }

    @Test
    public void warriorCastAbilityOneKillTest() {
        // Arrange
        CLI cli = new CLI();
        Player player = new Warrior("TestPlayer", 2000, 100, 100, 1);
        player.initializeAndNotify(new Position(5, 5), cli.gameManager);
        Enemy enemy1 = new Monster("TestEnemy1", 100, 100, 1, 100, 100, 'M');
        enemy1.initializeAndNotify(new Position(5, 6), cli.gameManager);
        Enemy enemy2 = new Monster("TestEnemy2", 100, 100, 1, 100, 100, 'M');
        enemy2.initializeAndNotify(new Position(6, 5), cli.gameManager);
        // Act
        player.castAbility();
        // Assert
        assert((enemy1.getHealth().getHealthAmount() == 0 && enemy2.getHealth().getHealthAmount() == 100) ||
                (enemy1.getHealth().getHealthAmount() == 100 && enemy2.getHealth().getHealthAmount() == 0));
    }
    @Test
    public void warriorCastAbilityNotKillTest() {
        // Arrange
        CLI cli = new CLI();
        Player player = new Warrior("TestPlayer", 2000, 100, 100, 1);
        player.initializeAndNotify(new Position(5, 5), cli.gameManager);
        Enemy enemy = new Monster("TestEnemy", 201, 100, 1, 100, 100, 'M');
        enemy.initializeAndNotify(new Position(5, 6), cli.gameManager);
        // Act
        player.castAbility();
        // Assert
        assert(enemy.getHealth().getHealthAmount() != 0 && enemy.getHealth().getHealthAmount() != 201);
    }

    @Test
    public void warriorCastAbilityOnCooldownTest() {
        // Arrange
        CLI cli = new CLI();
        Player player = new Warrior("TestPlayer", 2000, 100, 100, 1);
        player.initializeAndNotify(new Position(5, 5), cli.gameManager);
        Enemy enemy = new Monster("TestEnemy", 100, 100, 1, 100, 100, 'M');
        enemy.initializeAndNotify(new Position(5, 9), cli.gameManager);
        // Act
        player.castAbility();
        enemy.moveUp();
        player.castAbility();
        // Assert
        assert(enemy.getHealth().getHealthAmount() == 100);
    }

    @Test
    public void warriorCastAbilityOutOfRangeTest() {
        // Arrange
        CLI cli = new CLI();
        Player player = new Warrior("TestPlayer", 2000, 100, 100, 1);
        player.initializeAndNotify(new Position(5, 5), cli.gameManager);
        Enemy enemy = new Monster("TestEnemy", 100, 100, 1, 100, 100, 'M');
        enemy.initializeAndNotify(new Position(5, 9), cli.gameManager);
        // Act
        player.castAbility();
        // Assert
        assert(enemy.getHealth().getHealthAmount() == 100);
    }

    // Mage Tests

    @Test
    public void mageCastAbilityTest() {
        // Arrange
        CLI cli = new CLI();
        Player player = new Mage("TestPlayer", 2000, 100, 100, 1000, 1, 1000, 5, 5);
        player.initializeAndNotify(new Position(5, 5), cli.gameManager);
        Enemy enemy = new Monster("TestEnemy", 1, 100, 1, 100, 100, 'M');
        enemy.initializeAndNotify(new Position(5, 6), cli.gameManager);
        // Act
        player.castAbility();
        // Assert
        assert(enemy.getHealth().getHealthAmount() == 0);
    }
    @Test
    public void mageCastAbilityNotKillTest() {
        // Arrange
        CLI cli = new CLI();
        Player player = new Mage("TestPlayer", 2000, 100, 100, 1000, 1, 1000, 5, 5);
        player.initializeAndNotify(new Position(5, 5), cli.gameManager);
        Enemy enemy = new Monster("TestEnemy", 5001, 100, 1, 100, 100, 'M');
        enemy.initializeAndNotify(new Position(5, 6), cli.gameManager);
        // Act
        player.castAbility();
        // Assert
        assert(enemy.getHealth().getHealthAmount() != 0 && enemy.getHealth().getHealthAmount() != 5001);
    }

    @Test
    public void mageCastAbilityOnCooldownTest() {
        // Arrange
        CLI cli = new CLI();
        Player player = new Mage("TestPlayer", 2000, 100, 100, 4, 1, 1000, 5, 3);
        player.initializeAndNotify(new Position(5, 5), cli.gameManager);
        Enemy enemy = new Monster("TestEnemy", 100, 100, 1, 100, 100, 'M');
        enemy.initializeAndNotify(new Position(5, 9), cli.gameManager);
        // Act
        player.castAbility();
        enemy.moveUp();
        player.castAbility();
        // Assert
        assert(enemy.getHealth().getHealthAmount() == 100);
    }

    @Test
    public void mageCastAbilityOutOfRangeTest() {
        // Arrange
        CLI cli = new CLI();
        Player player = new Mage("TestPlayer", 2000, 100, 100, 4, 1, 1000, 5, 3);
        player.initializeAndNotify(new Position(5, 5), cli.gameManager);
        Enemy enemy = new Monster("TestEnemy", 100, 100, 1, 100, 100, 'M');
        enemy.initializeAndNotify(new Position(5, 9), cli.gameManager);
        // Act
        player.castAbility();
        // Assert
        assert(enemy.getHealth().getHealthAmount() == 100);
    }

    // Rogue Tests

    @Test
    public void rogueCastAbilityTest() {
        // Arrange
        CLI cli = new CLI();
        Player player = new Rogue("TestPlayer", 2000, 100, 100, 1);
        player.initializeAndNotify(new Position(5, 5), cli.gameManager);
        Enemy enemy1 = new Monster("TestEnemy", 1, 100, 1, 100, 100, 'M');
        enemy1.initializeAndNotify(new Position(5, 6), cli.gameManager);
        Enemy enemy2 = new Monster("TestEnemy2", 1, 100, 1, 100, 100, 'M');
        enemy2.initializeAndNotify(new Position(6, 5), cli.gameManager);

        // Act
        player.castAbility();
        // Assert
        assert(enemy1.getHealth().getHealthAmount() == 0 && enemy2.getHealth().getHealthAmount() == 0);
    }
    @Test
    public void rogueCastAbilityKillOneTest() {
        // Arrange
        CLI cli = new CLI();
        Player player = new Rogue("TestPlayer", 2000, 100, 100, 1);
        player.initializeAndNotify(new Position(5, 5), cli.gameManager);
        Enemy enemy1 = new Monster("TestEnemy", 101, 100, 1, 100, 100, 'M');
        enemy1.initializeAndNotify(new Position(5, 6), cli.gameManager);
        Enemy enemy2 = new Monster("TestEnemy2", 1, 100, 1, 100, 100, 'M');
        enemy2.initializeAndNotify(new Position(6, 5), cli.gameManager);

        // Act
        player.castAbility();
        // Assert
        assert(enemy1.getHealth().getHealthAmount() != 0 && enemy1.getHealth().getHealthAmount() != 101 &&
                enemy2.getHealth().getHealthAmount() == 0);
    }

    @Test
    public void rogueCastAbilityOutOfRangeTest() {
        // Arrange
        CLI cli = new CLI();
        Player player = new Rogue("TestPlayer", 2000, 100, 100, 1);
        player.initializeAndNotify(new Position(5, 5), cli.gameManager);
        Enemy enemy1 = new Monster("AliveEnemy", 100, 100, 1, 100, 100, 'M');
        enemy1.initializeAndNotify(new Position(5, 9), cli.gameManager);
        Enemy enemy2 = new Monster("DeadEnemy", 100, 100, 1, 100, 100, 'M');
        enemy2.initializeAndNotify(new Position(9, 5), cli.gameManager);
        // Act
        player.castAbility();
        // Assert
        assert(enemy1.getHealth().getHealthAmount() == 100 && enemy2.getHealth().getHealthAmount() == 100);
    }

    // Hunter Tests

    @Test
    public void hunterCastAbilityTest() {
        // Arrange
        CLI cli = new CLI();
        Player player = new Hunter("TestPlayer", 2000, 100, 100, 100);
        player.initializeAndNotify(new Position(5, 5), cli.gameManager);
        Enemy enemy = new Monster("TestEnemy", 1, 100, 1, 100, 100, 'M');
        enemy.initializeAndNotify(new Position(5, 6), cli.gameManager);
        Enemy enemy2 = new Monster("TestEnemy2", 1, 100, 1, 100, 100, 'M');
        enemy2.initializeAndNotify(new Position(5, 7), cli.gameManager);
        // Act
        player.castAbility();
        // Assert
        assert(enemy.getHealth().getHealthAmount() == 0 && enemy2.getHealth().getHealthAmount() == 1);
    }
    @Test
    public void hunterCastAbility2Closest() {
        // Arrange
        CLI cli = new CLI();
        Player player = new Hunter("TestPlayer", 2000, 100, 100, 1);
        player.initializeAndNotify(new Position(5, 5), cli.gameManager);
        Enemy enemy1 = new Monster("TestEnemy1", 1, 100, 1, 100, 100, 'M');
        enemy1.initializeAndNotify(new Position(5, 6), cli.gameManager);
        Enemy enemy2 = new Monster("TestEnemy2", 1, 100, 1, 100, 100, 'M');
        enemy2.initializeAndNotify(new Position(6, 5), cli.gameManager);
        // Act
        player.castAbility();
        // Assert
        assert((enemy1.getHealth().getHealthAmount() == 1 && enemy2.getHealth().getHealthAmount() == 0)||
                (enemy1.getHealth().getHealthAmount() == 0 && enemy2.getHealth().getHealthAmount() == 1));
    }
    @Test
    public void hunterCastAbilityNotKillTest() {
        // Arrange
        CLI cli = new CLI();
        Player player = new Hunter("TestPlayer", 2000, 100, 100, 1);
        player.initializeAndNotify(new Position(5, 5), cli.gameManager);
        Enemy enemy = new Monster("TestEnemy", 101, 100, 1, 100, 100, 'M');
        enemy.initializeAndNotify(new Position(6, 6), cli.gameManager);
        // Act
        player.castAbility();
        // Assert
        assert(enemy.getHealth().getHealthAmount() != 0 && enemy.getHealth().getHealthAmount() != 101);
    }

    @Test
    public void hunterCastAbilityOnCooldownTest() {
        // Arrange
        CLI cli = new CLI();
        Player player = new Hunter("TestPlayer", 2000, 100, 100, 2);
        player.initializeAndNotify(new Position(5, 5), cli.gameManager);
        Enemy enemy1 = new Monster("TestEnemy1", 9999999, 100, 1, 100, 100, 'M');
        enemy1.initializeAndNotify(new Position(5, 6), cli.gameManager);
        Enemy enemy2 = new Monster("TestEnemy2", 1, 100, 1, 100, 100, 'M');
        enemy2.initializeAndNotify(new Position(7, 5), cli.gameManager);
        // Act
        // Cast ability 10 times
        player.castAbility();
        player.castAbility();
        player.castAbility();
        player.castAbility();
        player.castAbility();
        player.castAbility();
        player.castAbility();
        player.castAbility();
        player.castAbility();
        player.castAbility();
        // No arrows left
        enemy1.moveDown();
        enemy1.moveDown();
        player.castAbility();
        // Assert
        assert(enemy2.getHealth().getHealthAmount() == 1);
    }

    @Test
    public void hunterCastAbilityOutOfRangeTest() {
        // Arrange
        CLI cli = new CLI();
        Player player = new Hunter("TestPlayer", 2000, 100, 100, 1);
        player.initializeAndNotify(new Position(5, 5), cli.gameManager);
        Enemy enemy1 = new Monster("TestEnemy1", 1, 100, 1, 100, 100, 'M');
        enemy1.initializeAndNotify(new Position(5, 9), cli.gameManager);
        Enemy enemy2 = new Monster("TestEnemy2", 1, 100, 1, 100, 100, 'M');
        enemy2.initializeAndNotify(new Position(9, 5), cli.gameManager);
        // Act
        player.castAbility();
        // Assert
        assert(enemy1.getHealth().getHealthAmount() == 1 && enemy2.getHealth().getHealthAmount() == 1);
    }

}
