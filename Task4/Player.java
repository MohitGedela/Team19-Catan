import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Player {
    private int playerID;
    private int victoryPoints;
    private List<City> playerCities;
    private List<Settlement> playerSettlements;
    private Map<ResourceType, Integer> playerResources;
    
    public Player(int playerNum, int playerVP, List<City> cities, List<Settlement> settlements, Map<ResourceType, Integer> resources) {
        playerID = playerNum;
        victoryPoints = playerVP;
        playerCities = cities;
        playerSettlements = settlements;
        playerResources = resources;
    }

    public void addResource(ResourceType resource, int quantity) {
        if (playerResources.containsKey(resource)) {
            playerResources.put(resource, playerResources.get(resource) + quantity);
        }
        System.out.println("Succesfully added");
    }

    public boolean removeResource(ResourceType resource, int quantity) {
        if (quantity > playerResources.get(resource) && playerResources.containsKey(resource)) {
            System.out.println("Unsuccesful not enough resources");
            return false;
        }

        if (playerResources.containsKey(resource)) {
            playerResources.put(resource, playerResources.get(resource) - quantity);
        }
        return true;
    }

    public boolean checkResource(ResourceType resource, int quantity) {
        if (playerResources.containsKey(resource) && playerResources.get(resource) == quantity) {
            return true;
        }
        else {
            return false;
        }
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }
}
