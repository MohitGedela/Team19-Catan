package com.boardgame;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Intersection class.
 */
public class IntersectionTest {
    
    private Intersection intersection;
    private Player player;
    
    @BeforeEach
    public void setUp() {
        intersection = new Intersection(10);
        player = new Player("TestPlayer");
    }
    
    @Test
    public void testConstructor() {
        assertEquals(10, intersection.getIntersectionLocation());
        assertNull(intersection.getIntersectionBuilding());
        assertFalse(intersection.isOccupied());
    }
    
    @Test
    public void testSetOccupiedBy() {
        Settlement settlement = new Settlement(player, intersection);
        intersection.setOccupiedBy(settlement);
        
        assertTrue(intersection.isOccupied());
        assertEquals(settlement, intersection.getIntersectionBuilding());
    }
    
    @Test
    public void testIsOccupied() {
        assertFalse(intersection.isOccupied());
        
        Settlement settlement = new Settlement(player, intersection);
        intersection.setOccupiedBy(settlement);
        
        assertTrue(intersection.isOccupied());
    }
    
    @Test
    public void testToString() {
        String result = intersection.toString();
        assertTrue(result.contains("Intersection"));
        assertTrue(result.contains("10"));
        assertTrue(result.contains("empty"));
    }
    
    @Test
    public void testToStringWithBuilding() {
        Settlement settlement = new Settlement(player, intersection);
        intersection.setOccupiedBy(settlement);
        
        String result = intersection.toString();
        assertTrue(result.contains("occupied"));
    }
}
