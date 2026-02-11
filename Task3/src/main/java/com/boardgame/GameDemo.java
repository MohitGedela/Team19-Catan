package com.boardgame;

/**
 * Demonstration class showing how to use the board game framework.
 * Creates a sample game with players and simulates gameplay.
 */
public class GameDemo {
    
    /**
     * Main method to run the game demonstration.
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   BOARD GAME SIMULATOR DEMO");
        System.out.println("========================================\n");
        
        // Create the game simulator
        Simulator simulator = new Simulator();
        
        // Add players
        Player player1 = new Player("Alice");
        Player player2 = new Player("Bob");
        Player player3 = new Player("Charlie");
        
        simulator.addPlayer(player1);
        simulator.addPlayer(player2);
        simulator.addPlayer(player3);
        
        // Setup the game
        simulator.setupBoard();
        Board board = simulator.getBoard();
        
        System.out.println("\n--- Initial Setup Phase ---");
        
        // Give players some starting resources
        player1.addResource("WOOD", 4);
        player1.addResource("BRICK", 4);
        player1.addResource("GRAIN", 3);
        player1.addResource("WOOL", 3);
        player1.addResource("ORE", 2);
        
        player2.addResource("WOOD", 3);
        player2.addResource("BRICK", 3);
        player2.addResource("GRAIN", 4);
        player2.addResource("WOOL", 2);
        player2.addResource("ORE", 3);
        
        player3.addResource("WOOD", 2);
        player3.addResource("BRICK", 2);
        player3.addResource("GRAIN", 2);
        player3.addResource("WOOL", 4);
        player3.addResource("ORE", 4);
        
        // Build some initial structures
        System.out.println("\n--- Building Phase ---");
        
        // Player 1 builds settlements and roads
        Intersection int1 = board.getIntersection(5);
        Intersection int2 = board.getIntersection(12);
        player1.buildSettlement(int1);
        player1.buildSettlement(int2);
        
        Edge edge1 = board.getEdge(5, 6);
        if (edge1 != null) {
            player1.buildRoad(edge1);
        }
        
        // Player 2 builds settlements
        Intersection int3 = board.getIntersection(20);
        Intersection int4 = board.getIntersection(25);
        player2.buildSettlement(int3);
        player2.buildSettlement(int4);
        
        Edge edge2 = board.getEdge(20, 21);
        if (edge2 != null) {
            player2.buildRoad(edge2);
        }
        
        // Player 3 builds settlements
        Intersection int5 = board.getIntersection(35);
        player3.buildSettlement(int5);
        
        Edge edge3 = board.getEdge(35, 36);
        if (edge3 != null) {
            player3.buildRoad(edge3);
        }
        
        // Give more resources to demonstrate city building
        player1.addResource("ORE", 3);
        player1.addResource("GRAIN", 2);
        
        // Upgrade a settlement to a city
        System.out.println("\n--- City Upgrade Phase ---");
        player1.buildCity(int1);
        
        // Display current game state
        simulator.displayGameState();
        
        // Run a few rounds of the game
        System.out.println("\n\n--- Starting Game Rounds ---");
        simulator.setRoundCount(5);
        
        for (int round = 1; round <= 3; round++) {
            simulator.runRound();
        }
        
        // Final game state
        System.out.println("\n\n========================================");
        System.out.println("   FINAL GAME STATE");
        System.out.println("========================================");
        simulator.displayGameState();
        
        // Show winner if there is one
        Player winner = simulator.getWinner();
        if (winner != null) {
            System.out.println("\n*** WINNER: " + winner.getId() + " ***");
            System.out.println("Victory Points: " + winner.getVictoryPoints());
        } else {
            System.out.println("\nNo winner yet. Game continues...");
        }
        
        // Demonstrate additional features
        demonstrateFeatures(board, player1, player2, player3);
    }
    
    /**
     * Demonstrates additional features of the framework.
     */
    private static void demonstrateFeatures(Board board, Player p1, Player p2, Player p3) {
        System.out.println("\n\n========================================");
        System.out.println("   FEATURE DEMONSTRATION");
        System.out.println("========================================");
        
        // Demonstrate terrain and resource information
        System.out.println("\n--- Terrain Information ---");
        for (int i = 1; i <= 5; i++) {
            HexTerrain terrain = board.getHexTile(i);
            if (terrain != null) {
                System.out.println("Hex " + i + ": " + terrain + 
                                 " (Produces: " + terrain.getResource() + ")");
            }
        }
        
        // Demonstrate resource checking
        System.out.println("\n--- Resource Check ---");
        System.out.println(p1.getId() + " has enough WOOD for road? " + 
                         p1.checkResource("WOOD", 1));
        System.out.println(p1.getId() + " has enough ORE for city? " + 
                         p1.checkResource("ORE", 3));
        
        // Show player statistics
        System.out.println("\n--- Player Statistics ---");
        for (Player player : new Player[]{p1, p2, p3}) {
            System.out.println("\n" + player.getId() + ":");
            System.out.println("  Victory Points: " + player.getVictoryPoints());
            System.out.println("  Settlements: " + player.getSettlements().size());
            System.out.println("  Cities: " + player.getCities().size());
            System.out.println("  Roads: " + player.getRoads().size());
            System.out.println("  Total Resources: " + 
                             player.getResources().values().stream()
                                   .mapToInt(Integer::intValue).sum());
        }
    }
}
