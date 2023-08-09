import org.junit.Test;


public class EnemyTests {

///////////////////////
// Interaction Tests //
///////////////////////
    @Test
    public void enemyInteractWithPlayerTest() {
        // Arrange
        CLI cli = new CLI();
        Enemy enemy = new Monster("Monster", 100, 1000, 10, 10, 3, 'm');
        enemy.initializeAndNotify(new Position(5, 5), cli.gameManager);
        Player player = new Warrior("Warrior", 1, 10, 1, 10);
        player.initializeAndNotify(new Position(5, 4), cli.gameManager);
        // Act
        enemy.moveUp();
        // Assert
        assert(player.getHealth().getHealthAmount() == 0);
    }
    
}

