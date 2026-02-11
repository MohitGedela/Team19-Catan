package com.boardgame;

/**
 * Represents a Road on the game board.
 * Roads are built on edges and connect settlements and cities.
 */
public class Road {
    private Player owner;
    private Edge location;
    
    /**
     * Constructs a Road owned by a player on a specific edge.
     * @param owner The player who owns this road
     * @param location The edge where this road is placed
     */
    public Road(Player owner, Edge location) {
        this.owner = owner;
        this.location = location;
    }
    
    /**
     * Gets the owner of this road.
     * @return The player who owns this road
     */
    public Player getOwner() {
        return owner;
    }
    
    /**
     * Gets the location of this road.
     * @return The edge where this road is located
     */
    public Edge getLocation() {
        return location;
    }
    
    @Override
    public String toString() {
        return "Road[owner=" + owner.getId() + ", location=" + location.getLocation() + "]";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Road road = (Road) obj;
        return owner.equals(road.owner) && location.equals(road.location);
    }
    
    @Override
    public int hashCode() {
        return owner.hashCode() * 31 + location.hashCode();
    }
}
