package com.boardgame;

/**
 * Represents an intersection on the game board where settlements and cities can be built.
 * An intersection is the point where three hex tiles meet.
 */
public class Intersection {
    private Building occupiedBy;
    private int intersectionLocation;
    
    /**
     * Constructs an Intersection at the specified location.
     * @param intersectionLocation The unique identifier for this intersection
     */
    public Intersection(int intersectionLocation) {
        this.intersectionLocation = intersectionLocation;
        this.occupiedBy = null;
    }
    
    /**
     * Gets the intersection's unique location identifier.
     * @return The intersection location ID
     */
    public int getIntersectionLocation() {
        return intersectionLocation;
    }
    
    /**
     * Gets the building occupying this intersection.
     * @return The building at this intersection, or null if unoccupied
     */
    public Building getIntersectionBuilding() {
        return occupiedBy;
    }
    
    /**
     * Sets the building at this intersection.
     * @param building The building to place
     */
    public void setOccupiedBy(Building building) {
        this.occupiedBy = building;
    }
    
    /**
     * Checks if this intersection is occupied by a building.
     * @return true if occupied, false otherwise
     */
    public boolean isOccupied() {
        return occupiedBy != null;
    }
    
    @Override
    public String toString() {
        return "Intersection[" + intersectionLocation + ", " + 
               (isOccupied() ? "occupied by " + occupiedBy : "empty") + "]";
    }
}
