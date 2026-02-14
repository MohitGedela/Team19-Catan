import java.util.List;

public class Simulator {

    private List<Player> players;
    private Turn turn;
    private int maxRounds;

    public Simulator(List<Player> players, Turn turn, int maxRounds) {
        this.players = players;
        this.turn = turn;
        this.maxRounds = maxRounds;
    }

    public void runGame() {
        int round = 1;
        boolean gameOver = false;

        while (!gameOver && round <= maxRounds) {

            System.out.println("******Round " + round + "******");

            for (Player player : players) {

                String result = turn.execute(player, round);
                System.out.println(result);

                if (player.getVictoryPoints() >= 10) {
                    System.out.println("Player " + player.getPlayerID() + " wins with " + player.getVictoryPoints()
                            + " victory points!");

                    gameOver = true;
                    break;
                }
            }

            // print victory points at the end of each round
            for (Player player : players) {
                System.out.println("Player " + player.getPlayerID() + " VP: " + player.getVictoryPoints());
            }

            round++;
        }

        if (!gameOver) {
            System.out.println("Game ended after " + maxRounds + " rounds. No winner.");
        }
    }
}
