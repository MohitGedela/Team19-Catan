import java.util.*;

public class GameRules {
    private int[][] tilesNodes = {{41,42,40,18,17,39}, {40,44,43,21,16,18}, {43,45,47,46,19,21},
                                  {38,39,17,15,14,37}, {17,18,16,5,4,15}, {16,21,19,20,0,5},
                                  {19,46,48,49,22,20}, {36,37,14,13,32,35}, {14,15,4,3,12,13},
                                  {4,5,0,1,2,3}, {0,20,22,23,6,1}, {22,49,50,51,52,23},
                                  {34,13,12,11,32,33}, {12,3,2,9,10,11}, {2,1,6,7,8,9},
                                  {6,23,52,53,24,7}, {32,11,10,29,30,31}, {10,9,8,27,28,29},
                                  {8,7,24,25,26,27}};

    public boolean checkBuildingPlacement(int IntersectionID, Player player) {
        return true;
    }

    public boolean checkEmptyIntersections(int intersectionID, Board board) {
        Intersection currentIntersection = board.getIntersection(intersectionID);

        if(currentIntersection.getBuilding() == null) {
            List<Integer> neighbourIntersections = board.getNeighbouringIntersections(intersectionID);

            for (Integer neighbourID : neighbourIntersections) {
                Intersection neighbour = board.getIntersection(neighbourID);

                if (neighbour.getBuilding() != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}