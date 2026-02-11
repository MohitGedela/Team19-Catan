package com.boardgame;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Building, Settlement, and City classes.
 */
public class BuildingTest {
    
    private Player player;
    private Intersection intersection;
    
    @BeforeEach
    public void setUp() {
        player = new Player("TestPlayer");
        intersection = new Intersection(1);
    }
    
    @Test
    public void testSettlementConstructor() {
        Settlement settlement = new Settlement(player, intersection);
        
        assertEquals(player, settlement.getOwner());
        assertEquals(intersection, settlement.getLocation());
        assertEquals("Settlement", settlement.getBuildingType());
    }
    
    @Test
    public void testSettlementVictoryPoints() {
        Settlement settlement = new Settlement(player, intersection);
        
        assertEquals(1, settlement.getVictoryPoints());
    }
    
    @Test
    public void testSettlementToString() {
        Settlement settlement = new Settlement(player, intersection);
        
        String result = settlement.toString();
        assertTrue(result.contains("Settlement"));
        assertTrue(result.contains(player.getId()));
    }
    
    @Test
    public void testCityConstructor() {
        City city = new City(player, intersection);
        
        assertEquals(player, city.getOwner());
        assertEquals(intersection, city.getLocation());
        assertEquals("City", city.getBuildingType());
    }
    
    @Test
    public void testCityVictoryPoints() {
        City city = new City(player, intersection);
        
        assertEquals(2, city.getVictoryPoints());
    }
    
    @Test
    public void testCityToString() {
        City city = new City(player, intersection);
        
        String result = city.toString();
        assertTrue(result.contains("City"));
        assertTrue(result.contains(player.getId()));
    }
    
    @Test
    public void testSettlementVsCityVictoryPoints() {
        Settlement settlement = new Settlement(player, intersection);
        City city = new City(player, new Intersection(2));
        
        assertTrue(city.getVictoryPoints() > settlement.getVictoryPoints());
        assertEquals(2, city.getVictoryPoints());
        assertEquals(1, settlement.getVictoryPoints());
    }
}
