import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class dec3 {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("input3.txt");
        Scanner data = new Scanner(input); 

        char[] charArray;
        int sum = 0;

        while(data.hasNextLine()){
            charArray = data.nextLine().toCharArray();
            for(int i = 0; i<charArray.length; i++){
                sum+= getMult(charArray, i);
            }

        }

        System.out.println(sum);
    }

    private static int getMult(char[] array, int i){
        if(array.length-1 < i+7) return 0;
        if(!(array[i] == 'm' && array[i+1] == 'u' && array[i+2] == 'l' && array[i+3] == '(') && Character.isDigit(array[i+4])) return 0;

        int length1 = 0;
        int x= i+4;
        while(Character.isDigit(array[x++])) length1++;
        
        if(length1 == 0 || array[--x] !=',') return 0;
        x++;

        int length2 = 0;
        while(Character.isDigit(array[x++])) length2++;

        if(array[--x] !=')') return 0;

        int num1 = Integer.parseInt(new String(array, i+4, length1));
        System.out.println(num1);

        int num2 = Integer.parseInt(new String(array, i+4+length1+1, length2));
        System.out.println(num2);


        return num1*num2;
    }
}