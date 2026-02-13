import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Catan {

    public static void main(String[] args) {

        Board board = new Board();
        Dice dice = new Dice();
        Production production = new Production(board);

        List<Player> players = new ArrayList<Player>();

        Player p1 = new Player(1, 0, new ArrayList<>(), 
        new ArrayList<>(), new ArrayList<>(), new HashMap<>());

        Player p2 = new Player(2, 0, new ArrayList<>(), 
        new ArrayList<>(), new ArrayList<>(), new HashMap<>());

        Player p3 = new Player(3, 0, new ArrayList<>(),
        new ArrayList<>(), new ArrayList<>(), new HashMap<>());

        players.add(p1);
        players.add(p2);
        players.add(p3);

        board.placeSettlement(board.getIntersection(0), p1);
        board.placeSettlement(board.getIntersection(10), p2);
        board.placeSettlement(board.getIntersection(20), p3);

        Turn turn = new Turn(dice, production, board);
        Simulator simulator = new Simulator(players, turn);

        simulator.runGame();
    }
}