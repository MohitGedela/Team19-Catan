package com.boardgame;

import java.util.*;

/**
 * Represents a player in the game.
 * Players have resources, buildings, roads, and can perform various game actions.
 */
public class Player {
    private String id;
    private int victoryPoints;
    private Map<String, Integer> resources; // Resource type -> quantity
    private List<Settlement> playerSettlements;
    private List<City> playerCities;
    private List<Road> playerRoads;
    
    /**
     * Constructs a Player with the specified ID.
     * @param id The unique identifier for this player
     */
    public Player(String id) {
        this.id = id;
        this.victoryPoints = 0;
        this.resources = new HashMap<>();
        this.playerSettlements = new ArrayList<>();
        this.playerCities = new ArrayList<>();
        this.playerRoads = new ArrayList<>();
        initializeResources();
    }
    
    /**
     * Initializes all resource types to zero.
     */
    private void initializeResources() {
        resources.put("WOOD", 0);
        resources.put("BRICK", 0);
        resources.put("ORE", 0);
        resources.put("GRAIN", 0);
        resources.put("WOOL", 0);
    }
    
    /**
     * Gets the player's ID.
     * @return The player ID
     */
    public String getId() {
        return id;
    }
    
    /**
     * Rolls the dice to determine resource production.
     * @return The dice roll result (2-12)
     */
    public int rollDice() {
        Random random = new Random();
        return (random.nextInt(6) + 1) + (random.nextInt(6) + 1);
    }
    
    /**
     * Executes the player's turn.
     * This is a placeholder for turn logic.
     */
    public void play() {
        // Turn logic to be implemented based on game rules
        System.out.println("Player " + id + " is taking their turn.");
    }
    
    /**
     * Adds a resource to the player's inventory.
     * @param resourceType The type of resource to add
     * @param amount The amount to add
     */
    public void addResource(String resourceType, int amount) {
        if (resources.containsKey(resourceType)) {
            resources.put(resourceType, resources.get(resourceType) + amount);
        }
    }
    
    /**
     * Removes a resource from the player's inventory.
     * @param resourceType The type of resource to remove
     * @param amount The amount to remove
     */
    public void removeResource(String resourceType, int amount) {
        if (resources.containsKey(resourceType)) {
            int current = resources.get(resourceType);
            resources.put(resourceType, Math.max(0, current - amount));
        }
    }
    
    /**
     * Checks if the player has enough of a specific resource.
     * @param resourceType The resource type to check
     * @param amount The required amount
     * @return true if the player has enough, false otherwise
     */
    public boolean checkResource(String resourceType, int amount) {
        return resources.getOrDefault(resourceType, 0) >= amount;
    }
    
    /**
     * Gets the quantity of a specific resource.
     * @param resourceType The resource type
     * @return The quantity of that resource
     */
    public int getResourceAmount(String resourceType) {
        return resources.getOrDefault(resourceType, 0);
    }
    
    /**
     * Builds a settlement at the specified intersection.
     * @param intersection The intersection where the settlement will be built
     */
    public void buildSettlement(Intersection intersection) {
        if (checkResource("WOOD", 1) && checkResource("BRICK", 1) && 
            checkResource("GRAIN", 1) && checkResource("WOOL", 1)) {
            
            Settlement settlement = new Settlement(this, intersection);
            playerSettlements.add(settlement);
            intersection.setOccupiedBy(settlement);
            
            removeResource("WOOD", 1);
            removeResource("BRICK", 1);
            removeResource("GRAIN", 1);
            removeResource("WOOL", 1);
            
            victoryPoints += 1;
            System.out.println("Player " + id + " built a settlement at " + 
                             intersection.getIntersectionLocation());
        } else {
            System.out.println("Not enough resources to build a settlement!");
        }
    }
    
    /**
     * Builds a city by upgrading a settlement.
     * @param intersection The intersection with the settlement to upgrade
     */
    public void buildCity(Intersection intersection) {
        if (checkResource("ORE", 3) && checkResource("GRAIN", 2)) {
            // Check if there's a settlement at this location
            Building building = intersection.getIntersectionBuilding();
            if (building instanceof Settlement && building.getOwner().equals(this)) {
                playerSettlements.remove(building);
                
                City city = new City(this, intersection);
                playerCities.add(city);
                intersection.setOccupiedBy(city);
                
                removeResource("ORE", 3);
                removeResource("GRAIN", 2);
                
                victoryPoints += 1; // Net gain of 1 VP (city is 2, settlement was 1)
                System.out.println("Player " + id + " upgraded to a city at " + 
                                 intersection.getIntersectionLocation());
            } else {
                System.out.println("No settlement to upgrade at this location!");
            }
        } else {
            System.out.println("Not enough resources to build a city!");
        }
    }
    
    /**
     * Builds a road on the specified edge.
     * @param edge The edge where the road will be built
     */
    public void buildRoad(Edge edge) {
        if (checkResource("WOOD", 1) && checkResource("BRICK", 1)) {
            Road road = new Road(this, edge);
            playerRoads.add(road);
            edge.setRoad(road);
            
            removeResource("WOOD", 1);
            removeResource("BRICK", 1);
            
            System.out.println("Player " + id + " built a road at " + edge.getLocation());
        } else {
            System.out.println("Not enough resources to build a road!");
        }
    }
    
    /**
     * Gets the player's current victory points.
     * @return The victory points total
     */
    public int getVictoryPoints() {
        return victoryPoints;
    }
    
    /**
     * Gets all settlements owned by this player.
     * @return List of settlements
     */
    public List<Settlement> getSettlements() {
        return new ArrayList<>(playerSettlements);
    }
    
    /**
     * Gets all cities owned by this player.
     * @return List of cities
     */
    public List<City> getCities() {
        return new ArrayList<>(playerCities);
    }
    
    /**
     * Gets all roads owned by this player.
     * @return List of roads
     */
    public List<Road> getRoads() {
        return new ArrayList<>(playerRoads);
    }
    
    /**
     * Gets a copy of the player's resources.
     * @return Map of resource types to quantities
     */
    public Map<String, Integer> getResources() {
        return new HashMap<>(resources);
    }
    
    @Override
    public String toString() {
        return "Player[id=" + id + ", VP=" + victoryPoints + ", resources=" + resources + "]";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Player player = (Player) obj;
        return id.equals(player.id);
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
