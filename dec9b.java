import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

/* Hade mycket problem */

public class dec9b {
    public static void main(String[] args) throws FileNotFoundException {

        File input = new File("input9.txt");
        Scanner data = new Scanner(input);

        char[] charInput = data.nextLine().toCharArray();
        int[] intInput = new int[charInput.length];

        for(int i = 0; i < intInput.length; i++){
            intInput[i] = Integer.parseInt(charInput[i]+"");
        }

        ArrayList<Integer> blocks = new ArrayList<Integer>();

        for(int i = 0; i<intInput.length; i++){
            if(i%2 == 1)for(int j = 0; j<intInput[i]; j++) blocks.add(-1);
            else for(int j = 0; j<intInput[i]; j++) blocks.add(i/2);
        }

        int frontIndex; 
        int backIndex;
        int fileSize;
        int curID = blocks.get(blocks.size()-1);

        while(curID > 1){
            backIndex = blocks.lastIndexOf(curID);
            fileSize = blocks.lastIndexOf(curID) - blocks.indexOf(curID) + 1;
            frontIndex = blocks.indexOf(-1);

            while(frontIndex < backIndex){
                while(blocks.get(frontIndex)!= -1) frontIndex++;
                if(frontIndex >= backIndex) break;
                if(fileSize <= getWidthfromFront(blocks, frontIndex)){
                    for(int i = 0; i<fileSize; i++){
                        blocks.set(frontIndex + i, curID);
                        blocks.set(backIndex-i, -1);
                    }  
                    break;             
                }
                frontIndex += getWidthfromFront(blocks, frontIndex);
            }
            curID--;
        }

        
        long sum = 0;

        for(int i = 0; i<blocks.size(); i++){
            if(blocks.get(i) >= 0) sum+= i*blocks.get(i);
        }
        System.out.println(sum);
    }

    private static int getWidthfromFront(ArrayList<Integer> list, int i){
        int width = 1;
        int element = list.get(i);
        while(list.get(++i) == element) width++;
        return width;
    }

}
