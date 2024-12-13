import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/* Tackar för tipsen, annars hade det inte gått */


public class dec13b {
    public static void main(String[] args) throws FileNotFoundException {

        long start = System.currentTimeMillis();

        File input = new File("input13.txt");
        Scanner data = new Scanner(input);

        ArrayList<int[]> buttonA = new ArrayList<int[]>();
        ArrayList<int[]> buttonB = new ArrayList<int[]>();
        ArrayList<int[]> prizePos = new ArrayList<int[]>();

        String s[];

        while(data.hasNextLine()){
            s = data.nextLine().split(" ");
            int [] xya = new int[2];
            xya[0]= Integer.parseInt(s[2].substring(2, s[2].length()-1));
            xya[1]= Integer.parseInt(s[3].substring(2, s[3].length()));
            buttonA.add(xya);
            //System.out.println(Arrays.toString(xy));

            s = data.nextLine().split(" ");
            int [] xyb = new int[2];
            xyb[0]= Integer.parseInt(s[2].substring(2, s[2].length()-1));
            xyb[1]= Integer.parseInt(s[3].substring(2, s[3].length()));
            buttonB.add(xyb);
            //System.out.println(Arrays.toString(xy));

            s = data.nextLine().split(" ");
            int [] xy = new int[2];
            xy[0]= Integer.parseInt(s[1].substring(2, s[1].length()-1));
            xy[1]= Integer.parseInt(s[2].substring(2, s[2].length()));
            prizePos.add(xy);
            
            if(data.hasNextLine()) data.nextLine();
        }

        for(int i = 0; i<buttonA.size(); i++){
            //System.out.println(Arrays.toString(buttonA.get(i)));
        }
        long totalPrice = 0;

        for(int i = 0; i<buttonA.size(); i++){
            //System.out.println(Arrays.toString(buttonA.get(i)));
            totalPrice += findCheapest(buttonA, buttonB, prizePos, i);
        }

        System.out.println(totalPrice);

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println(timeElapsed);

    }

    private static long findCheapest(ArrayList<int[]> a, ArrayList<int[]> b, ArrayList<int[]> pos, int i){
        int adx = a.get(i)[0]; int ady = a.get(i)[1];
        int bdx = b.get(i)[0]; int bdy = b.get(i)[1];
        long pricex = (long)pos.get(i)[0]+ Long.parseLong("10000000000000"); long pricey = (long)pos.get(i)[1] + Long.parseLong("10000000000000");

        long pressA = (bdy*pricex - bdx*pricey)/(adx*bdy-bdx*ady);
        long pressB = (-ady*pricex + adx*pricey)/(adx*bdy-bdx*ady);

        if(pressA >=0 && pressB >=0 && (-ady*pricex + adx*pricey)%(adx*bdy-bdx*ady)  ==0 && (bdy*pricex - bdx*pricey)%(adx*bdy-bdx*ady) ==0){
            System.out.println(pressA + ", " + pressB);
            return 3*pressA + pressB;
        }
        return 0;
    }
}

