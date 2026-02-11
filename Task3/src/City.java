/**
 * Represents a city building that can be placed on an intersection (upgraded from a settlement).
 * Cities are worth 2 victory points.
 */
public class City extends Building {

    /**
     * Constructs a City.
     * 
     * @param owner The player who owns this city
     * @param location The intersection where this city is located
     */
    public City(Player owner, Intersection location) {
        super(owner, location);
    }

    /**
     * Gets the location of this city.
     * 
     * @return The Intersection where this city is located
     */
    public Intersection getLocation() {
        return buildingLocation;
    }

    /**
     * Gets the victory points for this city.
     * 
     * @return 2 victory points
     */
    @Override
    public int getVictoryPoints() {
        return 2;
    }

    /**
     * Gets the type of this building.
     * 
     * @return "City"
     */
    @Override
    public String getBuildingType() {
        return "City";
    }

    @Override
    public String toString() {
        return "City{" +
                "owner=" + buildingOwner.getId() +
                ", location=" + buildingLocation.getIntersectionLocation() +
                '}';
    }
}
