import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class dec15 {
    public static void main(String[] args) throws FileNotFoundException {

        File input = new File("input15.txt");
        Scanner data = new Scanner(input);

        int dim = 50;
        char[][] map = new char[dim][2*dim];

        int index = 0;
        char[]line = data.nextLine().toCharArray();
        while(line.length>1){
            char[]wideLine = new char[2*dim];
            for(int i = 0; i<dim; i++){
                if(line[i]=='.'){wideLine[2*i] ='.'; wideLine[2*i+1] ='.';}
                else if(line[i]=='#'){wideLine[2*i] ='#'; wideLine[2*i+1] ='#';}
                else if(line[i]=='O'){wideLine[2*i] ='['; wideLine[2*i+1] =']';}
                else if(line[i]=='@'){wideLine[2*i] ='@'; wideLine[2*i+1] ='.';}
            }
            map[index++] = wideLine;
            line=data.nextLine().toCharArray();
        }

        ArrayList<Character> moves = new ArrayList<Character>();

        while(data.hasNextLine()) for(char c: data.nextLine().toCharArray()) moves.add(c);

        //for(char[] c: map) System.out.println(Arrays.toString(c));
        //if(data.hasNextLine()) System.out.println(data.nextLine());
        //System.out.println(moves.toString());

        int robR=-1;
        int robC=-1;

        for(int i = 0; i<dim; i++){
            for(int j = 0; j<2*dim; j++){
                if(map[i][j] == '@'){
                    robR = i;
                    robC = j;
                }
            }
        }
        
        int[] robPos;
        for(int i = 0; i<moves.size(); i++){
            //if(i<315&&i>300&&i%1 == 0)System.out.println(i + ", " + moves.get(i) + ", " +robR + ", " + robC);
            if(i<315&&i>300&&i%1000==0) for(char[] c: map){
                for(char ch:c) System.out.print(ch);
                System.out.println();
            }
            robPos = makeMove(map, moves, i, robR, robC);
            robR = robPos[0];
            robC = robPos[1];
        
            //System.out.println();
        }        

        long sum = 0;
        for(int i = 0; i<dim; i++){
            for(int j = 0; j<2*dim; j++){
                if(map[i][j] == '['){
                    sum+=100*i+j;
                }
            }
        }
        for(char[] c: map) System.out.println(Arrays.toString(c)); 
        System.out.println(sum);
    }

    private static int[] makeMove(char[][] map, ArrayList<Character> moves, int i, int robR, int robC){

        int[] robPos = new int[2];
        switch(moves.get(i)){
            case '<': robPos = moveLeft(map, robR, robC); break;
            case '>': robPos = moveRight(map, robR, robC); break;
            case '^': robPos = moveUp(map, robR, robC); break;
            case 'v': robPos = moveDown(map, robR, robC); break;
            default: break;
        }
        return robPos;      
    }

    private static int[] moveLeft(char[][] map, int robR, int robC){
    
        int lastBox = robC;
        while(map[robR][lastBox-2] == '[') lastBox-=2;
        if(map[robR][lastBox-1] == '.'){
            map[robR][robC] = '.';
            map[robR][robC - 1] = '@';
            robC--;
            for(int i = robC - 1; i >= lastBox-1; i--){
                if(map[robR][i] == '[') map[robR][i] = ']';
                else map[robR][i] = '[';
            }
        }

        int[] robPos = new int[2];
        robPos[0]=robR;
        robPos[1]=robC;
        return robPos;
    }

    private static int[] moveRight(char[][] map, int robR, int robC){
        //System.out.println(robR + ", " + robC);

        int lastBox = robC;
        while(map[robR][lastBox+2] == ']') lastBox+=2;
        if(map[robR][lastBox+1] == '.'){
            map[robR][robC] = '.';
            map[robR][robC + 1] = '@';
            robC++;
            for(int i = robC + 1; i <= lastBox+1; i++){
                if(map[robR][i] == ']') map[robR][i] = '[';
                else map[robR][i] = ']';
            }

        }

        int[] robPos = new int[2];
        robPos[0]=robR;
        robPos[1]=robC;
        return robPos;

    }

    private static int[] moveUp(char[][] map,  int robR, int robC){
        //row, col, only for ']'
        ArrayList<int[]> above = new ArrayList<int[]>();
    
        if(addAbove(map, above, robR, robC)){
            
            for(int i = above.size()-1; i>=0; i--){
                //System.out.println(above.get(i)[0] + ", " + above.get(i)[1]);
                //if(!map[i[0]][i[1]] = '@' || map[i[0]][i[1]-1] = @;)
                map[above.get(i)[0]][above.get(i)[1]] = '.';
                if(!contains(above, above.get(i)[0]+1, above.get(i)[1]-1))map[above.get(i)[0]][above.get(i)[1]-1] = '.';
                map[above.get(i)[0]-1][above.get(i)[1]] = ']';
                map[above.get(i)[0]-1][above.get(i)[1]-1] = '[';
            }
            map[robR][robC] = '.';
            map[robR-1][robC] = '@';
            robR--;
            //if(map[robR][robC-1] == '[') map[robR][robC-1] = '.';
        }

        int[] robPos = new int[2];
        robPos[0]=robR;
        robPos[1]=robC;
        return robPos;

    }

    private static int[] moveDown(char[][] map,  int robR, int robC){
        //row, col, only for ']'
        ArrayList<int[]> below = new ArrayList<int[]>();
        
        //if(robR == 25 && robC == 52)System.out.println(map[robR+2][robC]);
        if(addBelow(map, below, robR, robC)){
            
            for(int i = below.size()-1; i>=0; i--){
                if(robR == 25 && robC == 52)System.out.println(below.get(i)[0] + ", " +below.get(i)[1]);
                map[below.get(i)[0]][below.get(i)[1]] = '.';
                if(!contains(below, below.get(i)[0]-1, below.get(i)[1]-1))map[below.get(i)[0]][below.get(i)[1]-1] = '.';
                map[below.get(i)[0]+1][below.get(i)[1]] = ']';
                map[below.get(i)[0]+1][below.get(i)[1]-1] = '[';
            }
            map[robR][robC] = '.';
            map[robR+1][robC] = '@';
            robR++;
            //if(map[robR][robC-1] == '[') map[robR][robC-1] = '.';
        }

        int[] robPos = new int[2];
        robPos[0]=robR;
        robPos[1]=robC;
        return robPos;

    }

    private static boolean addAbove(char[][] map, ArrayList<int[]> above, int row, int col){
        for(int[] i: above){
            if(i[0]==row && i[1]== col) return true;
        }
        int[] current = {row, col};
        if(map[row][col] != '@') above.add(current);

        if(map[row][col] == '@'){
            if(map[row-1][col] == '#') return false;
            else if(map[row-1][col] == ']'){
                if(!addAbove(map, above, row-1, col)) return false;   
            }
            else if(map[row-1][col] == '['){
                if(!addAbove(map, above, row-1, col+1)) return false;
            }   
        }

        else{
            if(map[row-1][col] == '#'|| map[row-1][col-1] == '#') return false;
            if(map[row-1][col] == '[') if(!addAbove(map, above, row-1, col+1)) return false;
            if(map[row-1][col] == ']') if(!addAbove(map, above, row-1, col)) return false;
            if(map[row-1][col-1] == ']') if(!addAbove(map, above, row-1, col-1)) return false;
        }
        
        return true;

    }

    private static boolean addBelow(char[][] map, ArrayList<int[]> below, int row, int col){
        for(int[] i: below){
            if(i[0]==row && i[1]== col) return true;
        }
        if(row == 24 && col == 51) System.out.println(map[row+1][col]);
        int[] current = {row, col};
        if(map[row][col] != '@') below.add(current);

        if(map[row][col] == '@'){
            if(map[row+1][col] == '#') return false;
            else if(map[row+1][col] == ']'){
                if(!addBelow(map, below, row+1, col)) return false;
            }
            else if(map[row+1][col] == '['){
                if(!addBelow(map, below, row+1, col+1)){if(row == 24 && col == 51)System.out.println("hej"); return false;}
                
            }  
        }

        else{
            if(map[row+1][col] == '#'|| map[row+1][col-1] == '#') return false;
            if(map[row+1][col] == '[') if(!addBelow(map, below, row+1, col+1)) return false;
            if(map[row+1][col] == ']') if(!addBelow(map, below, row+1, col)) return false;
            if(map[row+1][col-1] == ']') if(!addBelow(map, below, row+1, col-1)) return false;
        }
        
        return true;

    }

    private static boolean contains(ArrayList<int[]> list, int r, int c){
        for(int[] i: list){
            if(i[0]==r && i[1]== c) return true;
        }
        return false;
    }
}
