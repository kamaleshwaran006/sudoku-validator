package jy.sudoku.validator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author jonathanyantz
 */

public class Puzzle {

    public static int[][] get(String location) {
        ArrayList<String> list = new ArrayList();
        
        /**
         * in the try, get the file in a scanner;
         * while there is still another line, keep adding to the arraylist;
         * if the file doesn't exist, it returns an error.
         */
        try {
            Scanner contents = new Scanner(new File(location));
            
            while(contents.hasNextLine()) {
                String line = contents.nextLine();
                list.add(line);
            }
            
        } catch(FileNotFoundException e) {
            System.out.println("File does not exist.");
            list.clear();
            
            int[][] empty = new int[0][0];
            return empty;
        }
        
        /**
         * this returns the 2d array after it had been made.
         */
        return make2d(list);
        
    }//end get
    
    private static int[][] make2d(ArrayList puzzle) {
        int[][] arr = new int[puzzle.size()][puzzle.size()];
        
        /**
         * in the for, every line in the array is stepped through;
         * in each line, every number is moved into a 2d array.
         */
        for(int i = 0; i < puzzle.size(); i++) {
            String[] splitLine = puzzle.get(i).toString().split("-");
            
            for(int j = 0; j < splitLine.length; j++) {
                arr[i][j] = Integer.parseInt(splitLine[j]);
            }
        }
        
        return arr;
        
    }//end make2d
}//end class Puzzle
