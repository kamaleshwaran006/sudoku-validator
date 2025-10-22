package jy.sudoku.validator;

import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 * @author jonathanyantz
 */

public class Main {

    public static void main(String[] args) {
        boolean cont = false;
        int[][] bits = new int[0][0];
        
        /**
         * in the while loop, user input is captured and checked;
         * this input must be a correct file path to a sudoku puzzle stored in a text file;
         * once the file path is read in, the puzzle is inputted into a 2d array
         * and returned to this document.
         */
        while(cont == false) {
            System.out.println("Please type the location and file name of the sudoku puzzle you would like to check.");
            Scanner input = new Scanner(new InputStreamReader(System.in));

            bits = Puzzle.get(input.nextLine());
            
            if(bits.length > 0) {
                cont = true;
            }
        }
        
        /**
         * this code gets the sudoku puzzle from the 2d array;
         * outputs it to the screen to show the puzzle being checked.
         */
        System.out.println("\nYour puzzle: ");
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(j < 8) {
                    System.out.print(bits[i][j] + " | ");
                } else {
                    System.out.println(bits[i][j]);
                }
            }
        }
        
        /**
         * this code runs the check;
         * then checks to see if the puzzle returned correct or not.
         */
        Check ck = new Check();
        ck.setup(bits);
        boolean run = ck.run();
        if(run == false) {
            System.out.println("\nThe puzzle is not correct.");
        } else {
            System.out.println("\nThe puzzle is correct.");
        }
        
    }//end main
}//end class Main
