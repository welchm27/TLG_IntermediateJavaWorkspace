package com.duckrace.app;
import com.duckrace.Board;
import com.duckrace.Reward;

import java.sql.SQLOutput;
import java.util.Locale;
import java.util.Scanner;

/*
 * Application "controller."
 * Sets up the system objects, orchestrates the overall flow of the application.
 * Prompts user for inputs and "forwards" those inputs into the system (back end).
 */
public class DuckRaceApp {
    // instance variables
    private final Scanner scanner = new Scanner(System.in);     // read inputs from console
    private final Board board = Board.getInstance();

    public void execute(){
        welcome();
        showBoard();
        int id = promptForId();
        Reward reward = promptForReward();  // D (for DEBIT_CARD) or P (for PRIZES)
        updateBoard(id, reward);
        showBoard();
    }

    private void updateBoard(int id, Reward reward) {
        board.update(id, reward);
    }

    private Reward promptForReward() {
        Reward reward = null;

        boolean validInput = false;
        while (!validInput){
            System.out.print("Please enter [D]ebit card or [P]rizes: ");
            String input = scanner.nextLine().trim().toUpperCase();          // you'll never use any other scanner input if dealing with a human
            if (input.matches("D|P")) {             // if input matches either a D or a P
                validInput=true;
                reward = ("D".equals(input)) ? Reward.DEBIT_CARD : Reward.PRIZES;   // item on left of : is the "if true" return, on the right of : is the "if false" return
                /*
                if ("D".equals(input)){                 // or you could use a ternary here (more later)
                    reward = Reward.DEBIT_CARD;
                }
                else{
                    reward = Reward.PRIZES;
                }

                 */
            }
        }
        return reward;
    }

    private int promptForId() {
        int id = 0;

        boolean validInput = false;
        while (!validInput) {
            System.out.print("Please enter id of the winner [1-13]: ");     // TODO: don't hardcode 13  HINT: board has a student ID map and that has a size
            String input = scanner.nextLine().trim();      // waits for an enter key to be pressed // .trim() removes any leading/trailing whitespace chars
            if (input.matches("\\d{1,2}")) {  // \d means any digit, 1 or 2 occurrences
                id = Integer.parseInt(input);       // safe to convert to int at this point
                if (1 <= id && id <= 13) {          // valid id, TODO: don't hardcode the 13
                    validInput = true;
                }
            }
        }
        return id;
    }

    private void showBoard() {
        board.show();
    }

    private void welcome() {
        System.out.println("---------------------------------------------------------------------");
        System.out.println("W E L C O M E   T O   T H E   D U C K R A C E   A P P L I C A T I O N");
        System.out.println("---------------------------------------------------------------------");
        System.out.printf(" Brought you to you by Michael Welch Not-So-Incorporated\n" +
                "       (\\_/) \n" +
                "       (O.O) \n" +
                "       (m m)o \n");
    }


}   // END OF CLASS