import java.util.Scanner;

public class SnakeAndLadder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of players: ");
        int numPlayers = sc.nextInt();
        Player[] players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter name for player " + (i + 1) + ": ");
            String name = sc.next();
            players[i] = new Player(name);
        }
        int[] board = new int[100];
        // initialize board with ladders
        board[3] = 25;
        board[6] = 14;
        board[20] = 56;
        // initialize board with snakes
        board[27] = 1;
        board[45] = 5;
        board[70] = 17;


        boolean gameOver = false;
        while (!gameOver) {
            for (int i = 0; i < numPlayers; i++) {
                Player player = players[i];
                System.out.print(player.getName() + ": Roll the dice (y/n)? ");
                String roll = sc.next();
                if (!roll.equals("y")) {
                    continue;
                }
                int diceRoll = (int) (Math.random() * 6) + 1;
                System.out.println(player.getName() + " rolled a " + diceRoll);
                player.setPosition(player.getPosition() + diceRoll);
                if (player.getPosition() >= 100) {
                    gameOver = true;
                    System.out.println(player.getName() + " wins the game!");
                    break;
                }
                
                int newPos = board[player.getPosition()];
                if (newPos > 0) {
                    System.out.println(player.getName() + " climbed a ladder to " + newPos);
                    player.setPosition(newPos);
                } 
                else if (newPos < 0) {
                    System.out.println(player.getName() + " encountered a snake to " + newPos);
                    player.setPosition(newPos);
                }
            }
        }
        sc.close();
    }
}
