import java.util.*;

/**
 * Main game simulator that manages the game flow, players, and board.
 * Orchestrates the entire game from setup to determining a winner.
 */
public class Simulator {
    private int roundCount;
    private int currentRound;
    private Player winningPlayer;
    private Board board;
    private List<Player> players;
    private static final int WINNING_POINTS = 10;

    /**
     * Constructs a Simulator for the game.
     */
    public Simulator() {
        this.roundCount = 0;
        this.currentRound = 0;
        this.winningPlayer = null;
        this.board = new Board();
        this.players = new ArrayList<>();
    }

    /**
     * Runs a complete round of the game.
     * Each player takes a turn in sequence.
     */
    public void runRound() {
        currentRound++;
        System.out.println("\n========== ROUND " + currentRound + " ==========");
        
        for (Player player : players) {
            if (!checkWinner()) {
                playPlayerTurn(player);
            }
        }
        
        roundCount = currentRound;
    }

    /**
     * Plays a single player's turn.
     * 
     * @param player The player taking their turn
     */
    private void playPlayerTurn(Player player) {
        System.out.println("\n--- Player " + player.getId() + "'s Turn ---");
        
        // Roll dice
        int diceRoll = player.rollDice();
        System.out.println("Player " + player.getId() + " rolled: " + diceRoll);
        
        // Distribute resources based on dice roll
        resourceProduction(diceRoll);
        
        // Player can build (simplified - in real game, player would choose actions)
        // For demo purposes, try to build if resources allow
        attemptBuilding(player);
        
        System.out.println("Player " + player.getId() + " ends turn with " + 
                         player.getVictoryPoints() + " victory points.");
    }

    /**
     * Distributes resources to players based on dice roll.
     * 
     * @param resourceNum The number rolled on the dice
     */
    public void resourceProduction(int resourceNum) {
        System.out.println("Producing resources for roll: " + resourceNum);
        
        // Find all hex tiles with the rolled number
        for (Map.Entry<Integer, HexTerrain> entry : board.getCurrentBoard().entrySet()) {
            HexTerrain hex = entry.getValue();
            
            if (hex.getHexNumber() == resourceNum && hex.getResource() != null) {
                // Give resources to players with settlements/cities on adjacent intersections
                // This is simplified - actual implementation would check adjacency
                distributeResourcesFromHex(hex);
            }
        }
    }

    /**
     * Distributes resources from a specific hex to adjacent players.
     * 
     * @param hex The hex tile producing resources
     */
    private void distributeResourcesFromHex(HexTerrain hex) {
        // In a real implementation, you would:
        // 1. Find all intersections adjacent to this hex
        // 2. Check if they have settlements or cities
        // 3. Give 1 resource per settlement, 2 per city to the owner
        
        // Simplified version for demonstration
        for (Player player : players) {
            // Give some resources randomly for demo purposes
            if (Math.random() < 0.3) {
                ResourceType resource = hex.getResource();
                if (resource != null) {
                    player.addResource(resource, 1);
                    System.out.println("  Player " + player.getId() + " received 1 " + resource);
                }
            }
        }
    }

    /**
     * Attempts to have a player build something if they have resources.
     * 
     * @param player The player attempting to build
     */
    private void attemptBuilding(Player player) {
        // Try to build a settlement if possible
        if (player.checkResource(ResourceType.WOOD, 1) &&
            player.checkResource(ResourceType.BRICK, 1) &&
            player.checkResource(ResourceType.WOOL, 1) &&
            player.checkResource(ResourceType.GRAIN, 1)) {
            
            // Find an available intersection
            for (Intersection intersection : board.getConnections().values()) {
                if (!intersection.isOccupied()) {
                    player.buildSettlement(intersection);
                    board.addBuilding(intersection.getIntersectionBuilding());
                    break;
                }
            }
        }
        
        // Try to upgrade to a city if possible
        else if (player.checkResource(ResourceType.ORE, 3) &&
                 player.checkResource(ResourceType.GRAIN, 2)) {
            
            // Find a settlement owned by this player
            for (Intersection intersection : board.getConnections().values()) {
                Building building = intersection.getIntersectionBuilding();
                if (building instanceof Settlement && building.getOwner().equals(player)) {
                    player.buildCity(intersection);
                    break;
                }
            }
        }
        
        // Try to build a road if possible
        else if (player.checkResource(ResourceType.WOOD, 1) &&
                 player.checkResource(ResourceType.BRICK, 1)) {
            
            // Find an available edge
            for (Edge edge : board.getEdges()) {
                if (!edge.hasRoad()) {
                    player.buildRoad(edge);
                    break;
                }
            }
        }
    }

    /**
     * Checks if any player has won the game.
     * 
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
     * 
     * @return The Player who won, or null if no winner yet
     */
    public Player getWinner() {
        return winningPlayer;
    }

    /**
     * Shows a player's move (for display purposes).
     * 
     * @param player The player whose move to show
     * @param move Description of the move
     */
    public void showPlayerMove(Player player, String move) {
        System.out.println("Player " + player.getId() + " " + move);
    }

    /**
     * Sets up the game board and initializes everything.
     */
    public void setupRound() {
        System.out.println("Setting up game...");
        board.setupBoard();
        
        // Give each player some starting resources
        for (Player player : players) {
            player.addResource(ResourceType.WOOD, 2);
            player.addResource(ResourceType.BRICK, 2);
            player.addResource(ResourceType.WOOL, 1);
            player.addResource(ResourceType.GRAIN, 1);
            System.out.println("Player " + player.getId() + " ready with starting resources.");
        }
        
        System.out.println("Game setup complete!\n");
    }

    /**
     * Adds a player to the game.
     * 
     * @param player The player to add
     */
    public void addPlayer(Player player) {
        players.add(player);
        System.out.println("Player " + player.getId() + " joined the game.");
    }

    /**
     * Gets all players in the game.
     * 
     * @return List of all players
     */
    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    /**
     * Gets the game board.
     * 
     * @return The Board object
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Gets the current round number.
     * 
     * @return The current round
     */
    public int getCurrentRound() {
        return currentRound;
    }

    /**
     * Displays the current game state.
     */
    public void displayGameState() {
        System.out.println("\n========== GAME STATE ==========");
        System.out.println("Round: " + currentRound);
        System.out.println("\nPlayers:");
        for (Player player : players) {
            System.out.println("  " + player);
            System.out.println("    Resources: " + player.getResources());
        }
        board.displayBoard();
    }

    /**
     * Runs the complete game simulation until there's a winner.
     * 
     * @param maxRounds Maximum number of rounds to play
     */
    public void runGame(int maxRounds) {
        System.out.println("\n========================================");
        System.out.println("    STARTING GAME SIMULATION");
        System.out.println("========================================");
        
        setupRound();
        
        while (currentRound < maxRounds && !checkWinner()) {
            runRound();
        }
        
        System.out.println("\n========================================");
        System.out.println("    GAME OVER");
        System.out.println("========================================");
        
        if (winningPlayer != null) {
            System.out.println("Winner: Player " + winningPlayer.getId() + 
                             " with " + winningPlayer.getVictoryPoints() + " victory points!");
        } else {
            System.out.println("Game ended after " + maxRounds + " rounds with no winner.");
            // Find player with most points
            Player leader = players.stream()
                .max(Comparator.comparingInt(Player::getVictoryPoints))
                .orElse(null);
            if (leader != null) {
                System.out.println("Leader: Player " + leader.getId() + 
                                 " with " + leader.getVictoryPoints() + " victory points.");
            }
        }
        
        displayGameState();
    }
}
