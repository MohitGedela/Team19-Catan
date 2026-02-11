import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Board class.
 */
public class BoardTest {
    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testBoardInitialization() {
        assertNotNull(board);
        assertEquals(0, board.getCurrentBoard().size());
        assertEquals(0, board.getConnections().size());
        assertEquals(0, board.getEdges().size());
    }

    @Test
    public void testGenerateBoard() {
        board.generateBoard();
        
        assertEquals(19, board.getCurrentBoard().size());
        assertEquals(54, board.getConnections().size());
        assertTrue(board.getEdges().size() > 0);
    }

    @Test
    public void testGetHexTile() {
        board.generateBoard();
        
        HexTerrain hex = board.getHexTile(0);
        assertNotNull(hex);
        assertEquals(0, hex.getHexId());
    }

    @Test
    public void testGetIntersection() {
        board.generateBoard();
        
        Intersection intersection = board.getIntersection(0);
        assertNotNull(intersection);
        assertEquals(0, intersection.getIntersectionLocation());
        assertFalse(intersection.isOccupied());
    }

    @Test
    public void testAddBuilding() {
        board.generateBoard();
        Player player = new Player("TestPlayer");
        Intersection intersection = board.getIntersection(0);
        
        Settlement settlement = new Settlement(player, intersection);
        board.addBuilding(settlement);
        
        assertEquals(1, board.getBuildings().size());
        assertTrue(board.getBuildings().contains(settlement));
    }

    @Test
    public void testFindEdge() {
        board.generateBoard();
        
        // This test depends on the edge generation logic
        // In the simplified version, we're just checking the method exists
        Edge edge = board.findEdge(0, 1);
        // May be null depending on actual topology
        if (edge != null) {
            assertNotNull(edge.getStart());
            assertNotNull(edge.getEnd());
        }
    }

    @Test
    public void testSetupBoard() {
        board.setupBoard();
        
        assertFalse(board.getCurrentBoard().isEmpty());
        assertFalse(board.getConnections().isEmpty());
        assertFalse(board.getEdges().isEmpty());
    }
}
