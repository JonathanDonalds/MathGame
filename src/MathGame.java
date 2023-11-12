import java.util.Objects;
import java.util.Scanner;

public class MathGame {

    private Player player1;
    private Player player2;
    private Player player3;
    private Player currentPlayer;
    private Player winner;
    private boolean gameOver;
    private Scanner scanner;
    private int p1Streak;
    private int p2Streak;
    private int p3Streak;
    private int streak;
    private int incorrect;
    private String firstOut;
    private int count;

    public MathGame(Player player1, Player player2, Player player3, Scanner scanner) {
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.scanner = scanner;
        currentPlayer = null;
        winner = null;
        gameOver = false;
        p1Streak = 0;
        p2Streak = 0;
        p3Streak = 0;
        streak = 0;
        incorrect = 0;
        firstOut = null;
        count = 0;
    }

    public Player getWinner() {
        return winner;
    }

    public void playRound() {
        chooseStartingPlayer();
        while (!gameOver) {
            printGameState();
            System.out.println("Current player: " + currentPlayer.getName());
            boolean correct = askQuestion();
            if (correct) {
                System.out.println("Correct!");
                currentPlayer.incrementScore();
                swapPlayers();
            } else {
                System.out.println("INCORRECT!");
                incorrect++;
                if (count == 0) {
                    firstOut = currentPlayer.getName();
                }
                if (firstOut.equals(currentPlayer.getName()) && incorrect == 2) {
                    gameOver = true;
                }
                gameOver = incorrect == 2;
                determineWinner();
                swapPlayers();
                count++;
            }
        }
    }

    private void printGameState() {
        System.out.println("--------------------------------------");
        System.out.println("Current Scores:");
        System.out.println(player1.getName() + ": " + player1.getScore());
        System.out.println(player2.getName() + ": " + player2.getScore());
        System.out.println(player3.getName() + ": " + player3.getScore());
        System.out.println("--------------------------------------");
    }

    public void resetGame() {
        player1.reset();
        player2.reset();
        player3.reset();
        gameOver = false;
        currentPlayer = null;
        winner = null;
    }

    private void chooseStartingPlayer() {
        int randNum = (int) (Math.random() * 3) + 1;
        if (randNum == 1) {
            currentPlayer = player1;
        } else if (randNum == 2) {
            currentPlayer = player2;
        } else {
            currentPlayer = player3;
        }
    }

    private boolean askQuestion() {
        int operation = (int) (Math.random() * 4) + 1;
        int num1 = (int) (Math.random() * 100) + 1;
        int num2;
        int correctAnswer;
        System.out.println("Type in your answer as an integer (/ is int division)");
        if (operation == 1) {
            num2 = (int) (Math.random() * 100) + 1;
            System.out.print(num1 + " + " + num2 + " = ");
            correctAnswer = num1 + num2;
        } else if (operation == 2) {
            num2 = (int) (Math.random() * 100) + 1;
            System.out.print(num1 + " - " + num2 + " = ");
            correctAnswer = num1 - num2;
        } else if (operation == 3) {
            num2 = (int) (Math.random() * 10) + 1;
            System.out.print(num1 + " * " + num2 + " = ");
            correctAnswer = num1 * num2;
        } else {
            num2 = (int) (Math.random() * 10) + 1;
            System.out.print(num1 + " / " + num2 + " = ");
            correctAnswer = num1 / num2;
        }

        int playerAnswer = scanner.nextInt();
        scanner.nextLine();

        if (playerAnswer == correctAnswer) {
            return true;
        } else {
            return false;
        }
    }

    private void swapPlayers() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else if (currentPlayer == player2) {
            currentPlayer = player3;
        } else {
            currentPlayer = player1;
        }
    }

    private void determineWinner() {
        if (player1.getScore() > player2.getScore()) {
            if (player1.getScore() > player3.getScore()) {
                winner = player1;
                p1Streak++;
                streak = p1Streak;
            } else {
                winner = player3;
                p3Streak++;
                streak = p3Streak;
            }
        } else if (player2.getScore() > player3.getScore()) {
            winner = player2;
            p2Streak++;
            streak = p2Streak;
        }
        if ((player1.getScore() == player2.getScore()) && currentPlayer == player3) {
            winner = player1;
            p1Streak++;
            streak = p1Streak;
        }
        if ((player2.getScore() == player3.getScore()) && currentPlayer == player1) {
            winner = player2;
            p2Streak++;
            streak = p2Streak;
        }
        if ((player3.getScore() == player1.getScore()) && currentPlayer == player2) {
            winner = player3;
            p3Streak++;
            streak = p3Streak;
        }
    }

    public int getStreak() {
        return streak;
    }
}