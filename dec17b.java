
import java.util.ArrayList;

/*Borde fungera men gör inte det */


public class dec17b {
    public static void main(String[] args){
        
        //int A = 27575648;
        //int A = 117440;
        long index = 0;
        //int[] program = {0,3,5,4,3,0};
        //int[] program = {0,1,5,4,3,0};
        ArrayList<Integer> output = new ArrayList<Integer>();
        long A = index;
        int B = 0;
        int C = 0;

        int[] program = {2,4,1,2,7,5,4,1,1,3,5,5,0,3,3,0};  

        int pointer = 0;
        int opcode;
        int operand;

        int index2;
        boolean OK = false;
        while(!OK){
            if(index%100000000 == 0) System.out.println(index/100000000);
            pointer = 0;
            A = index;
            B = 0;
            C = 0; 
            index2=0;         

            while(pointer<program.length){
                opcode=program[pointer];
                operand = program[pointer+1];

                switch (opcode) {
                    case 0: A = adv(A, B, C, operand); break;
                    case 1: B = bxl(B, operand); break;
                    case 2: B = bst(A, B, C, operand); break;
                    case 3: pointer = jnz(A, B, C, operand, pointer); break;
                    case 4: B =  bxc(A, B, C, operand); break;
                    case 5:{
                        output.add(out(A,B,C,operand));
                        if(output.size()>program.length || program[index2]!=output.get(index2)) pointer = program.length;
                        index2++;
                        break;
                    } 
                    case 6: B = bdv(A, B, C, operand); break;
                    case 7: C = cdv(A, B, C, operand); break;
                    default: System.out.println("va"); break;
                }
                pointer +=2;
            }
            if(output.size()>10)System.out.println( index + ", " +output.toString());

            OK = false;
            if(program.length == output.size()){
                OK = true;
                for(int i = 0; i<program.length; i++){
                    if(program[i]!=output.get(i)){
                        OK = false; 
                        break;
                    }
                }
            }
            if(OK==true) System.out.println(index);
            //System.out.println(index);
            index++;
            output.clear();

        }
    }

    private static int adv(long A, int B, int C, int operand){
        long numerator = A;
        long denomiator = (long) Math.pow(2, combo(operand,A,B,C));
        return Integer.parseInt(numerator/denomiator+"");
    }

    private static int bxl(int B, int operand){
        return B^operand;
    }

    private static int bst(long A, int B, int C, int operand){
        return (int)(combo(operand, A, B, C)) & 0b111;
    }
    private static int jnz(long A, int B, int C, int operand, int pointer){
        if(A == 0) return pointer;
        return operand-2;
    }

    private static int out(long A, int B, int C, int operand){
        return ((int)(combo(operand, A, B, C)) & 0b111);
    }

    private static int bxc(long A, int B, int C, int operand){
        return B^C;
    }
    private static int bdv(long A, int B, int C, int operand){
        //return adv(A, B, C, operand);
        long numerator = A;
        long denomiator = (long) Math.pow(2, combo(operand,A,B,C));
        return Integer.parseInt(numerator/denomiator+"");
    }
    private static int cdv(long A, int B, int C, int operand){
        //return adv(A, B, C, operand);
        long numerator = A;
        long denomiator = (long) Math.pow(2, combo(operand,A,B,C));
        return Integer.parseInt(numerator/denomiator+"");
    }

    private static long combo(int operand, long A, int B, int C){
        switch(operand){
            case 4: return A;
            case 5: return B;
            case 6: return C;
            default: return operand; 
        }
    }
}