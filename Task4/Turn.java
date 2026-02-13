import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Turn {

    private Dice dice;
    private Production production;
    private Board board;

    public Turn(Dice dice, Production production, Board board) {
        this.dice = dice;
        this.production = production;
        this.board = board;
    }

    public String execute(Player player, int roundNumber) {
        int roll = dice.roll();
        production.generateResources(roll);

        String actionResult = player.takeRandomAction(board);

        return "[" + roundNumber + "] Player "
                + player.getPlayerID()
                + " rolled " + roll
                + " -> " + actionResult;
    }
}