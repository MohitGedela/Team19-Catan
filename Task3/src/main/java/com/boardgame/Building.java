package com.boardgame;

/**
 * Abstract base class for buildings (Settlements and Cities).
 * Buildings are placed on intersections and owned by players.
 */
public abstract class Building {
    private Player buildingOwner;
    private Intersection buildingLocation;
    
    /**
     * Constructs a Building owned by a player at a specific location.
     * @param owner The player who owns this building
     * @param location The intersection where this building is placed
     */
    public Building(Player owner, Intersection location) {
        this.buildingOwner = owner;
        this.buildingLocation = location;
    }
    
    /**
     * Gets the owner of this building.
     * @return The player who owns this building
     */
    public Player getOwner() {
        return buildingOwner;
    }
    
    /**
     * Gets the location of this building.
     * @return The intersection where this building is located
     */
    public Intersection getLocation() {
        return buildingLocation;
    }
    
    /**
     * Gets the victory points this building provides.
     * Abstract method to be implemented by subclasses.
     * @return The number of victory points
     */
    public abstract int getVictoryPoints();
    
    /**
     * Gets the type of building as a string.
     * @return The building type name
     */
    public abstract String getBuildingType();
}
