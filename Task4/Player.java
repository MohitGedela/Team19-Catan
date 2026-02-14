import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Random;

// One player: their cards (resources), buildings (settlements/cities/roads), and VP. Can build if they have the right cards.
public class Player {
    private int playerID;
    private int victoryPoints;
    private List<City> playerCities;
    private List<Settlement> playerSettlements;
    private List<Road> playerRoads;
    private Map<ResourceType, Integer> playerResources;

    public Player(int playerNum, int playerVP, List<City> cities, List<Settlement> settlements, List<Road> roads,
            Map<ResourceType, Integer> resources) {
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

    // Take cards from hand. Returns false if not enough.
    public boolean removeResource(ResourceType resource, int quantity) {
        int have = playerResources.getOrDefault(resource, 0);
        if (have < quantity) {
            System.out.println("Unsuccesful not enough resources");
            return false;
        }
        playerResources.put(resource, have - quantity);
        return true;
    }

    public boolean checkResource(ResourceType resource, int quantity) {
        if (playerResources.containsKey(resource) && playerResources.get(resource) >= quantity) {
            return true;
        } else {
            return false;
        }
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public void addVictoryPoint() {
        victoryPoints++;
    }

    public int getTotalResources() {
        int total = 0;
        for (int amount : playerResources.values()) {
            total += amount;
        }
        return total;
    }

    // Costs 1 wood, 1 brick, 1 sheep, 1 wheat. Only builds if board says the spot
    // is ok.
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

    // Costs 2 wheat, 3 ore. Replaces one of your settlements with a city (same
    // spot).
    public void buildCity(Board board, Intersection buildIntersection) {
        if (!checkResource(ResourceType.Wheat, 2) || !checkResource(ResourceType.Ore, 3)) {
            return;
        }
        if (board.placeCity(buildIntersection, this)) {
            removeResource(ResourceType.Wheat, 2);
            removeResource(ResourceType.Ore, 3);

            for (int i = 0; i < playerSettlements.size(); i++) {
                if (playerSettlements.get(i).getBuildlocation() == buildIntersection) {
                    playerSettlements.remove(i);
                    break;
                }
            }

            playerCities.add(new City(buildIntersection, this));
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

    // If over 7 cards we must try to spend (settlement then city then road). Else
    // pick one build at random and try it.
    public String takeRandomAction(Board board) {
        Random random = new Random();

        if (getTotalResources() > 7) {
            for (int i = 0; i <= 53; i++) {
                Intersection spot = board.getIntersection(i);
                if (spot != null && spot.getBuilding() == null) {
                    int vpBefore = victoryPoints;
                    buildSettlement(board, spot);
                    if (victoryPoints > vpBefore) {
                        return "forced spend: built settlement at " + i;
                    }
                }
            }
            for (int i = 0; i < playerSettlements.size(); i++) {
                Intersection spot = playerSettlements.get(i).getBuildlocation();
                int vpBefore = victoryPoints;
                buildCity(board, spot);
                if (victoryPoints > vpBefore) {
                    return "forced spend: upgraded to city at " + spot.getIntersectionLocation();
                }
            }
            for (int i = 0; i <= 53; i++) {
                for (int j = i + 1; j <= 53; j++) {
                    if (board.isValidEdge(i, j)) {
                        int roadsBefore = playerRoads.size();
                        Edge edge = new Edge(i, j);
                        buildRoad(board, edge);
                        if (playerRoads.size() > roadsBefore) {
                            return "forced spend: built road at " + i + "-" + j;
                        }
                    }
                }
            }
            return "forced spend: could not build anything";
        }

        int action = random.nextInt(3); // 0 = settlement, 1 = city, 2 = road.

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
            List<int[]> validEdges = new ArrayList<>();
            for (int i = 0; i <= 53; i++) {
                for (int j = i + 1; j <= 53; j++) {
                    if (board.isValidEdge(i, j)) {
                        validEdges.add(new int[] { i, j });
                    }
                }
            }
            if (!validEdges.isEmpty()) {
                int randomIndex = random.nextInt(validEdges.size());
                int[] picked = validEdges.get(randomIndex);
                Edge edge = new Edge(picked[0], picked[1]);
                buildRoad(board, edge);
                return "attempted road at edge " + picked[0] + "-" + picked[1];
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