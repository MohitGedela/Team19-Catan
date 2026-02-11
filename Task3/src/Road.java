/**
 * Represents a road that can be built on an edge between two intersections.
 */
public class Road {
    private Player owner;
    private Edge location;

    /**
     * Constructs a Road.
     * 
     * @param owner The player who owns this road
     * @param location The edge where this road is located
     */
    public Road(Player owner, Edge location) {
        this.owner = owner;
        this.location = location;
    }

    /**
     * Gets the owner of this road.
     * 
     * @return The Player who owns this road
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Gets the location of this road.
     * 
     * @return The Edge where this road is located
     */
    public Edge getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Road{" +
                "owner=" + owner.getId() +
                ", location=" + location +
                '}';
    }
}
