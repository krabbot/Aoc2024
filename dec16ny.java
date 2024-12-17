import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/* Mardröm, kaos, vill aldrig mer se */


public class dec16ny {
    public static void main(String[] args) throws FileNotFoundException {

        File input = new File("input16.txt");
        Scanner data = new Scanner(input);

        int dim = 141;
        char[][] map = new char[dim][dim];

        int index = 0;
        
        while(data.hasNextLine()){
            map[index++] = data.nextLine().toCharArray();           
        }

        HashMap<Integer, Long> hmap = hej(map);
      
        boolean[][] bestPath = new boolean[dim][dim];
        for(int i = 0; i<dim; i++){
            for(int j = 0; j<dim; j++){
                bestPath[i][j] = false;
            }
        }

        int count = 0;

        markPath(map, hmap, 1, map.length - 2,bestPath);
         for(int i = 0; i<dim; i++){
            for(int j = 0; j<dim; j++){
                if(bestPath[i][j] == false) System.out.print(".");
                else{
                    System.out.print("X");
                    count++;
                }
            }
            System.out.println();      
         }
        System.out.println(count);
       
    }

    private static void markPath(char[][] map,HashMap<Integer, Long> hmap, int i, int j, boolean[][] bestPath){
        bestPath[i][j] = true;
    
        int k = bestDir(hmap, i, j);
        long val = bestDirVal(hmap, i, j);
        switch (k) {
            case 0:{ 
                if(map[i][j+1]!='#' && !(hmap.get(hashThis(i-1, j, 3)) < val+999 && bestPath[i+1][j])&& !(hmap.get(hashThis(i+1, j, 1)) < val+999 && bestPath[i-1][j]))markPath(map, hmap, i, j+1, bestPath);
                if(hmap.get(hashThis(i-1, j, 3)) <= val+999 && bestPath[i+1][j]) markPath(map, hmap, i-1, j, bestPath);
                if(hmap.get(hashThis(i+1, j, 1)) <= val+999 && bestPath[i-1][j]) markPath(map, hmap, i+1, j, bestPath);
                if(hmap.get(hashThis(i,j, 4)) == val) markPath(map, hmap, i, j-1, bestPath);
                break;
            }
            case 1:{

                if(map[i+1][j]!='#' && !(hmap.get(hashThis(i, j-1, 4))< val+999 && bestPath[i][j+1]) && !(hmap.get(hashThis(i, j+1, 0)) < val+999 && bestPath[i][j-1]))markPath(map, hmap, i+1, j, bestPath);
                if(hmap.get(hashThis(i, j-1, 4))<= val+999 && bestPath[i][j+1]) markPath(map, hmap, i, j-1, bestPath);
                if(hmap.get(hashThis(i, j+1, 0)) <= val+999 && bestPath[i][j-1]) markPath(map, hmap, i, j+1, bestPath);
                if(hmap.get(hashThis(i,j, 3)) == val) markPath(map, hmap, i-1, j, bestPath);
                break;
            }

            case 3:{
                 if(map[i-1][j]!='#' && !(hmap.get(hashThis(i, j-1, 4)) < val+999 && bestPath[i][j+1]) && !(hmap.get(hashThis(i, j+1, 0)) < val+999 && bestPath[i][j-1]))markPath(map, hmap, i-1, j, bestPath);
                 if(hmap.get(hashThis(i, j-1, 4)) <= val+999 && bestPath[i][j+1]) markPath(map, hmap, i, j-1, bestPath);
                if(hmap.get(hashThis(i, j+1, 0)) <= val+999 && bestPath[i][j-1]) markPath(map, hmap, i, j+1, bestPath);
                if(hmap.get(hashThis(i,j, 1)) == val) markPath(map, hmap, i+1, j, bestPath);
                break;
            }
            case 4:{
                 if(map[i][j-1]!='#' && !(hmap.get(hashThis(i-1,j, 3)) < val+999 && bestPath[i+1][j]) && !(hmap.get(hashThis(i+1,j, 1)) < val+999 && bestPath[i-1][j]))markPath(map, hmap, i, j-1, bestPath);
                 if(hmap.get(hashThis(i-1,j, 3)) <= val+999 && bestPath[i+1][j]) markPath(map, hmap, i-1, j, bestPath);
                if(hmap.get(hashThis(i+1,j, 1)) <= val+999 && bestPath[i-1][j]) markPath(map, hmap, i+1, j, bestPath);
                if(hmap.get(hashThis(i,j, 0)) == val) markPath(map, hmap, i, j+1, bestPath);
                break;
            }
        }
        

// vänster - -1,0 - 0
// upp - 0,-1 - 1
// ner - 0,1 - 3
// höger - 1,0 - 4

    }
    private static HashMap<Integer, Long> hej(char[][] map){
        /*0-7: xpos
        8-15:ypos
        16-19:dir        
        */
        HashMap<Integer, Long> hmap = new HashMap<Integer, Long>();    
        
        for(int i = 0; i< map.length; i++){
            for(int j = 0; j<map.length; j++){
                for(int k = 0; k<5; k++){
                    if(k!=2) hmap.put(hashThis(j, i, k), Long.MAX_VALUE-100000);
                }
            }
        }

        hmap.replace(hashThis(map.length-2,1,4),(long)0);
        hmap.replace(hashThis(map.length-2,1,1),(long)1000);
        
        update(map, hmap);
        int i = 0;
        
        while(i<1000){
            update(map, hmap);
            i++;
        }
        return hmap;
    }

    private static void update(char[][] map, HashMap<Integer, Long> hmap){

        for(int i = 0; i< map.length; i++){
            for(int j = 0; j<map.length; j++){
                if(map[i][j] != '#'){
                    //if(i<0 || j<0){System.out.println("fel2"); return;}
                    setBest(map, hmap, i, j);
                }
            }
        }
    }

    private static void setBest(char[][] map, HashMap<Integer, Long> hmap, int i, int j){
        //if(i<1 || j<1){System.out.println("fel"); return;}
        if(map[i-1][j] != '#'){
            hmap.replace(hashThis(i, j, 3), best(hmap.get(hashThis(i,j,3)), hmap.get(hashThis(i-1, j, 3))+1, hmap.get(hashThis(i-1, j, 0))+1001, hmap.get(hashThis(i-1, j, 4))+1001));
        }
        if(map[i+1][j] != '#'){
            hmap.replace(hashThis(i, j, 1), best(hmap.get(hashThis(i,j,1)), hmap.get(hashThis(i+1, j, 1))+1, hmap.get(hashThis(i+1, j, 0))+1001, hmap.get(hashThis(i+1, j, 4))+1001));
        }
        if(map[i][j+1] != '#'){
            hmap.replace(hashThis(i, j, 0), best(hmap.get(hashThis(i,j,0)), hmap.get(hashThis(i, j+1, 0))+1, hmap.get(hashThis(i, j+1, 1))+1001, hmap.get(hashThis(i, j+1, 3))+1001));
        }
        if(map[i][j-1] != '#'){
            hmap.replace(hashThis(i, j, 4), best(hmap.get(hashThis(i,j,4)), hmap.get(hashThis(i, j-1, 4))+1, hmap.get(hashThis(i, j-1, 1))+1001, hmap.get(hashThis(i, j-1, 3))+1001));
        }
    }

    private static int bestDir(HashMap<Integer, Long> hmap, int i, int j){
        long min = hmap.get(hashThis(i,j,0));
        int dir = 0;
        long val;
        for(int k = 1; k<5; k++){
            if(k!=2){
                val = hmap.get(hashThis(i,j,k));
                if(val<min){
                    min = val;
                    dir = k;

                }
                //else if(val == min ) System.err.println("hej " + val);
            }
        }
        return dir;
    }
    private static long bestDirVal(HashMap<Integer, Long> hmap, int i, int j){
        long min = hmap.get(hashThis(i,j,0));
        long val;
        boolean b = false;
        for(int k = 1; k<5; k++){
            if(k!=2){
                val = hmap.get(hashThis(i,j,k));
                if(val<min){
                    min = val;
                }
                //else if(val == min && min < 90000) b=true;
            }
        }
        //if(b) System.out.println(min);
        return min;
    }

    private static int secondBestDir(HashMap<Integer, Long> hmap, int i, int j){
        long min = bestDirVal(hmap, i, j);
        int kmin = bestDir(hmap, i, j);
        long min2 = Long.MAX_VALUE-2000; 
        int k2=2;
        long val;
        for(int k = 0; k<5; k++){
            if(k!=2 && k!=kmin){
                val = hmap.get(hashThis(i,j,k));
                if(val<min2){
                    min2 = val;
                    k2 = k;
                }
                //else if(val == min && min < 90000) b=true;
            }
        }
        //if(b) System.out.println(min);
        return k2;
    }

    private static long best(long a, long b, long c, long d){
        long[] toSort = {a, b, c, d};
        Arrays.sort(toSort);
       // if(toSort[0]<Long.MAX_VALUE-10000) System.out.println(toSort[0]);
        return toSort[0];

    }

    private static int hashThis(int row, int col, int k){
        return(row | (col<<8 )| (k<<16)); 
    }
}

// vänster - -1,0 - 0
// upp - 0,-1 - 1
// ner - 0,1 - 3
// höger - 1,0 - 4
