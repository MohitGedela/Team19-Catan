import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.Set;

public class Board {
    private int[][] tilesNodes = {{41,42,40,18,17,39}, {40,44,43,21,16,18}, {43,45,47,46,19,21},
                                  {38,39,17,15,14,37}, {17,18,16,5,4,15}, {16,21,19,20,0,5},
                                  {19,46,48,49,22,20}, {36,37,14,13,32,35}, {14,15,4,3,12,13},
                                  {4,5,0,1,2,3}, {0,20,22,23,6,1}, {22,49,50,51,52,23},
                                  {34,13,12,11,32,33}, {12,3,2,9,10,11}, {2,1,6,7,8,9},
                                  {6,23,52,53,24,7}, {32,11,10,29,30,31}, {10,9,8,27,28,29},
                                  {8,7,24,25,26,27}};
                                
    private Map<Integer, Intersection> intersections;
    private Map<Integer, HexTerrain> tiles;
    private Map<Integer, List<Integer>> adjacency;
    private Set<Road> builtRoads;
    private GameRules rules = new GameRules();


    public boolean isValidEdge(int start, int end) {
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 6; j++) {
                if (j != 4) {
                    if ((tilesNodes[i][j] == start && tilesNodes[i][j+1] == end) || (tilesNodes[i][j] == end && tilesNodes[i][j+1] == start)) {
                        return true;
                    }
                } 
                if ((tilesNodes[i][0] == start && tilesNodes[i][5] == end) || (tilesNodes[i][0] == end && tilesNodes[i][5] == start)) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<Integer> getNeighbouringIntersections(int intersectionID) {
        List<Integer> neighbours = new ArrayList<>();

        for (int i = 0; i <= 53; i++) { 
            if (i == intersectionID) {
                continue;
            }
            
            if (isValidEdge(intersectionID, i)) {
                neighbours.add(i);
            }
        }

        return neighbours;
    }

    public Intersection getIntersection(int intersectionID) {
        return intersections.get(intersectionID);
    }

    public boolean placeSettlement(Intersection placeIntersection, Player player) {
        if (rules.checkEmptyIntersections(placeIntersection.getIntersectionLocation(), this)) {
            if (player.getPlayerSettlements().size() < 2) {
                Settlement settlement = new Settlement(placeIntersection, player);
                placeIntersection.setBuilding(settlement);
                placeIntersection.setOwner(player);
                return true;
            }
        } else {
            if (rules.isConnected(placeIntersection.getIntersectionLocation(), player, this)) {
                Settlement settlement = new Settlement(placeIntersection, player);
                placeIntersection.setBuilding(settlement);
                placeIntersection.setOwner(player);
                return true;
            }
        }
        return false;
    }

    public boolean placeCity(Intersection placeIntersection, Player player) {
        Building existing = placeIntersection.getBuilding();
        if (existing instanceof Settlement && existing.getOwner() == player) {
            City city = new City(placeIntersection, player);
            placeIntersection.setBuilding(city);
            return true;
        }
        return false;
    }

    public boolean placeRoad(Edge placeEdge, Player player) {
        if (rules.checkRoadPlacement(placeEdge, player, this)) {
            Road road = new Road(player, placeEdge);
            player.getPlayerRoads().add(road);
            placeEdge.setNotEmpty();
            return true;
        }
        return false;
    }

    public List<HexTerrain> getHexes() {
        return new ArrayList<>(tiles.values());
    }

    public HexTerrain getHex(int hexID) {
        return tiles.get(hexID);
    }

    public List<Integer> getHexIntersections(int hexID) {
        List<Integer> hexes = new ArrayList<>();

        for (int hex : tilesNodes[hexID]) {
            hexes.add(hex);
        }

        return hexes;
    }
}