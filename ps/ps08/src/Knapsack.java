public class Knapsack {
    //C343 Fall 2020
    //Problem Set 08
    //Aaron Lack, alack

    /**
     The subset sum problem is related to the knapsack problem. The subset sum problem is defined thus:
     "Given N integers A1, A2, ..., AN and an integer K, is there a group of integers that sums exactly to K?"
     Your task for this problem is to write a method implementing an algorithm that solves this problem in
     O(2N) time. Note: you may assume that all integers are non-negative (i.e. integers A1, A2, ..., AN and
     K).
     */

    //
    //Recursive knapsack problem. Returns true if a subset of the provided integers can be found (where the
    //subset adds up to the value kSum). Returns False otherwise
    //Assign the value of that particular element in knapsack to 0 to not shorten the list.
    public static boolean recursiveKnapsackSubsetSum(int[] values, int n, int kSum) {
        //Base case 1: if the sum is 0
        if (kSum == 0) {
            return true;
        }
        //Base case 2: if the array is to <= 0, then you can't have a subset since it's empty.
        if (n <= 0) {
            return false;
        }

        //You can recur by reducing the length of the list by 1 if the base cases arent true,
        //Or that on top of reducing index value by 1 as well if the first condition in or statement isn't
        // met.
        return recursiveKnapsackSubsetSum(values, n - 1, kSum) || recursiveKnapsackSubsetSum(values, n - 1,
                kSum - values[n - 1]);
    }

    //Does the same problem but in polynomial, not exponential time. Uses dynamic programming
    public static boolean dynamicKnapsackSubsetSum(int[] values, int n, int kSum) {
        //A useful data structure 2d array that compares the length of the array and the sum.
        boolean[][] dynamicProgramming = new boolean[n + 1][kSum + 1];

        //Checks for if the sum is 0
        for (int i = 0; i <= n; i++) {
            dynamicProgramming[i][0] = true;
        }

        //Checks for an empty subset
        for (int i = 1; i <= kSum; i++) {
            dynamicProgramming[0][i] = false;
        }

        //Iterate through the whole dynamicProgramming 2D array, and fills it up using a bottom-up approach.
        //We can assign the boolean dynamicProgramming[i][j] when iterating through n and kSum to be either
        // itself or disregarding the last elements.
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= kSum; j++) {
                dynamicProgramming[i][j] = dynamicProgramming[i - 1][j];
                if (j >= values[i - 1]) {
                    //Same or case as the recursive method.
                    dynamicProgramming[i][j] = dynamicProgramming[i][j] || dynamicProgramming[i - 1][j - values[i - 1]];
                }
            }
        }
        //Returns the subset of size n, and the kSum.
        return dynamicProgramming[n][kSum];
    }

    public static void main(String[] args) {
        //Test 3 different sets of integers (size 10) for both methods
        //I will do test cases for 1 true and 1 false for each method for each of the arrays.
        int arr1[] = {1,2,3,4,5,6,7,8,9,10};
        int arr2[] = {1,3,5,7,9,11,13,15,17,19,21};
        int arr3[] = {2,4,6,8,10,12,14,16,18,20};
        System.out.println("Is there a subset that sums to 15 in Arr1: " + recursiveKnapsackSubsetSum(arr1,
                arr1.length, 15)); //True
        System.out.println("Is there a subset that sums to 15 in Arr1: " + dynamicKnapsackSubsetSum(arr1,
                arr1.length, 15));  //True
        System.out.println("Is there a subset that sums to 56 in Arr1: " + recursiveKnapsackSubsetSum(arr1,
                arr1.length, 56)); //False
        System.out.println("Is there a subset that sums to 56 in Arr1: " + dynamicKnapsackSubsetSum(arr1,
                arr1.length, 56));  //False
        System.out.println("Is there a subset that sums to 16 in Arr2: " + recursiveKnapsackSubsetSum(arr2,
                arr2.length, 16)); //True
        System.out.println("Is there a subset that sums to 16 in Arr2: " + dynamicKnapsackSubsetSum(arr2,
                arr2.length, 16));  //True
        System.out.println("Is there a subset that sums to 122 in Arr2: " + recursiveKnapsackSubsetSum(arr2,
                arr2.length, 122)); //False
        System.out.println("Is there a subset that sums to 122 in Arr2: " + dynamicKnapsackSubsetSum(arr2,
                arr2.length, 122));  //False
        System.out.println("Is there a subset that sums to 30 in Arr3: " + recursiveKnapsackSubsetSum(arr3,
                arr3.length, 30));  //False
        System.out.println("Is there a subset that sums to 30 in Arr3: " + dynamicKnapsackSubsetSum(arr3,
                arr3.length, 30));  //False
        System.out.println("Is there a subset that sums to 17 in Arr3: " + recursiveKnapsackSubsetSum(arr3,
                arr3.length, 17));  //False
        System.out.println("Is there a subset that sums to 17 in Arr3: " + dynamicKnapsackSubsetSum(arr3,
                arr3.length, 17));  //False

    }
}
