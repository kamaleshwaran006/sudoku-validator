package jy.sudoku.validator;

/**
 *
 * @author jonathanyantz
 */

public class Validate implements Runnable {
    private final int[] puzzle;
    
    /**
     * gets the puzzle that is supplied;
     * sets all variables to class variables.
     * @param puzzle
     */
    public Validate(int puzzle[]) {
        
        this.puzzle = puzzle;
    }//end Validate
    
    @Override
    public void run() {
        /**
         * sets up an array to hold data for each number (1-9);
         * sets all values to 0;
         * increment the given location for each number;
         * if any number shows up more than once, fail.
         */
        int[] options = new int[9];
        for(int i = 0; i < 9; i++) {
            options[i] = 0;
        }
        
        for(int i = 0; i < puzzle.length; i++) {
            switch(puzzle[i]) {
                case 1:
                    options[0]++;
                    break;
                case 2:
                    options[1]++;
                    break;
                case 3:
                    options[2]++;
                    break;
                case 4:
                    options[3]++;
                    break;
                case 5:
                    options[4]++;
                    break;
                case 6:
                    options[5]++;
                    break;
                case 7:
                    options[6]++;
                    break;
                case 8:
                    options[7]++;
                    break;
                case 9:
                    options[8]++;
                    break;
                default:
                    break;
            }
        }
        
        for(int i = 0; i < 9; i++) {
            if(options[i] > 1) {
                Check ck = new Check();
                ck.updateCorrect(false);
            }
        }
    }//end run
}//end class Validate
