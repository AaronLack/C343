import java.util.Arrays;

public class Int2DArray implements Int2DArrayADT{

    //make an instance variable that is a 2D array, matrix is an easier name. 
    private int[][] matrix;

    //constructor
    public Int2DArray(int[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public int get(int numOne, int numTwo) {
        return this.matrix[numOne][numTwo];
    }

    @Override
    public int[][] set(int numOne, int numTwo, int value) {
        this.matrix[numOne][numTwo] = value;
        return this.matrix;
    }

    @Override
    public void zeroArray() {
        for(int i = 0; i < this.matrix.length; i++) {
            for(int j = 0; j < this.matrix[i].length ; j++) {
                this.matrix[i][j] = 0;
            }
        }
    }

    @Override
    public int getRow(int[][] matrix, int value) {
        int rowNum = 0;
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix.length; j++) {
                if(matrix[i][j] == value) {
                    rowNum = i;
                }
            }
        }
        return rowNum;
    }

    @Override
    public int getColumn(int[][] matrix, int value) {
        int colNum = 0;
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix.length; j++) {
                if(matrix[i][j] == value) {
                    colNum = j;
                }
            }
        }
        return colNum;
    }

    public void displayMatrix() {
        for(int[] numOne : this.matrix) {
            System.out.print("[");
            for(int numTwo : numOne) {
                System.out.print(numTwo+" ");
            }
            System.out.print("]\n");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int [][] test = {{2,3,6}, {1,4,7}, {5,8,9}};
        Int2DArray testMatrix = new Int2DArray(test);

        //Display the matrix
        testMatrix.displayMatrix();

        //Testing the getRow and getColumn methods
        System.out.println(testMatrix.getRow(test, 1));
        System.out.println(testMatrix.getColumn(test, 1));

        //Test get method
        System.out.println(testMatrix.get(2,2));
        System.out.println(testMatrix.get(1,1));
        System.out.println(testMatrix.get(0,0));

        //Test zeroArrayMethod
        testMatrix.zeroArray();
        testMatrix.displayMatrix();

        //Test set method
        testMatrix.set(0,0,7);
        testMatrix.set(0,1,2);
        testMatrix.set(0,2,5);
        testMatrix.displayMatrix();
    }
}
