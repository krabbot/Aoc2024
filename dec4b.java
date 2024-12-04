import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class dec4b {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("input4.txt");
        Scanner data = new Scanner(input); 

        char[] temp = data.nextLine().toCharArray();
        char[][] matrix = new char[140][temp.length];

        int index = 0;
        matrix[index++] = temp;

        while(data.hasNextLine()){
            matrix[index++] = data.nextLine().toCharArray();
        }


        int xmasCount = 0;

        for(int i = 0; i<matrix.length; i++){
            for(int j = 0; j<matrix[i].length; j++){
                if(xmas(matrix, i, j)) xmasCount++;
            }

        }

        System.out.println(xmasCount);


    }

    private static boolean xmas(char[][] matrix, int i, int j){
        if(masDownRight(matrix, i, j) && masDownleft(matrix, i, j+2)) return true;
        return false;
    }

    private static boolean masDownRight(char[][]matrix, int i, int j){
        if(i>matrix.length -3) return false;

        if(j<matrix[i].length-2){
            if(matrix[i][j]=='M' && matrix[i+1][j+1]=='A' && matrix[i+2][j+2]=='S') return true;

            if(matrix[i][j]=='S' && matrix[i+1][j+1]=='A' && matrix[i+2][j+2]=='M') return true;
        }
        return false;
    }

    private static boolean masDownleft(char[][]matrix, int i, int j){
        if(i>matrix.length -3) return false;

        if(j>1){
            if(matrix[i][j]=='M' && matrix[i+1][j-1]=='A' && matrix[i+2][j-2]=='S') return true;

            if(matrix[i][j]=='S' && matrix[i+1][j-1]=='A' && matrix[i+2][j-2]=='M') return true;
        }
        return false;
    }


}