package com.boardgame;

/**
 * Represents an edge on the game board connecting two intersections.
 * Roads can be built on edges.
 */
public class Edge {
    private Intersection start;
    private Intersection end;
    private Road road;
    
    /**
     * Constructs an Edge between two intersections.
     * @param start The starting intersection
     * @param end The ending intersection
     */
    public Edge(Intersection start, Intersection end) {
        this.start = start;
        this.end = end;
        this.road = null;
    }
    
    /**
     * Gets the location/identifier for this edge.
     * @return A composite edge location based on start and end intersections
     */
    public String getLocation() {
        return start.getIntersectionLocation() + "-" + end.getIntersectionLocation();
    }
    
    /**
     * Gets the owner of the road on this edge.
     * @return The player who owns the road, or null if no road exists
     */
    public Player getOwner() {
        return road != null ? road.getOwner() : null;
    }
    
    /**
     * Checks if this edge has a road built on it.
     * @param road The road to check
     * @return true if the specified road exists on this edge
     */
    public boolean hasRoad(Road road) {
        return this.road != null && this.road.equals(road);
    }
    
    /**
     * Sets the road on this edge.
     * @param road The road to place on this edge
     */
    public void setRoad(Road road) {
        this.road = road;
    }
    
    /**
     * Gets the road on this edge.
     * @return The road, or null if no road exists
     */
    public Road getRoad() {
        return road;
    }
    
    /**
     * Gets the starting intersection of this edge.
     * @return The start intersection
     */
    public Intersection getStart() {
        return start;
    }
    
    /**
     * Gets the ending intersection of this edge.
     * @return The end intersection
     */
    public Intersection getEnd() {
        return end;
    }
    
    @Override
    public String toString() {
        return "Edge[" + getLocation() + ", " + 
               (road != null ? "has road owned by " + road.getOwner().getId() : "no road") + "]";
    }
}
