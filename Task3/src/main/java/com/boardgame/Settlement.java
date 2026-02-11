package com.boardgame;

/**
 * Represents a Settlement building.
 * Settlements provide 1 victory point and can be upgraded to cities.
 */
public class Settlement extends Building {
    
    /**
     * Constructs a Settlement.
     * @param owner The player who owns this settlement
     * @param location The intersection where this settlement is placed
     */
    public Settlement(Player owner, Intersection location) {
        super(owner, location);
    }
    
    /**
     * Gets the victory points for a settlement.
     * @return 1 victory point
     */
    @Override
    public int getVictoryPoints() {
        return 1;
    }
    
    /**
     * Gets the location of this settlement.
     * @return The intersection location
     */
    public Intersection getLocation() {
        return super.getLocation();
    }
    
    /**
     * Gets the victory points for this settlement.
     * @return 1 victory point
     */
    public int getVictoryPoints(Settlement settlement) {
        return getVictoryPoints();
    }
    
    @Override
    public String getBuildingType() {
        return "Settlement";
    }
    
    @Override
    public String toString() {
        return "Settlement[owner=" + getOwner().getId() + ", location=" + 
               getLocation().getIntersectionLocation() + "]";
    }
}
