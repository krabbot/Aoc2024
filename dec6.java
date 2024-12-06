import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


/*
 * Katastrofal kod
 */

public class dec6 {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("input6.txt");
        Scanner data = new Scanner(input);

        ArrayList<ArrayList<Character>> maP = new ArrayList<ArrayList<Character>>();

        
        while(data.hasNextLine()){
            ArrayList<Character> row = new ArrayList<Character>();
            for (char c : data.nextLine().toCharArray()) {
                row.add(c);
            }
            maP.add(row);
        }

        /*GuardPath path = new GuardPath(map);
        path.markVisited();
        int num1 = path.noOfMarks();
        System.out.println(num1);*/
        
        int count = 0;

        for(int i = 0; i<maP.size(); i++){
            for(int j = 0; j<maP.get(0).size();j++){
                if(maP.get(i).get(j) == '.'){
                    //count++;
                    //for(ArrayList<Character> a : maP) System.out.println(a.toString());
                
                    GuardPath path = new GuardPath(maP);

                    path.placeObstacle(i, j);
                    //System.out.println(i + ", " + j);
                    if(path.loops()) count++;

                }
            }
        }
    System.out.println(count);

            }
        }
        
        class GuardPath{
        
            ArrayList<ArrayList<Character>> map;
            boolean[][] marked;
            int[][][] turns; //y, x, turned 
            int xPos;
            int yPos;
            int dx;
            int dy;
        
            public GuardPath(ArrayList<ArrayList<Character>> data){
                map = new ArrayList<ArrayList<Character>>();
                for(ArrayList<Character> a : data){
                    ArrayList<Character> line = new ArrayList<Character>();
                    for(Character c : a) line.add(c);
                    map.add(line);
                } 
                dx = 0;
                dy = -1;
        
                for(int i = 0; i<map.size(); i++){
                    if(map.get(i).contains('^')){
                        yPos= i;
                        xPos = map.get(i).indexOf('^');
                        //System.out.println("start: " + xPos + ", " + yPos);
                    }
                }
        
                marked = new boolean[map.size()][map.get(0).size()];
                turns = new int[map.size()][map.get(0).size()][5];
                for(int i = 0; i<marked.length; i++){
                    for(int j = 0; j<marked[i].length; j++){
                        marked[i][j] = false;
                        for(int h = 0; h<5; h++) turns[i][j][h] = 0;
                    }
                } 

                marked[yPos][xPos] = true;
            }
        
            public void markVisited() {
               while(nextInBounds()){
                //System.out.println("hej");
                while(map.get(yPos+dy).get(xPos+dx)=='#'){
                    turnRight();
                    System.out.println("Sväng");
                }
                xPos += dx;
                yPos += dy; 
                marked[yPos][xPos] = true;
                System.out.println(yPos + ", " + xPos);

               }
            }

            public boolean loops() {
                while(nextInBounds()){
                 //System.out.println("hej");
                 while(map.get(yPos+dy).get(xPos+dx)=='#'){
                    if(turns[yPos][xPos][2*dx+dy+2]==1){
                        System.out.println("loop");
                        return true;
                    } 
                    else turns[yPos][xPos][2*dx+dy+2]=1;
                    turnRight();
                    //System.out.println("Sväng");
                 }
                 xPos += dx;
                 yPos += dy; 
                 //marked[yPos][xPos] = true;
                 //System.out.println(yPos + ", " + xPos);
 
                }
                System.out.println("inte loop");
                return false;
             }

            private boolean nextInBounds(){
                int xPos2 = xPos + dx;
                int yPos2 = yPos +dy;

                if(xPos2 >= 0 && xPos2 < marked[0].length && yPos2 >= 0 && yPos2 < marked.length) return true; 
                return false;

            }

            private void turnRight(){
                int dySave = dy;
                dy=dx;
                dx=(-1)*dySave;
            }

            public int noOfMarks(){
                int marks = 0;
                for(int i = 0; i<marked.length; i++){
                    //System.out.println(Arrays.toString(marked[i]));
                    for(int j = 0; j<marked[i].length; j++){
                        if(marked[i][j] == true){
                            //System.out.println(i + ", " + j);
                            marks++;
                        } 
                    }
                } 
                
                return marks;
            }

            public void placeObstacle(int i, int j){
                map.get(i).set(j, '#');
                //for(ArrayList<Character> a : map) System.out.println(a.toString());
                
            }

}