/**
 * Represents the terrain type of a hexagonal tile on the game board.
 * Each terrain type has an ID, terrain enum value, and hex number for resource generation.
 */
public class HexTerrain {
    private int hexId;
    private TerrainType hexTerrain;
    private int hexNumber;

    /**
     * Enum representing different terrain types available in the game.
     */
    public enum TerrainType {
        FOREST,
        HILLS,
        MOUNTAINS,
        FIELDS,
        PASTURE,
        DESERT
    }

    /**
     * Constructs a HexTerrain tile.
     * 
     * @param hexId Unique identifier for this hex tile
     * @param hexTerrain The type of terrain
     * @param hexNumber The number used for resource generation (2-12)
     */
    public HexTerrain(int hexId, TerrainType hexTerrain, int hexNumber) {
        this.hexId = hexId;
        this.hexTerrain = hexTerrain;
        this.hexNumber = hexNumber;
    }

    /**
     * Gets the hex ID.
     * 
     * @return The unique hex identifier
     */
    public int getHexId() {
        return hexId;
    }

    /**
     * Gets the terrain type.
     * 
     * @return The terrain type enum
     */
    public TerrainType getTerrain() {
        return hexTerrain;
    }

    /**
     * Gets the hex number for resource generation.
     * 
     * @return The hex number (2-12)
     */
    public int getHexNumber() {
        return hexNumber;
    }

    /**
     * Gets the resource type associated with this terrain.
     * 
     * @return ResourceType enum value
     */
    public ResourceType getResource() {
        switch (hexTerrain) {
            case FOREST:
                return ResourceType.WOOD;
            case HILLS:
                return ResourceType.BRICK;
            case MOUNTAINS:
                return ResourceType.ORE;
            case FIELDS:
                return ResourceType.GRAIN;
            case PASTURE:
                return ResourceType.WOOL;
            case DESERT:
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return "HexTerrain{" +
                "hexId=" + hexId +
                ", terrain=" + hexTerrain +
                ", number=" + hexNumber +
                '}';
    }
}
