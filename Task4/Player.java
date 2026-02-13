import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

public class Player {
    private int playerID;
    private int victoryPoints;
    private List<City> playerCities;
    private List<Settlement> playerSettlements;
    private List<Road> playerRoads;
    private Map<ResourceType, Integer> playerResources;
    
    public Player(int playerNum, int playerVP, List<City> cities, List<Settlement> settlements, List<Road> roads, Map<ResourceType, Integer> resources) {
        playerID = playerNum;
        victoryPoints = playerVP;
        playerCities = cities;
        playerSettlements = settlements;
        playerResources = resources;
        playerRoads = roads;
    }

    public void addResource(ResourceType resource, int quantity) {
        playerResources.put(resource, playerResources.getOrDefault(resource, 0) + quantity);
    }

    public boolean removeResource(ResourceType resource, int quantity) {
        if (playerResources.containsKey(resource) && quantity > playerResources.get(resource)) {
            System.out.println("Unsuccesful not enough resources");
            return false;
        }

        if (playerResources.containsKey(resource)) {
            playerResources.put(resource, playerResources.get(resource) - quantity);
        }
        return true;
    }

    public boolean checkResource(ResourceType resource, int quantity) {
        if (playerResources.containsKey(resource) && playerResources.get(resource) >= quantity) {
            return true;
        }
        else {
            return false;
        }
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public void buildSettlement(Board board, Intersection buildIntersection) {
        if (!checkResource(ResourceType.Wood, 1) || !checkResource(ResourceType.Brick, 1) ||
            !checkResource(ResourceType.Sheep, 1) || !checkResource(ResourceType.Wheat, 1)) {
            return;
        }
        if (board.placeSettlement(buildIntersection, this)) {
            removeResource(ResourceType.Wood, 1);
            removeResource(ResourceType.Brick, 1);
            removeResource(ResourceType.Sheep, 1);
            removeResource(ResourceType.Wheat, 1);
            victoryPoints += 1;
        }
    }

    public void buildCity(Board board, Intersection buildIntersection) {
        if (!checkResource(ResourceType.Wheat, 2) || !checkResource(ResourceType.Ore, 3)) {
            return;
        }
        if (board.placeCity(buildIntersection, this)) {
            removeResource(ResourceType.Wheat, 2);
            removeResource(ResourceType.Ore, 3);
            victoryPoints += 1;
        }
    }

    public void buildRoad(Board board, Edge buildEdge) {
        if (!checkResource(ResourceType.Brick, 1) || !checkResource(ResourceType.Wood, 1)) {
            return;
        }
        if (board.placeRoad(buildEdge, this)) {
            removeResource(ResourceType.Brick, 1);
            removeResource(ResourceType.Wood, 1);
        }
    }

    public String takeRandomAction(Board board) {
        Random random = new Random();
        int action = random.nextInt(3);

        if (action == 0) {
            List<Integer> validSpots = new ArrayList<>();
            for (int i = 0; i <= 53; i++) {
                Intersection intersection = board.getIntersection(i);
                if (intersection != null) {
                    validSpots.add(i);
                }
            }
            if (!validSpots.isEmpty()) {
                int randomIndex = random.nextInt(validSpots.size());
                Intersection target = board.getIntersection(validSpots.get(randomIndex));
                buildSettlement(board, target);
                return "attempted settlement at " + validSpots.get(randomIndex);
            }

        } else if (action == 1) {
            if (!playerSettlements.isEmpty()) {
                int randomIndex = random.nextInt(playerSettlements.size());
                Intersection target = playerSettlements.get(randomIndex).getBuildlocation();
                buildCity(board, target);
                return "attempted city at " + target.getIntersectionLocation();
            }

        } else {
            for (int i = 0; i <= 53; i++) {
                for (int j = i + 1; j <= 53; j++) {
                    if (board.isValidEdge(i, j)) {
                        Edge edge = new Edge(i, j);
                        buildRoad(board, edge);
                        return "attempted road at edge " + i + "-" + j;
                    }
                }
            }
        }

        return "no action taken";
    }

    public List<Settlement> getPlayerSettlements() {
        return playerSettlements;
    }

    public List<Road> getPlayerRoads() {
        return playerRoads;
    }

    public int getPlayerID() {
        return playerID;
    }
}