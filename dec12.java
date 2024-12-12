import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/* Medelful lösning som inte fungerar */

public class dec12 {
    public static void main(String[] args) throws FileNotFoundException {

        File input = new File("input12.txt");
        Scanner data = new Scanner(input);

        

        char[][] map = new char[140][140];
        
        int fields = 0;

        for(int i = 0; i<140; i++){
            map[i] = data.nextLine().toCharArray();
        }

        long totalPrice = 0;

        for(int i = 0; i<140; i++){
            for(int j = 0; j<140; j++){
                if(map[i][j] != '-'){
                    long price = price(map, i, j);
                    //System.out.println(price);
                    fields++;
                    totalPrice += price;
                } 
            }
        }

        //for(char[] row:map)System.out.println(Arrays.toString(row));
        System.out.println(totalPrice);
        System.out.println(fields);


    }



    private static long price(char[][] map, int r, int c){
        ArrayList<Integer> fieldInRegion = new ArrayList<Integer>();
        findRelated(map, r, c, fieldInRegion);
        int area = fieldInRegion.size();
        int perimeter = perimeter(map, fieldInRegion);


        char ch = map[r][c];
        for(int i:fieldInRegion){
            //System.out.print(".");
            if(ch!=map[(i & 0xFF00) >>8][i & 0xFF]) System.out.println("ERROR");
            map[(i & 0xFF00) >>8][i & 0xFF] = '-';
        }

        
        //System.out.println();
        //System.out.println(r + ", " + c + ", " + area + ", " + perimeter);
        //System.out.println(perimeter);

        if(perimeter<4)System.out.println("ERROR");
        long price = area*perimeter;
        return price;
    } 

    private static int perimeter(char[][] map, ArrayList<Integer> fieldInRegion){
        int r; 
        int c;
        char field;
        int perimeter = 0;
        
        for(int i:fieldInRegion){
            r=(i & 0xFF00) >>8;
            c=i & 0xFF;

            field = map[r][c];

            if(r > 139 || c>139) System.err.println("ERROR");

            if(r==0 || map[r-1][c]!= field) perimeter++;
            if(r==139 || map[r+1][c]!= field) perimeter++;
            if(c==0 || map[r][c-1]!= field) perimeter++;
            if(c==139 || map[r][c+1]!= field) perimeter++;

        }
        return perimeter;
    }
    private static void findRelated(char[][] map, int r, int c, ArrayList<Integer> fieldInRegion){
        fieldInRegion.add(c | (r<<8));
        char field = map[r][c];
        for(int i = r-1; i<r+2; i+=2){
            if(i>0&&i<map.length&& !fieldInRegion.contains(c | (i<<8)) && map[i][c] == field){
                //fieldInRegion.add(c | (i<<8));
                findRelated(map, i, c, fieldInRegion);
            }
        }
        for(int i = c-1; i<c+2; i+=2){
            if(i>0&&i<map[r].length&& !fieldInRegion.contains(i | (r<<8)) &&map[r][i] == field){
                //fieldInRegion.add(i | (r<<8));
                findRelated(map, r, i, fieldInRegion);
            }
        }        
    }
}