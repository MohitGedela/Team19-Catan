package com.boardgame;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Edge and Road classes.
 */
public class EdgeAndRoadTest {
    
    private Intersection start;
    private Intersection end;
    private Edge edge;
    private Player player;
    
    @BeforeEach
    public void setUp() {
        start = new Intersection(1);
        end = new Intersection(2);
        edge = new Edge(start, end);
        player = new Player("TestPlayer");
    }
    
    @Test
    public void testEdgeConstructor() {
        assertEquals(start, edge.getStart());
        assertEquals(end, edge.getEnd());
        assertNull(edge.getRoad());
        assertNull(edge.getOwner());
    }
    
    @Test
    public void testEdgeGetLocation() {
        String location = edge.getLocation();
        assertTrue(location.equals("1-2") || location.equals("2-1"));
    }
    
    @Test
    public void testEdgeSetRoad() {
        Road road = new Road(player, edge);
        edge.setRoad(road);
        
        assertEquals(road, edge.getRoad());
        assertEquals(player, edge.getOwner());
    }
    
    @Test
    public void testEdgeHasRoad() {
        Road road = new Road(player, edge);
        edge.setRoad(road);
        
        assertTrue(edge.hasRoad(road));
        
        Player otherPlayer = new Player("OtherPlayer");
        Road otherRoad = new Road(otherPlayer, edge);
        assertFalse(edge.hasRoad(otherRoad));
    }
    
    @Test
    public void testEdgeToString() {
        String result = edge.toString();
        assertTrue(result.contains("Edge"));
        assertTrue(result.contains("no road"));
        
        Road road = new Road(player, edge);
        edge.setRoad(road);
        
        result = edge.toString();
        assertTrue(result.contains("has road"));
    }
    
    @Test
    public void testRoadConstructor() {
        Road road = new Road(player, edge);
        
        assertEquals(player, road.getOwner());
        assertEquals(edge, road.getLocation());
    }
    
    @Test
    public void testRoadToString() {
        Road road = new Road(player, edge);
        
        String result = road.toString();
        assertTrue(result.contains("Road"));
        assertTrue(result.contains(player.getId()));
    }
    
    @Test
    public void testRoadEquals() {
        Road road1 = new Road(player, edge);
        Road road2 = new Road(player, edge);
        
        assertEquals(road1, road2);
        
        Player otherPlayer = new Player("OtherPlayer");
        Road road3 = new Road(otherPlayer, edge);
        
        assertNotEquals(road1, road3);
    }
    
    @Test
    public void testRoadHashCode() {
        Road road1 = new Road(player, edge);
        Road road2 = new Road(player, edge);
        
        assertEquals(road1.hashCode(), road2.hashCode());
    }
}
