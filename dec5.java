import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class dec5 {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("input5.txt");
        Scanner data = new Scanner(input); 

        ArrayList<Integer[]> constraints = new ArrayList<Integer[]>();

        String s;
        while(data.nextLine().contains("|")){
            s=data.nextLine();
            constraints.add(new Integer[]{Integer.parseInt(s.substring(0, 2)), Integer.parseInt(s.substring(3, 5))});

        }

        System.out.println(Arrays.toString(constraints.get(0)));
        data.nextLine();

        String[] row; 
        int sum = 0;
        int sumtest = 0;

        while(data.hasNextLine()){
            row = data.nextLine().split(",");

            if(isCorrect(row, constraints)) sum += middleNumber(row);
            sumtest+= middleNumber(row);

        }

        System.out.println(sum);



    }

    private static boolean isCorrect(String[] line, ArrayList<Integer[]> constraints){
        for(int i = 0; i<line.length; i++){
            for(Integer[] hej: constraints){
                if(Integer.parseInt(line[i]) == hej[1] && i < line.length-1){
                    System.out.println("hej");
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