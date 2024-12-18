import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/*Lugn och skön dag */

public class dec18 {
    public static void main(String[] args) throws FileNotFoundException {

        File input = new File("input18.txt");
        Scanner data = new Scanner(input);

        int dim = 71;
        char[][] map = new char[dim][dim];

        for(int i = 0; i<dim; i++){
            for(int j = 0; j<dim; j++){
                map[i][j] = '.';
            }
        }

        int index = 0;
        String[] line;
        int x; 
        int y;
     
        while(data.hasNextLine()&& index < 1024){
            line = data.nextLine().split(",");
            x = Integer.parseInt(line[0]);
            y = Integer.parseInt(line[1]);
            map[x][y] = '#';
            index++;
        }
        System.out.println(shortestRouteLength(map));
    }

    private static int shortestRouteLength(char[][] map){
        HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();    
        
        for(int i = 0; i< map.length; i++){
            for(int j = 0; j<map.length; j++){
                hmap.put(i | (j<<8), Integer.MAX_VALUE-1000);
            }
        }

        hmap.replace(0|(0<<8),0);        
        update(map, hmap);
        int i = 0;
        while(i<1000){
            update(map, hmap);
            i++;
        }

        return hmap.get((map.length-1)|((map.length-1)<<8));
    }

    private static void update(char[][] map, HashMap<Integer,Integer> hmap){

        for(int i = 0; i< map.length; i++){
            for(int j = 0; j<map.length; j++){
                if(map[i][j] != '#'){
                    setBest(map, hmap, i, j);
                }
            }
        }
    }

    private static void setBest(char[][] map, HashMap<Integer, Integer> hmap, int i, int j){
        int[] options = new int[4];
        int index = 0;

        if(i>0) options[index++] = hmap.get((i-1)|(j<<8));
        if(i<map.length-1) options[index++] = hmap.get((i+1)|(j<<8));
        if(j>0) options[index++] = hmap.get(i|((j-1)<<8));
        if(j<map.length-1) options[index++] = hmap.get(i|((j+1)<<8));

        Arrays.sort(options, 0, index);
        if(options[0]+1 < hmap.get((i)|(j<<8))) hmap.replace((i)|(j<<8), options[0]+1);
    }
}