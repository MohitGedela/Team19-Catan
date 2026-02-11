import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Intersection, Edge, and Road classes.
 */
public class IntersectionAndEdgeTest {
    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player("TestPlayer");
    }

    @Test
    public void testIntersectionCreation() {
        Intersection intersection = new Intersection(5);
        
        assertEquals(5, intersection.getIntersectionLocation());
        assertFalse(intersection.isOccupied());
        assertNull(intersection.getIntersectionBuilding());
    }

    @Test
    public void testIntersectionOccupancy() {
        Intersection intersection = new Intersection(0);
        Settlement settlement = new Settlement(player, intersection);
        
        intersection.setOccupiedBy(settlement);
        
        assertTrue(intersection.isOccupied());
        assertEquals(settlement, intersection.getIntersectionBuilding());
    }

    @Test
    public void testEdgeCreation() {
        Intersection start = new Intersection(0);
        Intersection end = new Intersection(1);
        Edge edge = new Edge(start, end);
        
        assertEquals(start, edge.getStart());
        assertEquals(end, edge.getEnd());
        assertFalse(edge.hasRoad());
        assertNull(edge.getOwner());
    }

    @Test
    public void testEdgeWithRoad() {
        Intersection start = new Intersection(0);
        Intersection end = new Intersection(1);
        Edge edge = new Edge(start, end);
        Road road = new Road(player, edge);
        
        edge.setRoad(road);
        
        assertTrue(edge.hasRoad());
        assertEquals(player, edge.getOwner());
        assertEquals(road, edge.getRoad());
    }

    @Test
    public void testRoadCreation() {
        Intersection start = new Intersection(0);
        Intersection end = new Intersection(1);
        Edge edge = new Edge(start, end);
        Road road = new Road(player, edge);
        
        assertEquals(player, road.getOwner());
        assertEquals(edge, road.getLocation());
    }
}
