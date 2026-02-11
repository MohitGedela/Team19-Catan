/**
 * Represents an intersection on the board where settlements and cities can be built.
 * Each intersection is adjacent to up to three hex tiles.
 */
public class Intersection {
    private Building occupiedBy;
    private int intersectionLocation;

    /**
     * Constructs an Intersection at the specified location.
     * 
     * @param intersectionLocation The unique location identifier for this intersection
     */
    public Intersection(int intersectionLocation) {
        this.intersectionLocation = intersectionLocation;
        this.occupiedBy = null;
    }

    /**
     * Gets the intersection location identifier.
     * 
     * @return The location ID
     */
    public int getIntersectionLocation() {
        return intersectionLocation;
    }

    /**
     * Gets the building occupying this intersection.
     * 
     * @return The Building object, or null if unoccupied
     */
    public Building getIntersectionBuilding() {
        return occupiedBy;
    }

    /**
     * Checks if this intersection is currently occupied by a building.
     * 
     * @return true if occupied, false otherwise
     */
    public boolean isOccupied() {
        return occupiedBy != null;
    }

    /**
     * Sets the building that occupies this intersection.
     * 
     * @param building The building to place at this intersection
     */
    public void setOccupiedBy(Building building) {
        this.occupiedBy = building;
    }

    @Override
    public String toString() {
        return "Intersection{" +
                "location=" + intersectionLocation +
                ", occupiedBy=" + (occupiedBy != null ? occupiedBy.getOwner().getId() : "none") +
                '}';
    }
}
