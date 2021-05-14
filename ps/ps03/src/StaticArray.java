import java.lang.reflect.Array;
import java.util.Arrays;
public class StaticArray {
    //The meaning of static array is replacing value with null, not the use of keyworkd static in Java1!
    private Integer[] numbers;

    public StaticArray(Integer[] nums) {
        this.numbers = nums;
    }

    public int removeAt(int index) {
        int removeInt = this.numbers[index];
        this.numbers[index] = null;
        return removeInt;
    }

    @Override
    public String toString() {
        return Arrays.toString(numbers);
    }

    public static void main(String[] args) {
        Integer[] testArray = {100, 20, 69, 78, 50};
        StaticArray statArr = new StaticArray(testArray);
        System.out.println(statArr.toString());
        System.out.println(statArr.removeAt(2));
        System.out.println(statArr.toString());
        System.out.println(statArr.removeAt(0));
        System.out.println(statArr.toString());
    }
}
