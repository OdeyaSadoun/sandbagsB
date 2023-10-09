// Odeya Sadoun 212380406 | Part 2

import java.util.Scanner;

public class Main {
    static final int BOARD_SIZE = 10;
    static final String PLAYER_1_NAME = "A";
    static final String PLAYER_2_NAME = "B";
    private static final String ILLEGAL_STEP_ERROR = "you cant go outside the board";
    private static final String INCORRECT_CHOOSE_OPTION_ERROR =
            "invalid option, please enter a number between 1 and 4";
    public static int playerALocationX;
    public static int playerALocationY;
    public static int playerBLocationX;
    public static int playerBLocationY;
    public static int winnersCarpetLocationX;
    public static int winnersCarpetLocationY;
    public static int winnersCarpetSideSize;
    public static boolean finishGame = false;

    public static void main(String[] args) {
        initializeValues();
        printBoard();
        int minimumStepsToFirstPlayer = minSteps(xLocationByPlayer(PLAYER_1_NAME), yLocationByPlayer(PLAYER_1_NAME));
        System.out.println("A" + minimumStepsToFirstPlayer);
        int minimumStepsToSecondPlayer = minSteps(xLocationByPlayer(PLAYER_2_NAME), yLocationByPlayer(PLAYER_2_NAME));
        System.out.println("B" + minimumStepsToSecondPlayer);

        /**
         * If we consider the situation that in any case A will start playing first -
         * he has a gap relative to B by a step difference of 1.
         * Therefore - everywhere in the following conditions,
         * instead of the minimum value of player A, we will subtract 1 from the minimum value.
         * In this way, it will meet the conditions of example number 2 on the test cases page,
         * where A and B are exactly the same distance from the carpet, each has 3 steps to reach,
         * but in the output it is written that A wins,
         * and the only option for the fact that the result is not "the distances are equal"
         * which gives precedence to A and he Apparently the first will win not because he has fewer steps,
         * but because he starts the journey of steps before B.
         * ------------------------------------------------------
         * But, if the process is as I understood it,
         * that if and above the number of steps is the same, the code should be left as it is,
         * and checking the above example - the value: "equals" will be printed.
         * */

        if (minimumStepsToSecondPlayer == minimumStepsToFirstPlayer) {
            System.out.println("Chances are equal for both players");
        } else if (minimumStepsToFirstPlayer < minimumStepsToSecondPlayer) {
            System.out.println("A player has the better chance");
        } else {
            System.out.println("B player has the better chance");
        }

        // For the situation that I wrote before:
//        if (minimumStepsToSecondPlayer == minimumStepsToFirstPlayer - 1) {
//            System.out.println("Chances are equal for both players");
//        } else if (minimumStepsToFirstPlayer - 1 < minimumStepsToSecondPlayer) {
//            System.out.println("A player has the better chance");
//        } else {
//            System.out.println("B player has the better chance");
//        }
    }

    // A function for find the minimum distance between 2 points when the steps are only by X and Y axes
    public static int manhattanDistance(int locationX1, int locationY1, int locationX2, int locationY2) {
        int distance = Math.abs(locationX1 - locationX2) + Math.abs(locationY1 - locationY2);

        return distance;
    }

    private static int minSteps(int xLocationByPlayer, int xLocationByPlayer1) {
        // The minimum value need to be some distance between all the winners carpet,
        // for the initial value the distance to the left corner of this carpet
        int minimum = manhattanDistance(xLocationByPlayer, xLocationByPlayer1, winnersCarpetLocationX, winnersCarpetLocationY);
        int distanceBetweenTwoPoints;

        for (int row = winnersCarpetLocationX; row < winnersCarpetLocationX + winnersCarpetSideSize; row++) {

            for (int column = winnersCarpetLocationY; column < winnersCarpetLocationY + winnersCarpetSideSize; column++) {

                distanceBetweenTwoPoints = manhattanDistance(xLocationByPlayer, xLocationByPlayer1, row, column);

                if (distanceBetweenTwoPoints < minimum) {
                    minimum = distanceBetweenTwoPoints;
                }
            }
        }

        return minimum;
    }


    private static void initializeValues() {
        Scanner input = new Scanner(System.in);
        System.out.println("WELCOME \n" + "enter A player X location");
        playerALocationX = input.nextInt();
        System.out.println("enter A player Y location");
        playerALocationY = input.nextInt();
        System.out.println("enter B player X location");
        playerBLocationX = input.nextInt();
        System.out.println("enter B player Y location");
        playerBLocationY = input.nextInt();
        System.out.println("enter WINNERS CARPET top left location");
        winnersCarpetLocationX = input.nextInt();
        winnersCarpetLocationY = input.nextInt();
        System.out.println("enter WINNERS CARPET size");
        winnersCarpetSideSize = input.nextInt();
    }

    private static void printBoard() {
        for (int row = 0; row < BOARD_SIZE + 2; row++) {

            for (int column = 0; column < BOARD_SIZE + 2; column++) {

                if (row == 0 || column == 0 || row == BOARD_SIZE + 1 || column == BOARD_SIZE + 1) { // frame
                    System.out.print("#");
                } else if (row == playerALocationX && column == playerALocationY) {
                    System.out.print(PLAYER_1_NAME);
                } else if (row == playerBLocationX && column == playerBLocationY) {
                    System.out.print(PLAYER_2_NAME);
                } else if (winnersCarpetLocationX <= row
                        && row < winnersCarpetLocationX + winnersCarpetSideSize
                        && winnersCarpetLocationY <= column
                        && column < winnersCarpetLocationY + winnersCarpetSideSize) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }

            System.out.println();
        }
    }

    //Help function to know which player is and return his x location
    private static int xLocationByPlayer(String player) {
        if (player == PLAYER_1_NAME) {
            return playerALocationX;
        }

        return playerBLocationX;
    }

    //Help function to know which player is and return his y location
    private static int yLocationByPlayer(String player) {
        if (player == PLAYER_1_NAME) {
            return playerALocationY;
        }

        return playerBLocationY;
    }
}
