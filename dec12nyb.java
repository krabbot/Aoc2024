import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/* :/ */

public class dec12nyb {
    public static void main(String[] args) throws FileNotFoundException {

        File input = new File("input12.txt");
        Scanner data = new Scanner(input);

        char[][] map = new char[140][140];
        boolean[][] visited = new boolean[140][140];

        for(int i = 0; i<140; i++){
            map[i] = data.nextLine().toCharArray();
            for(int j = 0; j<140; j++){
            
                visited[i][j] = false;
            }
        }

        long totalPrice=0;

        for(int r = 0; r<140; r++){
            for(int c = 0; c<140; c++){
                if(!visited[r][c])totalPrice += price(map, r, c, visited);
            }
        }
        System.out.println(totalPrice);
    }

    private static long price(char[][] map, int row, int col, boolean[][] visited){
        boolean[][] field = new boolean[140][140];
        for(int r = 0; r<140; r++){
            for(int c = 0; c<140; c++){
                field[r][c] = false;
            }
        }

        long area = 0;

        findNeighbours(map, row, col, field, visited);
        for(int r = 0; r<140; r++){
            for(int c = 0; c<140; c++){
                if(field[r][c]) area++;
            }
        }
        long sides = sides(field);
        return area * sides;
    }
    
    private static void findNeighbours(char[][]map, int row, int col, boolean[][]field, boolean[][]visited){
        char c = map[row][col];
        field[row][col] = true;
        visited[row][col] = true;

        if(row!=0 && map[row-1][col] == c && !visited[row-1][col]) findNeighbours(map, row-1, col, field, visited);
        if(row<139 && map[row+1][col] == c && !visited[row+1][col]) findNeighbours(map, row+1, col, field, visited);
        if(col<139 && map[row][col+1] == c && !visited[row][col+1]) findNeighbours(map, row, col+1, field, visited);
        if(col!=0 && map[row][col-1] == c && !visited[row][col-1]) findNeighbours(map, row, col-1, field, visited);

    }

    private static long sides(boolean[][]field){
        //System.out.println(sidesDOWN(field) + ", " + sidesUP(field) + ", "+sidesRIGHT(field) + ", " + sidesLEFT(field) );
        return sidesDOWN(field) + sidesLEFT(field) + sidesRIGHT(field) + sidesUP(field);

    }
    private static long sidesUP(boolean[][]field){
        boolean counts = true;
        long sideUps=0;

        for(int r = 0; r<140; r++){
            for(int c = 0; c<140; c++){
                if(field[r][c]){
                    if(counts&&(r==0 ||!field[r-1][c])){
                        sideUps++;
                        counts = false;
                    }
                    if(r>0 && field[r-1][c]) counts = true;
                }
                else counts = true;
            }
            counts = true;
        }
        return sideUps;
    }

    private static long sidesDOWN(boolean[][]field){
        boolean counts = true;
        long sideDowns=0;

        for(int r = 0; r<140; r++){
            for(int c = 0; c<140; c++){
                if(field[r][c]){
                    if(counts&&(r==139 ||!field[r+1][c])){
                        sideDowns++;
                        counts = false;
                    }
                    if(r<139 && field[r+1][c]) counts = true;
                }
                else counts = true;
            }
            counts = true;
        }
        return sideDowns;
    }

    private static long sidesRIGHT(boolean[][]field){
        boolean counts = true;
        long sidesRIGHT=0;

        for(int c = 0; c<140; c++){
            for(int r = 0; r<140; r++){
                if(field[r][c]){
                    if(counts&&(c==139 ||!field[r][c+1])){
                        sidesRIGHT++;
                        //System.err.println(r + " " + c);
                        counts = false;
                    }
                    if(c<139 && field[r][c+1]) counts = true;
                }
                else counts = true;
            }
            counts = true;
        }
        return sidesRIGHT;
    }

    private static long sidesLEFT(boolean[][]field){
        boolean counts = true;
        long sidesLEFT=0;

        for(int c = 0; c<140; c++){
            for(int r = 0; r<140; r++){
                if(field[r][c]){
                    if(counts&&(c==0 ||!field[r][c-1])){
                        sidesLEFT++;
                        counts = false;
                    }
                    if(c>0 && field[r][c-1]) counts = true;
                }
                else counts = true;
            }
            counts = true;
        }
        return sidesLEFT;
    }

}