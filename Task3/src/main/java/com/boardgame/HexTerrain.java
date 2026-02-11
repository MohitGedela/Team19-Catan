package com.boardgame;

/**
 * Enum representing different types of terrain on hexagonal tiles.
 * Each terrain type has an ID and provides a resource.
 */
public enum HexTerrain {
    FOREST(1),
    HILLS(2),
    MOUNTAINS(3),
    FIELDS(4),
    PASTURE(5),
    DESERT(6);
    
    private final int hexId;
    
    /**
     * Constructor for HexTerrain enum.
     * @param hexId The unique identifier for this terrain type
     */
    HexTerrain(int hexId) {
        this.hexId = hexId;
    }
    
    /**
     * Gets the hex ID for this terrain type.
     * @return The hex ID
     */
    public int getHexId() {
        return hexId;
    }
    
    /**
     * Gets the terrain type associated with a hex number.
     * @param hexNumber The hex number to look up
     * @return The terrain type, or null if not found
     */
    public static HexTerrain getTerrainByHexNumber(int hexNumber) {
        for (HexTerrain terrain : values()) {
            if (terrain.hexId == hexNumber) {
                return terrain;
            }
        }
        return null;
    }
    
    /**
     * Determines if this terrain produces resources.
     * @return true if the terrain produces resources, false otherwise
     */
    public boolean producesResource() {
        return this != DESERT;
    }
    
    /**
     * Gets the resource type produced by this terrain.
     * @return The resource type as a string
     */
    public String getResource() {
        switch (this) {
            case FOREST: return "WOOD";
            case HILLS: return "BRICK";
            case MOUNTAINS: return "ORE";
            case FIELDS: return "GRAIN";
            case PASTURE: return "WOOL";
            case DESERT: return "NONE";
            default: return "UNKNOWN";
        }
    }
}
