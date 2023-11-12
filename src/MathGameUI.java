import java.util.Scanner;

public class MathGameUI {

    private MathGame game;
    private Scanner scan;

    public MathGameUI() {
        game = null;
        scan = new Scanner(System.in);
        setup();
        mainMenu();
    }

    private void setup() {
        System.out.println("Welcome to the Math Game!");
        System.out.print("Enter player 1's name: ");
        String p1Name = scan.nextLine();
        System.out.print("Enter player 2's name: ");
        String p2Name = scan.nextLine();
        System.out.print("Enter player 3's name: ");
        String p3Name = scan.nextLine();

        Player p1 = new Player(p1Name);
        Player p2 = new Player(p2Name);
        Player p3 = new Player(p3Name);
        game = new MathGame(p1, p2, p3, scan);
    }

    private void mainMenu() {
        String playAgain = "y";
        while (playAgain.equals("y")) {
            game.resetGame(); // reset game
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("GAME HAS STARTED!");
            game.playRound(); // play one round of game
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("GAME OVER!");
            if (game.getWinner() != null) {  // check for null just to be safe (so we don't call a method on null)
                System.out.println("The winner was " + game.getWinner().getName());   // print winner's name
                System.out.println("with a score of " + game.getWinner().getScore()); // print winner's score
                System.out.println(game.getWinner().getName() + " has won " + game.getStreak() + " games in a row!");
            }
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.print("Want to play another round? y/n: ");
            playAgain = scan.nextLine().toLowerCase();
            while (!(playAgain.equals("y")) && !(playAgain.equals("n"))) {
                System.out.println("Invalid response!");
                System.out.print("Want to play another round? y/n: ");
                playAgain = scan.nextLine().toLowerCase();
            }
        }
        System.out.println("Thanks for playing the Math Game! Goodbye!");
    }
}