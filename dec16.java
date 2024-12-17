import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//Nej

public class dec16 {
    public static void main(String[] args) throws FileNotFoundException {

        File input = new File("test.txt");
        Scanner data = new Scanner(input);

        int dim = 7;
        char[][] map = new char[dim][dim];

        int index = 0;
        
        while(data.hasNextLine()){
            map[index++] = data.nextLine().toCharArray();           
        }
        for(char[] c: map){
            for(char ch: c){
                System.out.print(ch);
            } 
            System.out.println();
        }
        int startXPos = 1;
        int startYPos = dim-2;
        boolean[][] visited = new boolean[dim][dim];
        for(int i = 0; i< visited.length; i++){
            for(int j = 0; j<visited.length; j++){
               visited[i][j] = false; 
            }
        }
        long score = findLowestScore(map, visited, startXPos, startYPos, 1, 0,0);

        
        System.out.println(score);
    }


    private static long findLowestScore(char[][] map, boolean[][] marked, int xPos, int yPos, int xDir, int yDir, long score){
        if(map[yPos+yDir][xPos+xDir] == 'E') {
            //System.out.println("//hej"); 
        return score + 1;}

        System.err.println(xPos + ", " + yPos+ ", " + xDir+ ", " + yDir);

        if(score>100000) return -1;
        

        boolean[][] visited = new boolean[7][7];
        for(int i = 0; i< visited.length; i++){
            for(int j = 0; j<visited.length; j++){
               visited[i][j] = marked[i][j]; 
            }
        }
        visited[yPos][xPos] = true;
        /*for(boolean[] b: visited){
            for(boolean bo: b){
                if(bo) System.out.print("x");
                else System.out.print(".");
            } 
            System.out.println();
        }
        System.out.println();*/

        ///System.out.println(count);
        long[] options = new long[3];
        for(int i = 0; i<3; i++) options[i] = -1;
        if(map[yPos+yDir][xPos+xDir] == '.') options[0] = findLowestScore(map, visited, xPos+xDir, yPos+yDir, xDir, yDir,score+1);
        options[1] = findLowestScore(map, visited, xPos, yPos, yDir, xDir,score+1000);
        findLowestScore(map, visited, xPos, yPos, -yDir, -xDir,score+1000);

        Arrays.sort(options);
        for(int i = 0; i<3; i++){
            if(options[i] > 0){
                //System.out.println(options[i]);
             return options[i];
            }
        }
        return -1;

    }
}