import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//Så oförtjänt

public class dec14b {
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

        //for(int[] array : robots) System.out.println(Arrays.toString(array));
        int times = 0;
        while(!villkor(robots)){
            for(int[] array : robots) updatePos(array, 1);
            times++;
        //for(int[] array : robots) System.out.println(Arrays.toString(array));
        }
        System.out.println(times);
    }

    private static void updatePos(int[] posAndV, int time){
        //System.out.println(Arrays.toString(posAndV));
        posAndV[0] = (posAndV[0] + posAndV[2] * time) % 101;
        posAndV[1] = (posAndV[1] + posAndV[3] * time) % 103;
        if(posAndV[0] < 0) posAndV[0] += 101;
        if(posAndV[1] < 0) posAndV[1] += 103;
        //System.out.println(Arrays.toString(posAndV));
    }


    private static boolean villkor(ArrayList<int[]> robots){
        int[][] robotMap = new int[101][103]; 
        for(int i = 0; i < robotMap.length; i++){
            for(int j = 0; j < robotMap[i].length; j++){
                robotMap[i][j] = 0;
            }
        }

        for(int[] array : robots){
            if(robotMap[array[0]][array[1]]>0) return false;
            robotMap[array[0]][array[1]]++;
        }

        return true;

    }


}