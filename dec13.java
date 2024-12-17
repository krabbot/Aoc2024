import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class dec13 {
    public static void main(String[] args) throws FileNotFoundException {

        File input = new File("input13.txt");
        Scanner data = new Scanner(input);

        ArrayList<int[]> buttonA = new ArrayList<int[]>();
        ArrayList<int[]> buttonB = new ArrayList<int[]>();
        ArrayList<int[]> prizePos = new ArrayList<int[]>();

        

        String s[];

        int index = 0;
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
            System.out.println(Arrays.toString(buttonA.get(i)));
        }
        long totalPrice = 0;

        for(int i = 0; i<buttonA.size(); i++){
            //System.out.println(Arrays.toString(buttonA.get(i)));
            totalPrice += findCheapest(buttonA, buttonB, prizePos, i);
        }

        System.out.println(totalPrice);

    

    }

    private static long findCheapest(ArrayList<int[]> a, ArrayList<int[]> b, ArrayList<int[]> pos, int i){
        int adx = a.get(i)[0]; int ady = a.get(i)[1];
        int bdx = b.get(i)[0]; int bdy = b.get(i)[1];
        int pricex = pos.get(i)[0]; int pricey = pos.get(i)[1];
        //System.out.println(adx+ ady+ bdx+ bdy+ pricex+ pricey);

        long cheapest = 0;

        int x = 0; int y = 0; 

        long price; 
        for(int j = 0; j<100; j++){
            price=0;
            x= j*adx; y= j*ady;
            price += 3*j;

            if((pricex-x) % bdx == 0 && (pricey - y)%bdy == 0 && (pricex-x) / bdx == (pricey - y)/bdy && (pricex-x)>0){
                price += (pricex-x) / bdx;
                //System.out.println((pricex-x) / bdx);
                if(cheapest == 0) cheapest = price;
                else if(price < cheapest) cheapest = price;
            }
        }

        //System.out.println(cheapest);
        return cheapest;
    }
}
