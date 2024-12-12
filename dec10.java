import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

/* Gjorde oavsiktligt del 2 när jag skulle göra del 1 */

public class dec10 {
    public static void main(String[] args) throws FileNotFoundException {

        File input = new File("input10.txt");
        Scanner data = new Scanner(input);

        char[] charInput;
        int[][] map = new int[54][54];

        for(int i = 0; i < 54; i++){
            charInput = data.nextLine().toCharArray();
            for(int j = 0; j < 54; j++){
                map[i][j] = Integer.parseInt(charInput[j]+"");
            }
        }

        long sum = 0;

        for(int i = 0; i < 54; i++){
            for(int j = 0; j < 54; j++){
                if(map[i][j] == 0){
                    sum+= findTrails(map, i, j, 0);
                    //resetMap(map);        Del 1
                }
            } 
        }

        System.out.println(sum);
        
    }

    
    //x is y and y is x
    private static int findTrails(int[][] map, int xPos, int yPos, int elevation){
        
        if(elevation == 9) {
            //map[xPos][yPos] = -1;         Del 1
            return 1;
        }
        int noOfTrails = 0;
        if(xPos>0 && map[xPos-1][yPos] == elevation+1) noOfTrails += findTrails(map, xPos-1, yPos, elevation+1);
        if(xPos<map.length-1 && map[xPos+1][yPos] == elevation+1) noOfTrails += findTrails(map, xPos+1, yPos, elevation+1);
        if(yPos>0 && map[xPos][yPos-1] == elevation+1) noOfTrails += findTrails(map, xPos, yPos-1, elevation+1);
        if(yPos<map[xPos].length-1 && map[xPos][yPos+1] == elevation+1) noOfTrails += findTrails(map, xPos, yPos+1, elevation+1);

        return noOfTrails;
    }

    private static void resetMap(int[][] map){
        for(int i = 0; i < 54; i++){
            for(int j = 0; j < 54; j++){
                if(map[i][j] == -1) map[i][j] = 9;
            }
        }

    }
}