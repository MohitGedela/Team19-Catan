import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Building classes (Settlement and City).
 */
public class BuildingTest {
    private Player player;
    private Intersection intersection;

    @BeforeEach
    public void setUp() {
        player = new Player("TestPlayer");
        intersection = new Intersection(0);
    }

    @Test
    public void testSettlementCreation() {
        Settlement settlement = new Settlement(player, intersection);
        
        assertEquals(player, settlement.getOwner());
        assertEquals(intersection, settlement.getLocation());
        assertEquals(1, settlement.getVictoryPoints());
        assertEquals("Settlement", settlement.getBuildingType());
    }

    @Test
    public void testCityCreation() {
        City city = new City(player, intersection);
        
        assertEquals(player, city.getOwner());
        assertEquals(intersection, city.getLocation());
        assertEquals(2, city.getVictoryPoints());
        assertEquals("City", city.getBuildingType());
    }

    @Test
    public void testSettlementVsCity() {
        Settlement settlement = new Settlement(player, intersection);
        City city = new City(player, new Intersection(1));
        
        assertTrue(city.getVictoryPoints() > settlement.getVictoryPoints());
    }
}
