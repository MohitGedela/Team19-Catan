import java.util.*;

/**
 * Represents a player in the game.
 * Each player has resources, buildings, roads, cities, and settlements.
 */
public class Player {
    private String id;
    private int victoryPoints;
    private Map<ResourceType, Integer> resources;
    private List<Settlement> playerSettlements;
    private List<City> playerCities;
    private List<Road> playerRoads;

    /**
     * Constructs a Player with the specified ID.
     * 
     * @param id Unique identifier for this player
     */
    public Player(String id) {
        this.id = id;
        this.victoryPoints = 0;
        this.resources = new HashMap<>();
        this.playerSettlements = new ArrayList<>();
        this.playerCities = new ArrayList<>();
        this.playerRoads = new ArrayList<>();
        
        // Initialize resources to 0
        for (ResourceType type : ResourceType.values()) {
            resources.put(type, 0);
        }
    }

    /**
     * Gets the player's ID.
     * 
     * @return The player ID
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the player's current victory points.
     * 
     * @return The number of victory points
     */
    public int getVictoryPoints() {
        return victoryPoints;
    }

    /**
     * Rolls the dice and returns the result.
     * 
     * @return The sum of two dice (2-12)
     */
    public int rollDice() {
        Random random = new Random();
        int die1 = random.nextInt(6) + 1;
        int die2 = random.nextInt(6) + 1;
        return die1 + die2;
    }

    /**
     * Plays a turn for this player (placeholder for game logic).
     */
    public void play() {
        System.out.println("Player " + id + " is playing their turn.");
    }

    /**
     * Adds a resource to the player's inventory.
     * 
     * @param resourceType The type of resource to add
     * @param amount The amount to add
     */
    public void addResource(ResourceType resourceType, int amount) {
        if (resourceType != null) {
            resources.put(resourceType, resources.get(resourceType) + amount);
        }
    }

    /**
     * Removes a resource from the player's inventory.
     * 
     * @param resourceType The type of resource to remove
     * @param amount The amount to remove
     */
    public void removeResource(ResourceType resourceType, int amount) {
        if (resourceType != null) {
            int current = resources.get(resourceType);
            resources.put(resourceType, Math.max(0, current - amount));
        }
    }

    /**
     * Checks if the player has enough of a specific resource.
     * 
     * @param resourceType The type of resource to check
     * @param amount The required amount
     * @return true if the player has enough, false otherwise
     */
    public boolean checkResource(ResourceType resourceType, int amount) {
        return resources.getOrDefault(resourceType, 0) >= amount;
    }

    /**
     * Builds a settlement at the specified intersection.
     * 
     * @param intersection The intersection where the settlement will be built
     */
    public void buildSettlement(Intersection intersection) {
        // Check if player has required resources (1 wood, 1 brick, 1 wool, 1 grain)
        if (checkResource(ResourceType.WOOD, 1) &&
            checkResource(ResourceType.BRICK, 1) &&
            checkResource(ResourceType.WOOL, 1) &&
            checkResource(ResourceType.GRAIN, 1)) {
            
            Settlement settlement = new Settlement(this, intersection);
            playerSettlements.add(settlement);
            intersection.setOccupiedBy(settlement);
            
            // Deduct resources
            removeResource(ResourceType.WOOD, 1);
            removeResource(ResourceType.BRICK, 1);
            removeResource(ResourceType.WOOL, 1);
            removeResource(ResourceType.GRAIN, 1);
            
            // Add victory point
            victoryPoints += settlement.getVictoryPoints();
            
            System.out.println("Player " + id + " built a settlement at location " + 
                             intersection.getIntersectionLocation());
        } else {
            System.out.println("Player " + id + " does not have enough resources to build a settlement.");
        }
    }

    /**
     * Builds a city at the specified intersection (upgrades a settlement).
     * 
     * @param intersection The intersection where the city will be built
     */
    public void buildCity(Intersection intersection) {
        // Check if player has required resources (3 ore, 2 grain)
        if (checkResource(ResourceType.ORE, 3) &&
            checkResource(ResourceType.GRAIN, 2)) {
            
            // Check if there's a settlement at this location owned by this player
            Building existing = intersection.getIntersectionBuilding();
            if (existing instanceof Settlement && existing.getOwner().equals(this)) {
                City city = new City(this, intersection);
                
                // Remove settlement and add city
                playerSettlements.remove(existing);
                playerCities.add(city);
                intersection.setOccupiedBy(city);
                
                // Deduct resources
                removeResource(ResourceType.ORE, 3);
                removeResource(ResourceType.GRAIN, 2);
                
                // Update victory points (city is worth 2, settlement was worth 1)
                victoryPoints += 1;
                
                System.out.println("Player " + id + " upgraded to a city at location " + 
                                 intersection.getIntersectionLocation());
            } else {
                System.out.println("Player " + id + " does not have a settlement at this location.");
            }
        } else {
            System.out.println("Player " + id + " does not have enough resources to build a city.");
        }
    }

    /**
     * Builds a road on the specified edge.
     * 
     * @param edge The edge where the road will be built
     */
    public void buildRoad(Edge edge) {
        // Check if player has required resources (1 wood, 1 brick)
        if (checkResource(ResourceType.WOOD, 1) &&
            checkResource(ResourceType.BRICK, 1)) {
            
            Road road = new Road(this, edge);
            playerRoads.add(road);
            edge.setRoad(road);
            
            // Deduct resources
            removeResource(ResourceType.WOOD, 1);
            removeResource(ResourceType.BRICK, 1);
            
            System.out.println("Player " + id + " built a road.");
        } else {
            System.out.println("Player " + id + " does not have enough resources to build a road.");
        }
    }

    /**
     * Gets all settlements owned by this player.
     * 
     * @return List of settlements
     */
    public List<Settlement> getPlayerSettlements() {
        return new ArrayList<>(playerSettlements);
    }

    /**
     * Gets all cities owned by this player.
     * 
     * @return List of cities
     */
    public List<City> getPlayerCities() {
        return new ArrayList<>(playerCities);
    }

    /**
     * Gets all roads owned by this player.
     * 
     * @return List of roads
     */
    public List<Road> getPlayerRoads() {
        return new ArrayList<>(playerRoads);
    }

    /**
     * Gets the player's resources.
     * 
     * @return Map of resource types to amounts
     */
    public Map<ResourceType, Integer> getResources() {
        return new HashMap<>(resources);
    }

    /**
     * Gets the amount of a specific resource.
     * 
     * @param resourceType The type of resource
     * @return The amount of that resource
     */
    public int getResourceAmount(ResourceType resourceType) {
        return resources.getOrDefault(resourceType, 0);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", victoryPoints=" + victoryPoints +
                ", settlements=" + playerSettlements.size() +
                ", cities=" + playerCities.size() +
                ", roads=" + playerRoads.size() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
