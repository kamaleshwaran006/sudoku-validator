package jy.sudoku.validator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author jonathanyantz
 */

public class Check {
    private int[][] puzzle;
    private int boxSize;
    private static boolean correct;
    
    /**
     * gets the puzzle that is supplied;
     * sets all variables to class variables.
     * @param puzzle
     */
    public void setup(int[][] puzzle) {
        this.puzzle = puzzle;
        boxSize = (int) Math.sqrt(puzzle.length);
        correct = true;
        
    }//end setup
    
    public boolean run() {
        /**
         * sets-up the cached thread pool.
         */
        ExecutorService pool = Executors.newCachedThreadPool();
        
        /**
         * steps through each option for validation;
         * 9 rows, 9 columns, and 9 boxes;
         * submits them to threads.
         */
        for(int i = 0; i < puzzle.length; i++) {
            pool.execute(new Validate(puzzle[i]));
        }
        
        int[][] row = getColumn();
        for(int i = 0; i < row.length; i++) {
            pool.execute(new Validate(row[i]));
        }
        
        int[][] box = getBox();
        for(int i = 0; i < box.length; i++) {
            pool.execute(new Validate(box[i]));
        }
        
        /**
         * waits for all threads to finish, then shuts-down all threads.
         */
        pool.shutdown();
        
        return correct != false;
    }//end run
    
    /**
     * takes in the puzzle;
     * turns the puzzle;
     * returns every column as a row in a new 2d array.
     */
    private int[][] getColumn() {
        int[][] turnPuzzle = new int[puzzle.length][puzzle.length];
        
        for(int i = 0; i < puzzle.length; i++) {
            for(int j = 0; j < puzzle.length; j++) {
                turnPuzzle[i][j] = puzzle[j][i];
            }
        }
        
        return turnPuzzle;
    }//end getRows
    
    /**
     * takes in the puzzle;
     * gets the boxes by using square root of the puzzle length;
     * returns every box as a row in a new 2d array.
     */
    private int[][] getBox() {
        int[][] boxPuzzle = new int[puzzle.length][puzzle.length];
        
        int row = 0;
        
        for(int i = 0; i < puzzle.length; i += 3) {
            for(int j = 0; j < puzzle.length; j += 3) {

                int r = (i / boxSize) * boxSize;
                int c = (j / boxSize) * boxSize;

                for(int k = 0; k < puzzle.length; k++) {
                    boxPuzzle[row][k] = puzzle[r + (k % boxSize)][c + (k / boxSize)];
                }
                row++;
            }
        }
        
        return boxPuzzle;
    }//end getBox
    
    public void updateCorrect(boolean change) {
        correct = change;
    }//end updateCorrect
    
    public boolean getCorrect() {
        return correct;
    }//end getCorrect
}//end class Check
