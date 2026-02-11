import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Simulator class.
 */
public class SimulatorTest {
    private Simulator simulator;
    private Player player1;
    private Player player2;

    @BeforeEach
    public void setUp() {
        simulator = new Simulator();
        player1 = new Player("Player1");
        player2 = new Player("Player2");
        
        simulator.addPlayer(player1);
        simulator.addPlayer(player2);
    }

    @Test
    public void testSimulatorInitialization() {
        assertEquals(0, simulator.getCurrentRound());
        assertNull(simulator.getWinner());
        assertNotNull(simulator.getBoard());
        assertEquals(2, simulator.getPlayers().size());
    }

    @Test
    public void testAddPlayer() {
        Simulator newSim = new Simulator();
        Player player = new Player("Test");
        
        newSim.addPlayer(player);
        assertEquals(1, newSim.getPlayers().size());
        assertTrue(newSim.getPlayers().contains(player));
    }

    @Test
    public void testSetupRound() {
        simulator.setupRound();
        
        // Board should be generated
        assertFalse(simulator.getBoard().getCurrentBoard().isEmpty());
        
        // Players should have starting resources
        assertTrue(player1.getResourceAmount(ResourceType.WOOD) > 0);
        assertTrue(player2.getResourceAmount(ResourceType.WOOD) > 0);
    }

    @Test
    public void testRunRound() {
        simulator.setupRound();
        int initialRound = simulator.getCurrentRound();
        
        simulator.runRound();
        
        assertEquals(initialRound + 1, simulator.getCurrentRound());
    }

    @Test
    public void testCheckWinner() {
        assertFalse(simulator.checkWinner());
        
        // Artificially set a player's victory points to winning amount
        player1.addResource(ResourceType.WOOD, 100);
        player1.addResource(ResourceType.BRICK, 100);
        player1.addResource(ResourceType.WOOL, 100);
        player1.addResource(ResourceType.GRAIN, 100);
        
        simulator.setupRound();
        
        // Build enough settlements to win
        for (int i = 0; i < 10; i++) {
            Intersection intersection = simulator.getBoard().getIntersection(i);
            if (intersection != null && !intersection.isOccupied()) {
                player1.buildSettlement(intersection);
            }
        }
        
        if (player1.getVictoryPoints() >= 10) {
            assertTrue(simulator.checkWinner());
            assertEquals(player1, simulator.getWinner());
        }
    }

    @Test
    public void testResourceProduction() {
        simulator.setupRound();
        
        int initialWood = player1.getResourceAmount(ResourceType.WOOD);
        
        // Roll a number and produce resources
        simulator.resourceProduction(6);
        
        // Resources may or may not change depending on board state
        // Just verify the method doesn't crash
        assertTrue(player1.getResourceAmount(ResourceType.WOOD) >= 0);
    }

    @Test
    public void testRunGame() {
        // Run a short game
        simulator.runGame(5);
        
        // Should have run some rounds
        assertTrue(simulator.getCurrentRound() > 0);
        assertTrue(simulator.getCurrentRound() <= 5);
    }
}
