import java.util.*;

public class Production {
 
    private Board gameBoard;
    private List<Player> gamePlayers;

    public Production(Board gameBoard, List<Player> gamePlayers) {
        this.gameBoard = gameBoard;
        this.gamePlayers = gamePlayers;
    }

    public void generateResources(int diceRollNum, Board board) {
        
        if (diceRollNum == 7) {
            return;
        }

        for (HexTerrain hex : board.getTiles()) {
            if (!hex.productionStatus()) {
                continue;
            }

            HexBoardNum boardNumber = hex.getHexNumber();
            if (boardNumber != null && boardNumber.getHexNum() == diceRollNum) {
                ResourceType resource = hex.produceResource();

                List<Integer> intersectionIDs = board.getNeighbouringIntersections(hex.getHexID());
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
