package com.boardgame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the HexTerrain enum.
 */
public class HexTerrainTest {
    
    @Test
    public void testGetHexId() {
        assertEquals(1, HexTerrain.FOREST.getHexId());
        assertEquals(2, HexTerrain.HILLS.getHexId());
        assertEquals(3, HexTerrain.MOUNTAINS.getHexId());
        assertEquals(4, HexTerrain.FIELDS.getHexId());
        assertEquals(5, HexTerrain.PASTURE.getHexId());
        assertEquals(6, HexTerrain.DESERT.getHexId());
    }
    
    @Test
    public void testGetTerrainByHexNumber() {
        assertEquals(HexTerrain.FOREST, HexTerrain.getTerrainByHexNumber(1));
        assertEquals(HexTerrain.HILLS, HexTerrain.getTerrainByHexNumber(2));
        assertEquals(HexTerrain.DESERT, HexTerrain.getTerrainByHexNumber(6));
        assertNull(HexTerrain.getTerrainByHexNumber(99));
    }
    
    @Test
    public void testProducesResource() {
        assertTrue(HexTerrain.FOREST.producesResource());
        assertTrue(HexTerrain.HILLS.producesResource());
        assertTrue(HexTerrain.MOUNTAINS.producesResource());
        assertTrue(HexTerrain.FIELDS.producesResource());
        assertTrue(HexTerrain.PASTURE.producesResource());
        assertFalse(HexTerrain.DESERT.producesResource());
    }
    
    @Test
    public void testGetResource() {
        assertEquals("WOOD", HexTerrain.FOREST.getResource());
        assertEquals("BRICK", HexTerrain.HILLS.getResource());
        assertEquals("ORE", HexTerrain.MOUNTAINS.getResource());
        assertEquals("GRAIN", HexTerrain.FIELDS.getResource());
        assertEquals("WOOL", HexTerrain.PASTURE.getResource());
        assertEquals("NONE", HexTerrain.DESERT.getResource());
    }
}
