public interface Int2DArrayADT {
    public int get(int numOne, int numTwo);
    public int[][] set(int numOne, int numTwo, int value);
    public void zeroArray();
    public int getRow(int[][] matrix, int value);
    public int getColumn(int[][] matrix, int value);
}