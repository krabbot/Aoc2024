import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class dec5b {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("input5.txt");
        Scanner data = new Scanner(input); 

        ArrayList<Integer[]> constraints = new ArrayList<Integer[]>();

        String s = data.nextLine();
        while(s.contains("|")){
            constraints.add(new Integer[]{Integer.parseInt(s.substring(0, 2)), Integer.parseInt(s.substring(3, 5))});
            s=data.nextLine();
        }

        System.out.println(s);
        //data.nextLine();


        String[] row; 
        int sum = 0;

        while(data.hasNextLine()){
            row = data.nextLine().split(",");

            if(!isCorrect(row, constraints)){
                //System.out.println(Arrays.toString(row));
                row = reOrder(row, constraints);
                //System.out.println(Arrays.toString(row));
                //System.out.println(middleNumber(row));
                sum += middleNumber(row);
            }
            //else System.out.println("OK");

            //if(!isCorrect(row, constraints)) System.out.println("FEL");


        }

        System.out.println(sum);



    }

    private static String[] reOrder(String[] line, ArrayList<Integer[]> constraints){
        //System.out.println(Arrays.toString(line));
        while(!isCorrect(line, constraints)){
            for(int i = 0; i<line.length; i++){
                for(Integer[] hej: constraints){
                    if(Integer.parseInt(line[i]) == hej[1] && i < line.length-1){
                        //System.out.println("hej");
                        for(int j = i+1; j<line.length; j++){
                            if(Integer.parseInt(line[j]) == hej[0]){
                                String save = line[j];
                                line[j] = line[i];
                                line[i]= save;
                                //System.out.println("hej");

                            }
                        }
                    }
                }
            }

        }
        //System.out.println(Arrays.toString(line));
        return line;
    }

    private static boolean isCorrect(String[] line, ArrayList<Integer[]> constraints){
        for(int i = 0; i<line.length; i++){
            for(Integer[] hej: constraints){
                if(Integer.parseInt(line[i]) == hej[1] && i < line.length-1){
                    //System.out.println("hej");
                    for(int j = i+1; j<line.length; j++){
                        if(Integer.parseInt(line[j]) == hej[0]) return false;
                    }
                }
            }
        }

        return true;
    }

    private static int middleNumber(String[] numbers){
        return Integer.parseInt(numbers[numbers.length/2]);
    }
}