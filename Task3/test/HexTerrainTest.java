import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the HexTerrain class.
 */
public class HexTerrainTest {

    @Test
    public void testHexTerrainCreation() {
        HexTerrain hex = new HexTerrain(0, HexTerrain.TerrainType.FOREST, 6);
        
        assertEquals(0, hex.getHexId());
        assertEquals(HexTerrain.TerrainType.FOREST, hex.getTerrain());
        assertEquals(6, hex.getHexNumber());
    }

    @Test
    public void testGetResourceForest() {
        HexTerrain hex = new HexTerrain(0, HexTerrain.TerrainType.FOREST, 6);
        assertEquals(ResourceType.WOOD, hex.getResource());
    }

    @Test
    public void testGetResourceHills() {
        HexTerrain hex = new HexTerrain(1, HexTerrain.TerrainType.HILLS, 5);
        assertEquals(ResourceType.BRICK, hex.getResource());
    }

    @Test
    public void testGetResourceMountains() {
        HexTerrain hex = new HexTerrain(2, HexTerrain.TerrainType.MOUNTAINS, 8);
        assertEquals(ResourceType.ORE, hex.getResource());
    }

    @Test
    public void testGetResourceFields() {
        HexTerrain hex = new HexTerrain(3, HexTerrain.TerrainType.FIELDS, 4);
        assertEquals(ResourceType.GRAIN, hex.getResource());
    }

    @Test
    public void testGetResourcePasture() {
        HexTerrain hex = new HexTerrain(4, HexTerrain.TerrainType.PASTURE, 10);
        assertEquals(ResourceType.WOOL, hex.getResource());
    }

    @Test
    public void testGetResourceDesert() {
        HexTerrain hex = new HexTerrain(5, HexTerrain.TerrainType.DESERT, 0);
        assertNull(hex.getResource());
    }

    @Test
    public void testAllTerrainTypes() {
        HexTerrain.TerrainType[] types = {
            HexTerrain.TerrainType.FOREST,
            HexTerrain.TerrainType.HILLS,
            HexTerrain.TerrainType.MOUNTAINS,
            HexTerrain.TerrainType.FIELDS,
            HexTerrain.TerrainType.PASTURE,
            HexTerrain.TerrainType.DESERT
        };
        
        for (int i = 0; i < types.length; i++) {
            HexTerrain hex = new HexTerrain(i, types[i], i + 2);
            assertNotNull(hex);
            assertEquals(types[i], hex.getTerrain());
        }
    }
}
