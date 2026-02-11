/**
 * Represents an edge on the board where roads can be built.
 * Each edge connects two intersections.
 */
public class Edge {
    private Intersection start;
    private Intersection end;
    private Road road;

    /**
     * Constructs an Edge connecting two intersections.
     * 
     * @param start The starting intersection
     * @param end The ending intersection
     */
    public Edge(Intersection start, Intersection end) {
        this.start = start;
        this.end = end;
        this.road = null;
    }

    /**
     * Gets the starting intersection of this edge.
     * 
     * @return The start Intersection
     */
    public Intersection getStart() {
        return start;
    }

    /**
     * Gets the ending intersection of this edge.
     * 
     * @return The end Intersection
     */
    public Intersection getEnd() {
        return end;
    }

    /**
     * Gets the location (edge identifier) of this edge.
     * 
     * @return Edge location as a combination of start and end intersection locations
     */
    public Edge getLocation() {
        return this;
    }

    /**
     * Gets the owner of the road on this edge.
     * 
     * @return The Player who owns the road, or null if no road exists
     */
    public Player getOwner() {
        return road != null ? road.getOwner() : null;
    }

    /**
     * Checks if this edge has a road built on it.
     * 
     * @return true if a road exists, false otherwise
     */
    public boolean hasRoad() {
        return road != null;
    }

    /**
     * Sets the road on this edge.
     * 
     * @param road The Road to place on this edge
     */
    public void setRoad(Road road) {
        this.road = road;
    }

    /**
     * Gets the road on this edge.
     * 
     * @return The Road object, or null if no road exists
     */
    public Road getRoad() {
        return road;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "start=" + start.getIntersectionLocation() +
                ", end=" + end.getIntersectionLocation() +
                ", hasRoad=" + hasRoad() +
                '}';
    }
}
