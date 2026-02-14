import java.util.*;

public class Production {

    private Board board;

    public Production(Board gameBoard) {
        board = gameBoard;
    }

    public void generateResources(int diceRollNum) {

        for (HexTerrain hex : board.getHexes()) {
            if (!hex.productionStatus()) {
                continue;
            }

            HexBoardNum boardNumber = hex.getHexNumber();
            if (boardNumber != null && boardNumber.getHexNum() == diceRollNum) {
                ResourceType resource = hex.produceResource();

                List<Integer> intersectionIDs = board.getHexIntersections(hex.getHexID());
                for (Integer intersectionID : intersectionIDs) {
                    Intersection intersection = board.getIntersection(intersectionID);

                    if (intersection.getBuilding() != null) {
                        Player intersectionOwner = intersection.getPlayer();
                        int quantity = 1;

                        if (intersection.getBuilding() instanceof City) {
                            quantity += 1;
                        }

                        intersectionOwner.addResource(resource, quantity);
                    }
                }
            }
        }
    }
}