import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;



/*Till slut */

public class dec11b{

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        String[] stones = new String("3028,78,973951,5146801,5,0,23533,857").split(",");

        HashMap<Integer, Long> map = generateMap();

        long nrOfStones=0;
        for(String stone: stones) nrOfStones+= generatedStones(map, stone, 75);

        System.out.println(nrOfStones);

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println(timeElapsed);
        
    }

    private static HashMap<Integer, Long> generateMap(){
        HashMap<Integer, Long> map = new HashMap<Integer, Long>();

        for(int i = 2; i<75; i++){
            for(int j = 0; j<10; j++){
                map.put(j | (i<<4), generatedStones(map, j + "", i));
            }
        }
        return map;
    }


    private static long generatedStones(HashMap<Integer, Long> map, String stone, int blinks){

        if(stone.length() < 2 && map.containsKey(Integer.parseInt(stone)| (blinks<<4))){
            //System.out.println(map.get(Integer.parseInt(stone)| (blinks<<4))); 
            return map.get(Integer.parseInt(stone)| (blinks<<4));   
        }

        long generatedStones = 0;
        String[] stonesFromStone = blink(stone);

        if(blinks == 1) return stonesFromStone.length;

        for(String s : stonesFromStone) generatedStones += generatedStones(map, s, blinks-1);

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