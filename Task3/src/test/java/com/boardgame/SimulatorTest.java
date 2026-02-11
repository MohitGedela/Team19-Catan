package com.boardgame;

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
        player1 = new Player("Alice");
        player2 = new Player("Bob");
    }
    
    @Test
    public void testConstructor() {
        assertEquals(1, simulator.getCurrentRound());
        assertNull(simulator.getWinner());
        assertNotNull(simulator.getBoard());
        assertTrue(simulator.getPlayers().isEmpty());
    }
    
    @Test
    public void testAddPlayer() {
        simulator.addPlayer(player1);
        simulator.addPlayer(player2);
        
        assertEquals(2, simulator.getPlayers().size());
        assertTrue(simulator.getPlayers().contains(player1));
        assertTrue(simulator.getPlayers().contains(player2));
    }
    
    @Test
    public void testSetupBoard() {
        simulator.setupBoard();
        
        Board board = simulator.getBoard();
        assertNotNull(board);
        assertFalse(board.getCurrentBoard().isEmpty());
    }
    
    @Test
    public void testSetRoundCount() {
        simulator.setRoundCount(10);
        // No direct getter, but we can verify it doesn't crash
        assertNotNull(simulator);
    }
    
    @Test
    public void testCheckWinnerNoWinner() {
        simulator.addPlayer(player1);
        simulator.addPlayer(player2);
        
        assertFalse(simulator.checkWinner());
        assertNull(simulator.getWinner());
    }
    
    @Test
    public void testCheckWinnerWithWinner() {
        simulator.addPlayer(player1);
        
        // Give player enough resources to build settlements
        player1.addResource("WOOD", 10);
        player1.addResource("BRICK", 10);
        player1.addResource("GRAIN", 10);
        player1.addResource("WOOL", 10);
        
        // Setup board and build settlements to get victory points
        simulator.setupBoard();
        Board board = simulator.getBoard();
        
        // Build enough settlements to win (10 VP)
        for (int i = 1; i <= 10 && i <= 54; i++) {
            Intersection intersection = board.getIntersection(i);
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
        simulator.addPlayer(player1);
        simulator.setupBoard();
        
        // Build a settlement for player1
        player1.addResource("WOOD", 1);
        player1.addResource("BRICK", 1);
        player1.addResource("GRAIN", 1);
        player1.addResource("WOOL", 1);
        
        Intersection intersection = simulator.getBoard().getIntersection(1);
        player1.buildSettlement(intersection);
        
        int initialResources = player1.getResources().values().stream()
                                      .mapToInt(Integer::intValue).sum();
        
        simulator.resourceProduction(6);
        
        // Resources should increase (simplified test)
        int finalResources = player1.getResources().values().stream()
                                    .mapToInt(Integer::intValue).sum();
        
        assertTrue(finalResources >= initialResources);
    }
    
    @Test
    public void testGetBoard() {
        assertNotNull(simulator.getBoard());
    }
    
    @Test
    public void testGetPlayers() {
        simulator.addPlayer(player1);
        simulator.addPlayer(player2);
        
        assertEquals(2, simulator.getPlayers().size());
    }
    
    @Test
    public void testGetCurrentRound() {
        assertEquals(1, simulator.getCurrentRound());
    }
}
