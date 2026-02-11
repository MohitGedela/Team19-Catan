package com.boardgame;

/**
 * Represents a City building.
 * Cities provide 2 victory points and are upgraded from settlements.
 */
public class City extends Building {
    
    /**
     * Constructs a City.
     * @param owner The player who owns this city
     * @param location The intersection where this city is placed
     */
    public City(Player owner, Intersection location) {
        super(owner, location);
    }
    
    /**
     * Gets the victory points for a city.
     * @return 2 victory points
     */
    @Override
    public int getVictoryPoints() {
        return 2;
    }
    
    /**
     * Gets the location of this city.
     * @return The intersection location
     */
    public Intersection getLocation() {
        return super.getLocation();
    }
    
    /**
     * Gets the victory points for this city.
     * @return 2 victory points
     */
    public int getVictoryPoints(City city) {
        return getVictoryPoints();
    }
    
    @Override
    public String getBuildingType() {
        return "City";
    }
    
    @Override
    public String toString() {
        return "City[owner=" + getOwner().getId() + ", location=" + 
               getLocation().getIntersectionLocation() + "]";
    }
}
