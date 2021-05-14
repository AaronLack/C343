import java.io.ObjectStreamException;
import java.sql.SQLOutput;
import java.util.Observable;
import java.util.Random;

public class PS05Model extends Observable{
    private int width;
    private int height;
    private int[][] matrix;

    //Constructor
    public PS05Model(int width, int height) {
        this.width = width;
        this.height = height;
        this.matrix = new int[this.width][this.height];
    }

    //Should return reference to 2D array stored
    public int[][] getArray() {
        return this.matrix;
    }

    public void randomize(int matrix[][]) {
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix.length; j++) {
                matrix[i][j] = (int) (-255 + (Math.random() * 512));
            }
        }
    }

    //Make a insertion sort helper method for sortColumns and sortRows.
    //I chose insertion sort over heapsort because it's easier to implement and I understand it better than heapsort.
    //Source: https://www.geeksforgeeks.org/insertion-sort/
    public void insertionSort(int[] arr, int size) {
        int current, j, i;
        for (i = 1; i < size; i++){
            current = arr[i];
            j = i - 1;
            while (j >= 0 && arr[j] > current){
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = current;
        }
    }

    //Uses a sorting algorithm to sort the columns in ascending order
    //every column j, the elements at indices (j, 0), (j, 1), ... (j, m-1) should be sorted from smallest (at (j, 0))
    // to largest (at (j, m-1)) where m is the size of each column in the array.
    private void sortColumns(int[][] matrix) {
        for(int i = 0; i < height; i++) {
            insertionSort(matrix[i], this.matrix.length);
        }
        setChanged();
        notifyObservers();
    }

    //Uses a sorting algorithm to sort the columns in ascending order.
    //at every row i, the elements at indices (0, i), (1, i), ... (n-1, i) should be sorted from smallest (at (0, i))
    // to largest (at (n-1, i)) where n is the size of each row in the array.
    private void sortRows(int[][] matrix) {
        for(int i = 0; i < width; i++) {
            insertionSort(matrix[i], this.matrix.length);
        }
        setChanged();
        notifyObservers();
    }

    //this method should first sort each column, then sort each row in the array.
    public void sortArray1(int[][] matrix) {
        long startTime = System.nanoTime();
        sortColumns(this.matrix);
        sortRows(this.matrix);
        long endTime = System.nanoTime();
        long totalTime = (endTime - startTime);
        System.out.println(totalTime);
    }

    //this method should first sort each row, then sort each column in the array.
    public void sortArray2(int[][] matrix) {
        long startTime = System.nanoTime();
        sortRows(this.matrix);
        sortColumns(this.matrix);
        long endTime = System.nanoTime();
        long totalTime = (endTime - startTime);
        System.out.println(totalTime);
    }

    public static void main(String[] args) {
        PS05Model model = new PS05Model(1280, 1280);
        int[][] testArray = new int[model.width][model.height];
        model.getArray();
        model.randomize(testArray);
        model.sortColumns(testArray);
        model.sortRows(testArray);
        System.out.println("Run Time of SortArray1");
        model.sortArray1(testArray);
        System.out.println("Run Time of SortArray2");
        model.sortArray2(testArray);
    }
}
