import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class dec14 {
    public static void main(String[] args) throws FileNotFoundException {

        File input = new File("input14.txt");
        Scanner data = new Scanner(input);

        ArrayList<int[]> robots = new ArrayList<int[]>();

        String s[];
        while(data.hasNextLine()){
            s = data.nextLine().split(" ");
            int[] posAndV = new int[4]; //xpos, ypos, xv, yv
            posAndV[0] = Integer.parseInt(s[0].substring(2, s[0].indexOf(",")));
            posAndV[1] = Integer.parseInt(s[0].substring(s[0].indexOf(",")+1, s[0].length()));
            posAndV[2] = Integer.parseInt(s[1].substring(2, s[1].indexOf(",")));
            posAndV[3] = Integer.parseInt(s[1].substring(s[1].indexOf(",")+1, s[1].length()));
            robots.add(posAndV);
        }

        for(int[] array : robots) updatePos(array, 100);

        System.out.println(safetyFactor(robots));
    }

    private static void updatePos(int[] posAndV, int time){
        System.out.println(Arrays.toString(posAndV));
        posAndV[0] = (posAndV[0] + posAndV[2] * time) % 101;
        posAndV[1] = (posAndV[1] + posAndV[3] * time) % 103;
        if(posAndV[0] < 0) posAndV[0] += 101;
        if(posAndV[1] < 0) posAndV[1] += 103;
        System.out.println(Arrays.toString(posAndV));
    }

    private static long safetyFactor(ArrayList<int[]> robots){
        int[][] robotMap = new int[101][103]; 
        for(int i = 0; i < robotMap.length; i++){
            for(int j = 0; j < robotMap[i].length; j++){
                robotMap[i][j] = 0;
            }
        }

        for(int[] array : robots){
            robotMap[array[0]][array[1]]++;
        }

        return quadrantSum(robotMap, 1) * quadrantSum(robotMap, 2) * quadrantSum(robotMap, 3) * quadrantSum(robotMap, 4);
    }

    private static long quadrantSum(int[][] array, int quadrant){
        int xStartIndex = 0; int xEndIndex = array.length; int yStartIndex = 0; int yEndIndex = array[0].length;
        if(quadrant<3)yEndIndex = array[0].length/2;
        else yStartIndex = array[0].length/2 + 1;
        if(quadrant%2==1) xEndIndex = array.length/2;
        else xStartIndex = array.length/2+1;

        System.out.println(xStartIndex + ", " + xEndIndex + ", " + yStartIndex + ", " + yEndIndex);

        long sum = 0;

        for(int x = xStartIndex; x < xEndIndex; x++){
            for(int y = yStartIndex; y < yEndIndex; y++){
                sum+=array[x][y];
            }
        }
        return sum;
    }

}