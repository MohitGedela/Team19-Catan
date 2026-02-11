/**
 * Demo class to showcase the board game system.
 * Demonstrates game setup, player actions, and game flow.
 */
public class GameDemo {
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   BOARD GAME SIMULATOR DEMO");
        System.out.println("========================================\n");
        
        // Create the game simulator
        Simulator game = new Simulator();
        
        // Create players
        Player player1 = new Player("Alice");
        Player player2 = new Player("Bob");
        Player player3 = new Player("Charlie");
        
        // Add players to the game
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);
        
        System.out.println();
        
        // Run the game for a maximum of 20 rounds
        game.runGame(20);
        
        System.out.println("\n========================================");
        System.out.println("   DEMO COMPLETE");
        System.out.println("========================================");
        
        // Additional demonstrations
        demonstratePlayerActions();
        demonstrateBoardFeatures();
    }
    
    /**
     * Demonstrates individual player actions.
     */
    private static void demonstratePlayerActions() {
        System.out.println("\n\n========================================");
        System.out.println("   PLAYER ACTIONS DEMO");
        System.out.println("========================================\n");
        
        Player player = new Player("Demo");
        Board board = new Board();
        board.generateBoard();
        
        // Give player resources
        System.out.println("Giving player resources...");
        player.addResource(ResourceType.WOOD, 5);
        player.addResource(ResourceType.BRICK, 5);
        player.addResource(ResourceType.WOOL, 3);
        player.addResource(ResourceType.GRAIN, 3);
        player.addResource(ResourceType.ORE, 5);
        
        System.out.println("Player resources: " + player.getResources());
        
        // Build a settlement
        System.out.println("\nBuilding a settlement...");
        Intersection intersection1 = board.getIntersection(0);
        player.buildSettlement(intersection1);
        
        // Build a road
        System.out.println("\nBuilding a road...");
        Edge edge = board.getEdges().get(0);
        player.buildRoad(edge);
        
        // Upgrade to a city
        System.out.println("\nUpgrading settlement to city...");
        player.buildCity(intersection1);
        
        System.out.println("\nFinal player state: " + player);
        System.out.println("Resources remaining: " + player.getResources());
    }
    
    /**
     * Demonstrates board features.
     */
    private static void demonstrateBoardFeatures() {
        System.out.println("\n\n========================================");
        System.out.println("   BOARD FEATURES DEMO");
        System.out.println("========================================\n");
        
        Board board = new Board();
        board.generateBoard();
        
        System.out.println("Board created with:");
        System.out.println("  - " + board.getCurrentBoard().size() + " hex tiles");
        System.out.println("  - " + board.getConnections().size() + " intersections");
        System.out.println("  - " + board.getEdges().size() + " edges");
        
        System.out.println("\nSample hex tiles:");
        for (int i = 0; i < 5; i++) {
            HexTerrain hex = board.getHexTile(i);
            if (hex != null) {
                System.out.println("  Hex " + i + ": " + hex.getTerrain() + 
                                 " (Number: " + hex.getHexNumber() + 
                                 ", Resource: " + hex.getResource() + ")");
            }
        }
        
        System.out.println("\nBoard structure: " + board);
    }
}
