import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class dec1b {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("input1.txt");
        Scanner data = new Scanner(input); 

        int[] leftLine = new int[1000];
        int[] rightLine = new int[1000];

        String s;
        String left;
        String right;
        String[] ss;

        int index = 0;

        while(data.hasNextLine()){
            
            s = data.nextLine();
            System.out.println(s);
            ss = s.split(" ");
            leftLine[index] = Integer.parseInt(ss[0]);
            rightLine[index] = Integer.parseInt(ss[ss.length-1]);
            index++;
        }


        int sum = 0;
        for (int i = 0; i < 1000; i++){
            sum+= leftLine[i] * noOfOccs(rightLine, leftLine[i]);            

        }


        System.out.println(sum);
    }


    public static int noOfOccs(int[] array, int number){
        int count = 0;
        for(int i = 0; i<array.length; i++){
            if (array[i] == number) count++;
        }
        System.out.println("hej " + count);
        return count;
    }
}

