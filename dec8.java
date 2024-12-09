import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/* Medel */

public class dec8 {
    public static void main(String[] args) throws FileNotFoundException {

        File input = new File("input8.txt");
        Scanner data = new Scanner(input);

        char[][] antennas = new char[50][50];
        boolean[][] antinodes = new boolean[50][50];

        char[] line;

        for(int y = 0; y < 50; y++){
            line = data.nextLine().toCharArray();
            for(int x = 0; x < 50; x++){
                antennas[x][y] = line[x];
                antinodes[x][y] = false;
            }
        }

        for(int x=0; x<50; x++){
            for(int y = 0; y<50; y++){
                if(antennas[x][y] != '.'){
                    //System.out.println(antennas[x][y]);
                    for(int[] xy : findAntinodes(antennas, x, y)){
                        antinodes[xy[0]][ xy[1]] = true;
                    }
                }
            }
        }

        int count = 0; 
        for(int x=0; x<50; x++){
            //System.out.println(Arrays.toString(antinodes[x]));
            for(int y = 0; y<50; y++){
                if(antinodes[x][y]) count++;
            }
        }
        System.out.println(count);

    }

    //Marks antinodes in the same row or rows above
    private static ArrayList<int[]> findAntinodes(char[][] antennas, int x, int y){
        ArrayList<int[]> nodesPos = new ArrayList<int[]>();
        char frequency = antennas[x][y];
        for(int i = 0; i <=x; i++){
            for(int j = 0; j < 50; j++){
                if(antennas[i][j] == frequency && !(i==x && j==y)){
                    //System.out.println("Hej");
                    for(int[] xy: markNodes(x, y, i, j)) nodesPos.add(xy);
                } 
            }
        }
        return nodesPos;

    }
    private static ArrayList<int[]> markNodes(int x1, int y1, int x2, int y2){
        ArrayList<int[]> nodesPos = new ArrayList<int[]>();
        int dirX = x2-x1;
        int dirY = y2-y1;

        int k = 0;
        while(x1 + k*dirX >=0 && x1 + k*dirX < 50 &&
            y1 + k*dirY >=0 && y1 + k*dirY < 50){
                int[] add = {x1 + k*dirX, y1 + k*dirY}; 
                nodesPos.add(add);
                k++;
            }

        k=1;
        while(x1 - k*dirX >=0 && x1 - k*dirX < 50 &&
        y1 - k*dirY >=0 && y1 - k*dirY < 50){
            int[] add = {x1 - k*dirX, y1 - k*dirY}; 
            k++;
            nodesPos.add(add);
        }
        return nodesPos;
    }
}