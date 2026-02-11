package com.boardgame;

import java.util.*;

/**
 * Main game simulator that orchestrates game flow and manages game state.
 * Handles turn management, resource distribution, and win conditions.
 */
public class Simulator {
    private int roundCount;
    private int currentRound;
    private Player winningPlayer;
    private Board board;
    private List<Player> players;
    private static final int WINNING_POINTS = 10;
    
    /**
     * Constructs a Simulator with default settings.
     */
    public Simulator() {
        this.roundCount = 0;
        this.currentRound = 1;
        this.winningPlayer = null;
        this.board = new Board();
        this.players = new ArrayList<>();
    }
    
    /**
     * Initializes and runs a complete game simulation.
     */
    public void run() {
        System.out.println("=== GAME SIMULATION STARTED ===\n");
        
        // Setup
        setupBoard();
        
        // Run rounds until someone wins
        while (winningPlayer == null && currentRound <= roundCount) {
            runRound();
        }
        
        // Display results
        if (winningPlayer != null) {
            System.out.println("\n=== GAME OVER ===");
            System.out.println("Winner: " + winningPlayer.getId());
            System.out.println("Final Score: " + winningPlayer.getVictoryPoints() + " victory points");
        }
    }
    
    /**
     * Runs a single round of the game.
     */
    public void runRound() {
        System.out.println("\n--- Round " + currentRound + " ---");
        
        for (Player player : players) {
            System.out.println("\nPlayer " + player.getId() + "'s turn:");
            
            // Roll dice
            int diceRoll = player.rollDice();
            System.out.println("  Rolled: " + diceRoll);
            
            // Distribute resources based on dice roll
            resourceProduction(diceRoll);
            
            // Player takes their turn
            showPlayerMove(player, player, "Taking turn");
            
            // Check for winner
            if (checkWinner()) {
                return;
            }
        }
        
        currentRound++;
    }
    
    /**
     * Distributes resources to players based on dice roll.
     * @param resourceNum The dice roll number (2-12)
     */
    public void resourceProduction(int resourceNum) {
        System.out.println("  Resource production for roll " + resourceNum);
        
        // Check each hex on the board
        for (Map.Entry<Integer, HexTerrain> entry : board.getCurrentBoard().entrySet()) {
            HexTerrain terrain = entry.getValue();
            
            // Simple resource distribution logic
            // In a real game, this would be based on number tokens on hexes
            if (terrain.producesResource()) {
                // Give resources to players with buildings adjacent to this hex
                // This is simplified; actual implementation would check adjacency
                for (Player player : players) {
                    for (Settlement settlement : player.getSettlements()) {
                        // Simplified: give 1 resource per settlement
                        player.addResource(terrain.getResource(), 1);
                    }
                    for (City city : player.getCities()) {
                        // Cities produce 2 resources
                        player.addResource(terrain.getResource(), 2);
                    }
                }
            }
        }
    }
    
    /**
     * Checks if any player has won the game.
     * @return true if there is a winner, false otherwise
     */
    public boolean checkWinner() {
        for (Player player : players) {
            if (player.getVictoryPoints() >= WINNING_POINTS) {
                winningPlayer = player;
                return true;
            }
        }
        return false;
    }
    
    /**
     * Gets the winning player.
     * @return The player who won, or null if no winner yet
     */
    public Player getWinner() {
        return winningPlayer;
    }
    
    /**
     * Displays a player's move.
     * @param player The player making the move
     * @param mover The player object (same as player)
     * @param move Description of the move
     */
    public void showPlayerMove(Player player, Player mover, String move) {
        System.out.println("  " + player.getId() + ": " + move);
        System.out.println("  Resources: " + player.getResources());
        System.out.println("  Victory Points: " + player.getVictoryPoints());
    }
    
    /**
     * Sets up the game board.
     */
    public void setupBoard() {
        board.setupBoard();
    }
    
    /**
     * Adds a player to the game.
     * @param player The player to add
     */
    public void addPlayer(Player player) {
        players.add(player);
        System.out.println("Player " + player.getId() + " joined the game.");
    }
    
    /**
     * Sets the maximum number of rounds.
     * @param rounds The number of rounds
     */
    public void setRoundCount(int rounds) {
        this.roundCount = rounds;
    }
    
    /**
     * Gets the current round number.
     * @return The current round
     */
    public int getCurrentRound() {
        return currentRound;
    }
    
    /**
     * Gets the game board.
     * @return The board
     */
    public Board getBoard() {
        return board;
    }
    
    /**
     * Gets all players in the game.
     * @return List of players
     */
    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }
    
    /**
     * Displays the current game state.
     */
    public void displayGameState() {
        System.out.println("\n=== CURRENT GAME STATE ===");
        System.out.println("Round: " + currentRound + "/" + roundCount);
        System.out.println("Players: " + players.size());
        
        for (Player player : players) {
            System.out.println("\n" + player);
            System.out.println("  Settlements: " + player.getSettlements().size());
            System.out.println("  Cities: " + player.getCities().size());
            System.out.println("  Roads: " + player.getRoads().size());
        }
        
        board.displayBoard();
    }
}
