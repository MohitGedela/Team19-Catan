package com.boardgame;

import java.util.*;

/**
 * Represents the game board with hexagonal tiles, intersections, edges, and buildings.
 * The board manages the spatial layout and terrain distribution.
 */
public class Board {
    private Map<Integer, HexTerrain> gameBoard; // Hex ID -> Terrain type
    private Map<Integer, Intersection> connections; // Intersection ID -> Intersection
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
     * Generates the game board with hexagonal terrain tiles.
     * Creates a standard board layout with various terrain types.
     */
    public void generateBoard() {
        // Create hex tiles with different terrains
        gameBoard.put(1, HexTerrain.FOREST);
        gameBoard.put(2, HexTerrain.HILLS);
        gameBoard.put(3, HexTerrain.MOUNTAINS);
        gameBoard.put(4, HexTerrain.FIELDS);
        gameBoard.put(5, HexTerrain.PASTURE);
        gameBoard.put(6, HexTerrain.FOREST);
        gameBoard.put(7, HexTerrain.HILLS);
        gameBoard.put(8, HexTerrain.FIELDS);
        gameBoard.put(9, HexTerrain.PASTURE);
        gameBoard.put(10, HexTerrain.MOUNTAINS);
        gameBoard.put(11, HexTerrain.FOREST);
        gameBoard.put(12, HexTerrain.FIELDS);
        gameBoard.put(13, HexTerrain.HILLS);
        gameBoard.put(14, HexTerrain.PASTURE);
        gameBoard.put(15, HexTerrain.MOUNTAINS);
        gameBoard.put(16, HexTerrain.FOREST);
        gameBoard.put(17, HexTerrain.FIELDS);
        gameBoard.put(18, HexTerrain.PASTURE);
        gameBoard.put(19, HexTerrain.DESERT);
        
        // Generate intersections
        for (int i = 1; i <= 54; i++) {
            connections.put(i, new Intersection(i));
        }
        
        // Generate edges between intersections
        generateEdges();
        
        System.out.println("Board generated with " + gameBoard.size() + " hexes, " + 
                         connections.size() + " intersections, and " + edges.size() + " edges.");
    }
    
    /**
     * Generates edges connecting intersections.
     * This creates the road network on the board.
     */
    private void generateEdges() {
        // Example edge connections (simplified)
        // In a real implementation, this would connect all adjacent intersections
        for (int i = 1; i < connections.size(); i++) {
            if (connections.containsKey(i) && connections.containsKey(i + 1)) {
                edges.add(new Edge(connections.get(i), connections.get(i + 1)));
            }
        }
        // Add more complex connections based on hex geometry
        // This is simplified; actual hex board would have more intricate connections
    }
    
    /**
     * Gets the current game board configuration.
     * @return Map of hex IDs to terrain types
     */
    public Map<Integer, HexTerrain> getCurrentBoard() {
        return new HashMap<>(gameBoard);
    }
    
    /**
     * Gets a hex tile by its number.
     * @param tileNum The tile number to retrieve
     * @return The terrain type at that hex, or null if not found
     */
    public HexTerrain getHexTile(int tileNum) {
        return gameBoard.get(tileNum);
    }
    
    /**
     * Sets up the initial board state.
     */
    public void setupBoard() {
        generateBoard();
        System.out.println("Board setup complete!");
    }
    
    /**
     * Gets an intersection by its ID.
     * @param intersectionId The intersection ID
     * @return The intersection, or null if not found
     */
    public Intersection getIntersection(int intersectionId) {
        return connections.get(intersectionId);
    }
    
    /**
     * Gets an edge connecting two intersections.
     * @param intersection1 First intersection ID
     * @param intersection2 Second intersection ID
     * @return The edge connecting them, or null if not found
     */
    public Edge getEdge(int intersection1, int intersection2) {
        for (Edge edge : edges) {
            if ((edge.getStart().getIntersectionLocation() == intersection1 && 
                 edge.getEnd().getIntersectionLocation() == intersection2) ||
                (edge.getStart().getIntersectionLocation() == intersection2 && 
                 edge.getEnd().getIntersectionLocation() == intersection1)) {
                return edge;
            }
        }
        return null;
    }
    
    /**
     * Adds a building to the board.
     * @param building The building to add
     */
    public void addBuilding(Building building) {
        buildings.add(building);
    }
    
    /**
     * Gets all buildings on the board.
     * @return List of all buildings
     */
    public List<Building> getBuildings() {
        return new ArrayList<>(buildings);
    }
    
    /**
     * Gets all edges on the board.
     * @return List of all edges
     */
    public List<Edge> getEdges() {
        return new ArrayList<>(edges);
    }
    
    /**
     * Gets all intersections on the board.
     * @return Collection of all intersections
     */
    public Collection<Intersection> getIntersections() {
        return new ArrayList<>(connections.values());
    }
    
    /**
     * Displays the current state of the board.
     */
    public void displayBoard() {
        System.out.println("\n=== BOARD STATE ===");
        System.out.println("Hexes: " + gameBoard.size());
        System.out.println("Intersections: " + connections.size());
        System.out.println("Edges: " + edges.size());
        System.out.println("Buildings: " + buildings.size());
        
        System.out.println("\nTerrain Distribution:");
        Map<HexTerrain, Integer> terrainCount = new HashMap<>();
        for (HexTerrain terrain : gameBoard.values()) {
            terrainCount.put(terrain, terrainCount.getOrDefault(terrain, 0) + 1);
        }
        for (Map.Entry<HexTerrain, Integer> entry : terrainCount.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
    }
}
