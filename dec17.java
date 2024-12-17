import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/*Snyggt */


public class dec17 {
    public static void main(String[] args) throws FileNotFoundException {

        //int A = 27575648;
        int A = 117440;
        int B = 0;
        int C = 0;

        //int[] program = {2,4,1,2,7,5,4,1,1,3,5,5,0,3,3,0};
        int[] program = {0,3,5,4,3,0};

        int pointer = 0;
        int opcode;
        int operand;

        while(pointer<program.length){
            opcode=program[pointer];
            operand = program[pointer+1];

            switch (opcode) {
                case 0: A = adv(A, B, C, operand); break;
                case 1: B = bxl(B, operand); break;
                case 2: C = B = bst(A, B, C, operand); break;
                case 3: pointer = jnz(A, B, C, operand, pointer); break;
                case 4: B =  bxc(A, B, C, operand); break;
                case 5: System.out.print(out(A,B,C,operand) + ","); break;
                case 6: B = bdv(A, B, C, operand); break;
                case 7: C = cdv(A, B, C, operand); break;
            }

            pointer +=2;
        }
    }

    private static int adv(int A, int B, int C, int operand){
        int numerator = A;
        int denomiator = (int) Math.pow(2, combo(operand,A,B,C));
        return numerator/denomiator;
    }

    private static int bxl(int B, int operand){
        return B^operand;
    }

    private static int bst(int A, int B, int C, int operand){
        return combo(operand, A, B, C)%8;
    }
    private static int jnz(int A, int B, int C, int operand, int pointer){
        if(A == 0) return pointer;
        return operand-2;
    }

    private static int out(int A, int B, int C, int operand){
        return combo(operand, A, B, C)%8;
    }

    private static int bxc(int A, int B, int C, int operand){
        return B^C;
    }
    private static int bdv(int A, int B, int C, int operand){
        return adv(A, B, C, operand);
    }
    private static int cdv(int A, int B, int C, int operand){
        return adv(A, B, C, operand);
    }

    private static int combo(int operand, int A, int B, int C){
        switch(operand){
            case 4: return A;
            case 5: return B;
            case 6: return C;
            default: return operand; 
        }
    }
}