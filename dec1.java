import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class dec1 {
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
            System.out.println(ss[0]);
            rightLine[index] = Integer.parseInt(ss[ss.length-1]);
            index++;
        }

        Arrays.sort(leftLine);
        Arrays.sort(rightLine);


        int sum = 0;
        int diff=0;
        for (int i = 0; i < 1000; i++){
            diff = rightLine[i] - leftLine[i];
            //System.out.println(diff);
            if(diff < 0) diff = (-1)*diff;
            sum += diff;

        }


        System.out.println(sum);
    }
}