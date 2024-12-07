import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


/* Den här är faktiskt relativt ren */

public class dec7 {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("input7.txt");
        Scanner data = new Scanner(input);

        long sum = 0;
        ArrayList<Integer> terms = new ArrayList<Integer>();
        long product=0;

        String s[];
        String[] tempTerms;

        while(data.hasNextLine()){
            terms.clear();
            s = data.nextLine().split(": ");
            tempTerms = s[1].split(" ");
            product = Long.parseLong(s[0]);
            for(String str: tempTerms) terms.add(Integer.parseInt(str));

            if(hasTrueEquation(terms.get(0), product, terms, 1)) sum += product;

        }
        System.out.println(sum);

    }


    private static boolean hasTrueEquation(long curValue, long product, ArrayList<Integer> terms, int nextIndex){       

        if(curValue > product) return false;

        if(nextIndex>=terms.size()){
            if(curValue == product) return true;
            return false;
        } 

        if(hasTrueEquation(curValue + terms.get(nextIndex), product, terms, nextIndex+1) 
        || hasTrueEquation(curValue * terms.get(nextIndex), product, terms, nextIndex+1)
        || hasTrueEquation(Long.parseLong(new String(curValue + "") + terms.get(nextIndex)), product, terms, nextIndex+1)) return true;
        return false;
        
    }
}