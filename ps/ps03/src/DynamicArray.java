import java.util.Arrays;

public class DynamicArray {

    private Integer[] numbers;

    public DynamicArray(Integer[] nums) {
        this.numbers = nums;
    }
    //This dynamic method shrinks the length of the list, so n-1
    public int removeAt(int index) {
        int removeInt = this.numbers[index];
        //Create a new Integer array
        Integer[] shrinkArray = new Integer[this.numbers.length-1];
        //The element in the array is the length-1, which is the condition. This is if 0 is the index
        for(int i = 0; i < this.numbers.length-1; i++) {
            shrinkArray[i] = this.numbers[i+1];
        }
        //This is if 0 is not the index
        if(index != 0) {
            for (int i = 0; i < index; i++) {
                shrinkArray[i] = this.numbers[i];
            }
        }
        this.numbers = shrinkArray;
        return removeInt;
    }

    @Override
    public String toString() {
        return Arrays.toString(numbers);
    }

    public static void main(String[] args) {
        Integer[] testArray = {100, 20, 69, 78, 50};
        DynamicArray dyArr = new DynamicArray(testArray);
        System.out.println(dyArr.toString());
        System.out.println(dyArr.removeAt(1));
        System.out.println(dyArr.toString());
        System.out.println(dyArr.removeAt(0));
        System.out.println(dyArr.toString());

    }
}
