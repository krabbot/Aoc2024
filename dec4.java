import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class dec4 {
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
                xmasCount= xmasCount + xmasDown(matrix, i, j)+xmasUp(matrix, i, j) + side(matrix, i, j);
                System.out.println(xmasCount);
            }

        }

        System.out.println(xmasCount);



    }

    private static int xmasDown(char[][]matrix, int i, int j){
        if(i>matrix.length -4) return 0;

        int count = 0;
        //Down
        if(matrix[i][j]=='X' && matrix[i+1][j]=='M' && matrix[i+2][j]=='A' && matrix[i+3][j]=='S') count++;

        //left
        if(j>=3){
            if(matrix[i][j]=='X' && matrix[i+1][j-1]=='M' && matrix[i+2][j-2]=='A' && matrix[i+3][j-3]=='S') count++;
        }

        //right
        if(j<matrix[i].length-3){
            if(matrix[i][j]=='X' && matrix[i+1][j+1]=='M' && matrix[i+2][j+2]=='A' && matrix[i+3][j+3]=='S') count++;
        }
        return count;
    }

    private static int xmasUp(char[][]matrix, int i, int j){
        if(i< 3) return 0;

        System.out.println(i);
        int count = 0;
        //Up
        if(matrix[i][j]=='X' && matrix[i-1][j]=='M' && matrix[i-2][j]=='A' && matrix[i-3][j]=='S') count++;

        //left
        if(j>=3){
            if(matrix[i][j]=='X' && matrix[i-1][j-1]=='M' && matrix[i-2][j-2]=='A' && matrix[i-3][j-3]=='S') count++;
        }

        //right
        if(j<matrix[i].length-3){
            if(matrix[i][j]=='X' && matrix[i-1][j+1]=='M' && matrix[i-2][j+2]=='A' && matrix[i-3][j+3]=='S') count++;
        }
        return count;
    }

    private static int side(char[][]matrix, int i, int j){

        int count = 0;
        //left
        if(j>=3){
            if(matrix[i][j]=='X' && matrix[i][j-1]=='M' && matrix[i][j-2]=='A' && matrix[i][j-3]=='S') count++;
        }

        //right
        if(j<matrix[i].length-3){
            if(matrix[i][j]=='X' && matrix[i][j+1]=='M' && matrix[i][j+2]=='A' && matrix[i][j+3]=='S') count++;
        }
        return count;
    }
}

