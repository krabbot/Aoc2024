import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class dec2 {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("input2.txt");
        Scanner data = new Scanner(input); 

        int safeReports = 0;
        int[] array;

        while(data.hasNextLine()){
            array = toIntArray(data.nextLine());
            if(isSafe(array)) safeReports++;

        }

        System.out.println(safeReports);

    }

    public static int[] toIntArray(String s){
        String[] stringArray = s.split(" ");
        for(int i = 0; i<stringArray.length; i++) if(stringArray[i] == "") System.out.println(s); 
        int[] intArray = new int[stringArray.length];
        for(int i = 0; i<intArray.length;i++) intArray[i] = Integer.parseInt(stringArray[i]);
        return intArray;

    }

    public static boolean isSafe(int[] array){
        if((isIncreasing(array) || isDecreasing(array)) && diffOK(array)) return true;
        
        for(int i = 0; i<array.length; i++){
            if(safeWithout(array, i)) return true;
        }
        
        return false;
    }

    private static boolean isDecreasing(int[] report){
        int min = report[0];
        for(int i = 1; i<report.length; i++){
            if (!(report[i] < min)) return false;
            min = report[i];
        }
        return true;
    }

    private static boolean isIncreasing(int[] report){
        int max = report[0];
        for(int i = 1; i<report.length; i++){
            if (!(report[i] > max)) return false;
            max = report[i];
        }
        return true;
    }

    private static boolean diffOK(int[] report){
        for(int i = 1; i<report.length; i++){
            if (Math.abs(report[i] - report[i-1])>3) return false;
        }
        return true;
    }

    private static boolean safeWithout(int[] array, int index){
        int[]newArray = new int[array.length-1];
        for(int i = 0; i<newArray.length; i++){
            if(i<index) newArray[i] = array[i];
            else newArray[i] = array[i+1];
        }
        if((isIncreasing(newArray) || isDecreasing(newArray)) && diffOK(newArray)) return true;
        System.out.println(Arrays.toString(newArray));
        return false;
    }

    


}