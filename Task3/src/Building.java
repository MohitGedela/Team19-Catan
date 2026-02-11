/**
 * Abstract base class for buildings (settlements and cities) that can be placed on the board.
 */
public abstract class Building {
    protected Player buildingOwner;
    protected Intersection buildingLocation;

    /**
     * Constructs a Building.
     * 
     * @param owner The player who owns this building
     * @param location The intersection where this building is located
     */
    public Building(Player owner, Intersection location) {
        this.buildingOwner = owner;
        this.buildingLocation = location;
    }

    /**
     * Gets the owner of this building.
     * 
     * @return The Player who owns this building
     */
    public Player getOwner() {
        return buildingOwner;
    }

    /**
     * Gets the location of this building.
     * 
     * @return The Intersection where this building is located
     */
    public Intersection getLocation() {
        return buildingLocation;
    }

    /**
     * Gets the victory points this building is worth.
     * 
     * @return The number of victory points (1 for settlement, 2 for city)
     */
    public abstract int getVictoryPoints();

    /**
     * Gets the type name of this building.
     * 
     * @return String representing the building type
     */
    public abstract String getBuildingType();
}
