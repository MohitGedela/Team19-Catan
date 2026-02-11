import java.util.*;

/**
 * Represents the game board containing hex tiles, intersections, edges, and buildings.
 */
public class Board {
    private Map<Integer, HexTerrain> gameBoard;
    private Map<Integer, Intersection> connections;
    private List<Edge> edges;
    private List<Building> buildings;

    /**
     * Constructs an empty Board.
     */
    public Board() {
        this.gameBoard = new HashMap<>();
        this.connections = new HashMap<>();
        this.edges = new ArrayList<>();
        this.buildings = new ArrayList<>();
    }

    /**
     * Generates the game board with hex tiles, intersections, and edges.
     * Creates a standard board layout.
     */
    public void generateBoard() {
        System.out.println("Generating game board...");
        
        // Create hex tiles (standard 19-tile board)
        HexTerrain.TerrainType[] terrains = {
            HexTerrain.TerrainType.FOREST, HexTerrain.TerrainType.FOREST, 
            HexTerrain.TerrainType.FOREST, HexTerrain.TerrainType.FOREST,
            HexTerrain.TerrainType.HILLS, HexTerrain.TerrainType.HILLS, 
            HexTerrain.TerrainType.HILLS,
            HexTerrain.TerrainType.MOUNTAINS, HexTerrain.TerrainType.MOUNTAINS, 
            HexTerrain.TerrainType.MOUNTAINS,
            HexTerrain.TerrainType.FIELDS, HexTerrain.TerrainType.FIELDS, 
            HexTerrain.TerrainType.FIELDS, HexTerrain.TerrainType.FIELDS,
            HexTerrain.TerrainType.PASTURE, HexTerrain.TerrainType.PASTURE, 
            HexTerrain.TerrainType.PASTURE, HexTerrain.TerrainType.PASTURE,
            HexTerrain.TerrainType.DESERT
        };
        
        int[] numbers = {5, 2, 6, 3, 8, 10, 9, 12, 11, 4, 8, 10, 9, 4, 5, 6, 3, 11, 0};
        
        for (int i = 0; i < terrains.length; i++) {
            HexTerrain hex = new HexTerrain(i, terrains[i], numbers[i]);
            gameBoard.put(i, hex);
        }
        
        // Generate intersections (54 for a standard board)
        for (int i = 0; i < 54; i++) {
            connections.put(i, new Intersection(i));
        }
        
        // Generate edges (72 for a standard board)
        // This is a simplified edge generation - in a real implementation,
        // you would define the actual topology of the hex grid
        generateEdges();
        
        System.out.println("Board generated with " + gameBoard.size() + " hexes, " + 
                         connections.size() + " intersections, and " + edges.size() + " edges.");
    }

    /**
     * Generates edges connecting intersections.
     * This is a simplified version - actual implementation would match hex grid topology.
     */
    private void generateEdges() {
        // Simplified edge generation
        // In a real implementation, you would define the actual edge connections
        // based on the hexagonal grid structure
        for (int i = 0; i < 50; i++) {
            Intersection start = connections.get(i);
            Intersection end = connections.get((i + 1) % 54);
            if (start != null && end != null) {
                edges.add(new Edge(start, end));
            }
        }
    }

    /**
     * Gets the current board configuration.
     * 
     * @return Map of hex IDs to HexTerrain objects
     */
    public Map<Integer, HexTerrain> getCurrentBoard() {
        return new HashMap<>(gameBoard);
    }

    /**
     * Gets a hex tile by its ID.
     * 
     * @param hexNum The hex tile ID
     * @return The HexTerrain object, or null if not found
     */
    public HexTerrain getHexTile(int hexNum) {
        return gameBoard.get(hexNum);
    }

    /**
     * Sets up the initial board configuration.
     * Called at the start of the game.
     */
    public void setupBoard() {
        generateBoard();
        System.out.println("Board setup complete.");
    }

    /**
     * Gets an intersection by its location ID.
     * 
     * @param location The intersection location ID
     * @return The Intersection object, or null if not found
     */
    public Intersection getIntersection(int location) {
        return connections.get(location);
    }

    /**
     * Gets all intersections on the board.
     * 
     * @return Map of location IDs to Intersection objects
     */
    public Map<Integer, Intersection> getConnections() {
        return new HashMap<>(connections);
    }

    /**
     * Gets all edges on the board.
     * 
     * @return List of Edge objects
     */
    public List<Edge> getEdges() {
        return new ArrayList<>(edges);
    }

    /**
     * Gets all buildings on the board.
     * 
     * @return List of Building objects
     */
    public List<Building> getBuildings() {
        return new ArrayList<>(buildings);
    }

    /**
     * Adds a building to the board.
     * 
     * @param building The building to add
     */
    public void addBuilding(Building building) {
        buildings.add(building);
    }

    /**
     * Finds an edge between two intersections.
     * 
     * @param loc1 First intersection location
     * @param loc2 Second intersection location
     * @return The Edge connecting them, or null if not found
     */
    public Edge findEdge(int loc1, int loc2) {
        Intersection int1 = connections.get(loc1);
        Intersection int2 = connections.get(loc2);
        
        if (int1 == null || int2 == null) {
            return null;
        }
        
        for (Edge edge : edges) {
            if ((edge.getStart().equals(int1) && edge.getEnd().equals(int2)) ||
                (edge.getStart().equals(int2) && edge.getEnd().equals(int1))) {
                return edge;
            }
        }
        return null;
    }

    /**
     * Displays the current state of the board.
     */
    public void displayBoard() {
        System.out.println("\n========== BOARD STATE ==========");
        System.out.println("Hex Tiles:");
        for (Map.Entry<Integer, HexTerrain> entry : gameBoard.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
        
        System.out.println("\nOccupied Intersections:");
        for (Map.Entry<Integer, Intersection> entry : connections.entrySet()) {
            if (entry.getValue().isOccupied()) {
                System.out.println("  " + entry.getValue());
            }
        }
        
        System.out.println("\nRoads:");
        for (Edge edge : edges) {
            if (edge.hasRoad()) {
                System.out.println("  " + edge);
            }
        }
        System.out.println("================================\n");
    }

    @Override
    public String toString() {
        return "Board{" +
                "hexTiles=" + gameBoard.size() +
                ", intersections=" + connections.size() +
                ", edges=" + edges.size() +
                ", buildings=" + buildings.size() +
                '}';
    }
}
