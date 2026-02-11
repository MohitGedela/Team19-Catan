package com.boardgame;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Player class.
 */
public class PlayerTest {
    
    private Player player;
    private Intersection intersection;
    
    @BeforeEach
    public void setUp() {
        player = new Player("Alice");
        intersection = new Intersection(1);
    }
    
    @Test
    public void testConstructor() {
        assertEquals("Alice", player.getId());
        assertEquals(0, player.getVictoryPoints());
        assertEquals(0, player.getResourceAmount("WOOD"));
        assertEquals(0, player.getSettlements().size());
        assertEquals(0, player.getCities().size());
        assertEquals(0, player.getRoads().size());
    }
    
    @Test
    public void testAddResource() {
        player.addResource("WOOD", 3);
        assertEquals(3, player.getResourceAmount("WOOD"));
        
        player.addResource("WOOD", 2);
        assertEquals(5, player.getResourceAmount("WOOD"));
    }
    
    @Test
    public void testRemoveResource() {
        player.addResource("BRICK", 5);
        player.removeResource("BRICK", 2);
        assertEquals(3, player.getResourceAmount("BRICK"));
        
        // Test removing more than available
        player.removeResource("BRICK", 10);
        assertEquals(0, player.getResourceAmount("BRICK"));
    }
    
    @Test
    public void testCheckResource() {
        player.addResource("GRAIN", 4);
        
        assertTrue(player.checkResource("GRAIN", 3));
        assertTrue(player.checkResource("GRAIN", 4));
        assertFalse(player.checkResource("GRAIN", 5));
        assertFalse(player.checkResource("ORE", 1));
    }
    
    @Test
    public void testBuildSettlement() {
        // Give player resources
        player.addResource("WOOD", 1);
        player.addResource("BRICK", 1);
        player.addResource("GRAIN", 1);
        player.addResource("WOOL", 1);
        
        int initialVP = player.getVictoryPoints();
        player.buildSettlement(intersection);
        
        assertEquals(1, player.getSettlements().size());
        assertEquals(initialVP + 1, player.getVictoryPoints());
        assertEquals(0, player.getResourceAmount("WOOD"));
        assertEquals(0, player.getResourceAmount("BRICK"));
        assertEquals(0, player.getResourceAmount("GRAIN"));
        assertEquals(0, player.getResourceAmount("WOOL"));
        assertTrue(intersection.isOccupied());
    }
    
    @Test
    public void testBuildSettlementInsufficientResources() {
        int initialSize = player.getSettlements().size();
        player.buildSettlement(intersection);
        
        // Should not build without resources
        assertEquals(initialSize, player.getSettlements().size());
        assertFalse(intersection.isOccupied());
    }
    
    @Test
    public void testBuildCity() {
        // First build a settlement
        player.addResource("WOOD", 1);
        player.addResource("BRICK", 1);
        player.addResource("GRAIN", 1);
        player.addResource("WOOL", 1);
        player.buildSettlement(intersection);
        
        // Now upgrade to city
        player.addResource("ORE", 3);
        player.addResource("GRAIN", 2);
        
        int vpBeforeUpgrade = player.getVictoryPoints();
        player.buildCity(intersection);
        
        assertEquals(0, player.getSettlements().size());
        assertEquals(1, player.getCities().size());
        assertEquals(vpBeforeUpgrade + 1, player.getVictoryPoints());
        assertEquals(0, player.getResourceAmount("ORE"));
        assertEquals(0, player.getResourceAmount("GRAIN"));
    }
    
    @Test
    public void testBuildCityInsufficientResources() {
        player.addResource("WOOD", 1);
        player.addResource("BRICK", 1);
        player.addResource("GRAIN", 1);
        player.addResource("WOOL", 1);
        player.buildSettlement(intersection);
        
        int initialCities = player.getCities().size();
        player.buildCity(intersection); // No resources for city
        
        assertEquals(initialCities, player.getCities().size());
    }
    
    @Test
    public void testBuildRoad() {
        Intersection int1 = new Intersection(1);
        Intersection int2 = new Intersection(2);
        Edge edge = new Edge(int1, int2);
        
        player.addResource("WOOD", 1);
        player.addResource("BRICK", 1);
        
        player.buildRoad(edge);
        
        assertEquals(1, player.getRoads().size());
        assertEquals(0, player.getResourceAmount("WOOD"));
        assertEquals(0, player.getResourceAmount("BRICK"));
        assertNotNull(edge.getRoad());
    }
    
    @Test
    public void testRollDice() {
        int roll = player.rollDice();
        assertTrue(roll >= 2 && roll <= 12);
    }
    
    @Test
    public void testGetResources() {
        player.addResource("WOOD", 3);
        player.addResource("BRICK", 2);
        
        var resources = player.getResources();
        assertEquals(3, resources.get("WOOD"));
        assertEquals(2, resources.get("BRICK"));
    }
    
    @Test
    public void testEquals() {
        Player player1 = new Player("Alice");
        Player player2 = new Player("Alice");
        Player player3 = new Player("Bob");
        
        assertEquals(player1, player2);
        assertNotEquals(player1, player3);
    }
    
    @Test
    public void testHashCode() {
        Player player1 = new Player("Alice");
        Player player2 = new Player("Alice");
        
        assertEquals(player1.hashCode(), player2.hashCode());
    }
}
