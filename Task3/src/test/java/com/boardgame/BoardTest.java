package com.boardgame;

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
    public void testGenerateBoard() {
        board.generateBoard();
        
        assertFalse(board.getCurrentBoard().isEmpty());
        assertFalse(board.getIntersections().isEmpty());
        assertFalse(board.getEdges().isEmpty());
    }
    
    @Test
    public void testSetupBoard() {
        board.setupBoard();
        
        assertTrue(board.getCurrentBoard().size() > 0);
        assertTrue(board.getIntersections().size() > 0);
    }
    
    @Test
    public void testGetHexTile() {
        board.generateBoard();
        
        HexTerrain terrain = board.getHexTile(1);
        assertNotNull(terrain);
        assertEquals(HexTerrain.FOREST, terrain);
    }
    
    @Test
    public void testGetHexTileInvalid() {
        board.generateBoard();
        
        HexTerrain terrain = board.getHexTile(999);
        assertNull(terrain);
    }
    
    @Test
    public void testGetIntersection() {
        board.generateBoard();
        
        Intersection intersection = board.getIntersection(1);
        assertNotNull(intersection);
        assertEquals(1, intersection.getIntersectionLocation());
    }
    
    @Test
    public void testGetEdge() {
        board.generateBoard();
        
        Edge edge = board.getEdge(1, 2);
        assertNotNull(edge);
    }
    
    @Test
    public void testAddBuilding() {
        board.generateBoard();
        Player player = new Player("TestPlayer");
        Intersection intersection = board.getIntersection(1);
        Settlement settlement = new Settlement(player, intersection);
        
        board.addBuilding(settlement);
        
        assertTrue(board.getBuildings().contains(settlement));
    }
    
    @Test
    public void testGetCurrentBoard() {
        board.generateBoard();
        
        var gameBoard = board.getCurrentBoard();
        assertNotNull(gameBoard);
        assertTrue(gameBoard.size() > 0);
    }
    
    @Test
    public void testGetIntersections() {
        board.generateBoard();
        
        var intersections = board.getIntersections();
        assertNotNull(intersections);
        assertEquals(54, intersections.size());
    }
    
    @Test
    public void testGetEdges() {
        board.generateBoard();
        
        var edges = board.getEdges();
        assertNotNull(edges);
        assertTrue(edges.size() > 0);
    }
}
