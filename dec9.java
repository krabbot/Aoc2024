import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

/* Den här koden fungerar inte */

public class dec9 {
    public static void main(String[] args) throws FileNotFoundException {

        File input = new File("input9.txt");
        Scanner data = new Scanner(input);

        String data2= "2333133121414131402";

        //char[] charInput = data.nextLine().toCharArray();
        char[] charInput = data2.toCharArray();


        int[] intInput = new int[charInput.length];

        for(int i = 0; i< intInput.length; i++){
            intInput[i] = Integer.parseInt(charInput[i]+"");
        }

        ArrayList<Integer> blocks = new ArrayList<Integer>();

        for(int i = 0; i<intInput.length; i++){
            if(i%2 == 1)for(int j = 0; j<intInput[i]; j++) blocks.add(-1);
            else for(int j = 0; j<intInput[i]; j++) blocks.add(i/2);
        }

        int frontIndex = 0; 
        int backIndex = blocks.size()-1;
        int fileSize = getWidthfromBack(blocks, backIndex);

        //boolean dont = false;

        while(1 < backIndex){
            frontIndex = 0;
            while(frontIndex < backIndex){
                while(blocks.get(frontIndex)!= -1) frontIndex++;
                while(blocks.get(backIndex) == -1) backIndex--;

                if(backIndex < frontIndex) break;

                fileSize = getWidthfromBack(blocks, backIndex);
                //System.out.println(frontIndex + ", " + backIndex);
                if(fileSize <= getWidthfromFront(blocks, frontIndex)){
                    int id = blocks.get(backIndex);
                    for(int i = 0; i<fileSize; i++){
                        //System.out.println(id);
                        blocks.set(frontIndex + i, id);
                        blocks.set(backIndex-i, -1);
                    }
                    break;
                }
                frontIndex += fileSize;
                //System.out.println(blocks.toString());
            }
            backIndex -=fileSize;
            //System.out.println(backIndex);
        }
        
        long sum = 0;

        for(int i = 0; i<10; i++){
            System.out.print(blocks.get(i) + " ");
        }

        for(int i = 0; i<blocks.size(); i++){
            if(blocks.get(i) >= 0) sum+= i*blocks.get(i);
        }
        //System.out.println(blocks.toString());
        System.out.println(sum);
        //System.out.println(blocks.toString());

    }
    private static int getWidthfromFront(ArrayList<Integer> list, int i){
        int width = 1;
        int element = list.get(i);
        while(list.get(++i) == element) width++;
        return width;
    }

    private static int getWidthfromBack(ArrayList<Integer> list, int i){
        int width = 1;
        int element = list.get(i);
        while(i > 0 && list.get(--i) == element) width++;
        return width;
    }
}
