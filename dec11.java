import java.util.ArrayList;
import java.util.Arrays;

public class dec11{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        String[] stones = new String("3028,78,973951,5146801,5,0,23533,857").split(",");

        long nrOfStones=0;
        for(String stone: stones) nrOfStones+= generatedStones(stone, 45);

        System.out.println(nrOfStones);

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println(timeElapsed);
        
    }


    private static long generatedStones(String stone, int blinks){
        long generatedStones = 0;
        String[] stonesFromStone = blink(stone);

        if(blinks == 1) return stonesFromStone.length;

        for(String s : stonesFromStone) generatedStones += generatedStones(s, blinks-1);

        return generatedStones;
    }

    
    private static String[] blink(String stone){ 
        //System.out.println(Arrays.toString(array));
        
        ArrayList<Long> newStones = new ArrayList<Long>();


        if(stone.equals("0")) newStones.add((long) 1);
        else if(stone.length()%2==0){
            newStones.add(Long.parseLong(stone.substring(0, stone.length()/2)));
            newStones.add(Long.parseLong(stone.substring(stone.length()/2, stone.length())));
        }
        else newStones.add(Long.parseLong(stone)*2024);

        String[] stones = new String[newStones.size()];

        for(int i = 0; i<stones.length; i++){
            stones[i]=newStones.get(i) + "";
        }
        return stones;

    }

}