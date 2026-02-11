/**
 * Represents a settlement building that can be placed on an intersection.
 * Settlements are worth 1 victory point.
 */
public class Settlement extends Building {

    /**
     * Constructs a Settlement.
     * 
     * @param owner The player who owns this settlement
     * @param location The intersection where this settlement is located
     */
    public Settlement(Player owner, Intersection location) {
        super(owner, location);
    }

    /**
     * Gets the location of this settlement.
     * 
     * @return The Intersection where this settlement is located
     */
    public Intersection getLocation() {
        return buildingLocation;
    }

    /**
     * Gets the victory points for this settlement.
     * 
     * @return 1 victory point
     */
    @Override
    public int getVictoryPoints() {
        return 1;
    }

    /**
     * Gets the type of this building.
     * 
     * @return "Settlement"
     */
    @Override
    public String getBuildingType() {
        return "Settlement";
    }

    @Override
    public String toString() {
        return "Settlement{" +
                "owner=" + buildingOwner.getId() +
                ", location=" + buildingLocation.getIntersectionLocation() +
                '}';
    }
}
