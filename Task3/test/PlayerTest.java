import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Player class.
 */
public class PlayerTest {
    private Player player;
    private Board board;

    @BeforeEach
    public void setUp() {
        player = new Player("TestPlayer");
        board = new Board();
        board.generateBoard();
    }

    @Test
    public void testPlayerInitialization() {
        assertEquals("TestPlayer", player.getId());
        assertEquals(0, player.getVictoryPoints());
        assertEquals(0, player.getPlayerSettlements().size());
        assertEquals(0, player.getPlayerCities().size());
        assertEquals(0, player.getPlayerRoads().size());
    }

    @Test
    public void testAddResource() {
        player.addResource(ResourceType.WOOD, 5);
        assertEquals(5, player.getResourceAmount(ResourceType.WOOD));
        
        player.addResource(ResourceType.WOOD, 3);
        assertEquals(8, player.getResourceAmount(ResourceType.WOOD));
    }

    @Test
    public void testRemoveResource() {
        player.addResource(ResourceType.BRICK, 10);
        player.removeResource(ResourceType.BRICK, 3);
        assertEquals(7, player.getResourceAmount(ResourceType.BRICK));
        
        // Should not go below zero
        player.removeResource(ResourceType.BRICK, 20);
        assertEquals(0, player.getResourceAmount(ResourceType.BRICK));
    }

    @Test
    public void testCheckResource() {
        player.addResource(ResourceType.WOOL, 5);
        assertTrue(player.checkResource(ResourceType.WOOL, 5));
        assertTrue(player.checkResource(ResourceType.WOOL, 3));
        assertFalse(player.checkResource(ResourceType.WOOL, 6));
    }

    @Test
    public void testBuildSettlement() {
        // Give player required resources
        player.addResource(ResourceType.WOOD, 1);
        player.addResource(ResourceType.BRICK, 1);
        player.addResource(ResourceType.WOOL, 1);
        player.addResource(ResourceType.GRAIN, 1);
        
        Intersection intersection = board.getIntersection(0);
        int initialPoints = player.getVictoryPoints();
        
        player.buildSettlement(intersection);
        
        assertEquals(1, player.getPlayerSettlements().size());
        assertEquals(initialPoints + 1, player.getVictoryPoints());
        assertTrue(intersection.isOccupied());
        assertEquals(0, player.getResourceAmount(ResourceType.WOOD));
    }

    @Test
    public void testBuildSettlementInsufficientResources() {
        // Don't give enough resources
        player.addResource(ResourceType.WOOD, 1);
        
        Intersection intersection = board.getIntersection(0);
        player.buildSettlement(intersection);
        
        // Settlement should not be built
        assertEquals(0, player.getPlayerSettlements().size());
        assertFalse(intersection.isOccupied());
    }

    @Test
    public void testBuildCity() {
        // First build a settlement
        player.addResource(ResourceType.WOOD, 1);
        player.addResource(ResourceType.BRICK, 1);
        player.addResource(ResourceType.WOOL, 1);
        player.addResource(ResourceType.GRAIN, 1);
        
        Intersection intersection = board.getIntersection(0);
        player.buildSettlement(intersection);
        
        // Now upgrade to city
        player.addResource(ResourceType.ORE, 3);
        player.addResource(ResourceType.GRAIN, 2);
        
        int pointsAfterSettlement = player.getVictoryPoints();
        player.buildCity(intersection);
        
        assertEquals(0, player.getPlayerSettlements().size());
        assertEquals(1, player.getPlayerCities().size());
        assertEquals(pointsAfterSettlement + 1, player.getVictoryPoints());
    }

    @Test
    public void testBuildRoad() {
        player.addResource(ResourceType.WOOD, 1);
        player.addResource(ResourceType.BRICK, 1);
        
        Edge edge = board.getEdges().get(0);
        player.buildRoad(edge);
        
        assertEquals(1, player.getPlayerRoads().size());
        assertTrue(edge.hasRoad());
        assertEquals(0, player.getResourceAmount(ResourceType.WOOD));
        assertEquals(0, player.getResourceAmount(ResourceType.BRICK));
    }

    @Test
    public void testRollDice() {
        for (int i = 0; i < 100; i++) {
            int roll = player.rollDice();
            assertTrue(roll >= 2 && roll <= 12, "Dice roll should be between 2 and 12");
        }
    }

    @Test
    public void testPlayerEquality() {
        Player player2 = new Player("TestPlayer");
        Player player3 = new Player("OtherPlayer");
        
        assertEquals(player, player2);
        assertNotEquals(player, player3);
    }
}
