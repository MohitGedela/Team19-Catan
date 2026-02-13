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
            
        }
    }
}
