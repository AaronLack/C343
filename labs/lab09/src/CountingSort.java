import java.util.Arrays; //Imports

public class CountingSort { //Start class
    //Aaron Lack alack
    //C343 FA20 Lab 9

    //Directions:
    /**
     Implement the following algorithm:

     ***input***:
     A is an unsorted array of n integer keys
     (and k is the largest value of all keys in A)
     A may contain more than one entry for any given key value

     ***output***:
     C will be a sorted array of  n  integer keys (we can preserve the input array A)

     ***intermediate array***:
     B is an array of  k  integer counters, initially all set to 0
     (B is constructed as histogram in the first stage,
     and it is rebuilt as a lookup table in the second stage,
     for use in the third stage of the algorithm)

     // first stage:
     // count the number of records for each key value,
     //   saving the total counts to the array B
     //   (note: B is constructed as histogram )

     // second stage:
     // for this algorithm to handle entire records (not just integer keys)
     //   for each key that was found in A, calculate
     //   its starting index in the output array C,
     //   and store the calculated starting index in B
     //   ( B is thus repurposed to be used in the third stage
     //          as a lookup table for the array C )

     // third stage:
     //   use the information in the lookup table array B
     //   to place the sorted items from A in a new array C:
     //   (note: the only elements used in B
     //        are those that correspond to input keys in A)

     Note: before calling the actual counting sort implementation, your countingSort() method should find the
     maximum value present in the input array, in order to determine the size of the intermediate array. If
     the maximum value present in the input array exceeds the "maximum working key", your implementation
     should print a warning message, but it should run the sorting algorithm nevertheless, to show that it
     actually stops working correctly.
     */

    //Counting sort method: returns an integer array and takes in an integer array
    public static int[] countingSort(int[] a) { //start countingSort method
        //Initialize variables
        int n = a.length; //n is amount of unique values
        int [] b = new int[100]; //b is the intermediate array
        int k = b.length; //k is maxKeyValue which is length of intermediate array
        int[] c = new int[n] ; //c is the output final array

        //This initializes the intermediate array to 0, using pre increment
        for(int i = 0; i < 100; ++i) {
            b[i] = 0; //sets the intermediate array to 0
        }

        //This for loop stores the count of each positive integer for the size of original array a
        for(int i = 0; i < n; i++) {
            ++b[a[i]]; //pre-increment the intermediate array of the original array at index i
        }

        //This for loop changes the intermediate array b[i] so that it now equals the actual position in
        // output array c.
        for(int i = 1; i <= 99; ++i) { //We have to start i at one and set it <= 99 so we don't get an
            // index out of bounds error.
            b[i] += b[i-1]; //Moving the index of the intermediate array one to the left to match output
            // array c.
        }

        //Builds the output character array
        for(int i = n-1; i >= 0; i--) { //we decrement i because we work in reverse order, as in lecture.
            c[b[a[i]]-1] = a[i]; //assigning the output array to a value from the index array.
            --b[a[i]]; //Decreasing the count in the intermediate array.
        }

        //Copy the output array to original array so that the output array is sorted.
        for(int i = 0; i < n; ++i) {
            a[i] = c[i]; //Assigns the
        }
        return c; //Returns the output array
    } //End counting sort method.

    //A method to fill my array with random values 1-100. returns nothing
    public static void randomizeArray(int[] arr) { //start randomize array method
        for(int i = 0; i < arr.length; i++) { //loop through the input array
            arr[i] = (int) (Math.random() * 100); //fill up input array with random values 1-100.
        }
    } //end randomize array method.

    //failRandomizeArray method that fills values 1-300 in input array.
    //This allows the program to generate a value larger than max key value, causing the countingSort
    //method to fail by having a value greater than 100 to be out of bounds for the intermediate array
    //length 100.
    public static void failRandomizeArray(int[] arr) { //start fail randomize array method
        for (int i = 0; i <= arr.length-1; i++) { //loop through the input array
            arr[i] = (int) (Math.random() * 300); //fill up input array with random values 1-300.
        }
        System.out.println("WARNING: The Max Key Value is greater than the Max Value"); //warning message
    } //end fail randomize array method.

    //A method to find the max element in original array, which will also be the size of the intermediate
    // array for the maxKeyValue
    public static int maxElement(int[] arr) { // start maxElement method
        int max = arr[0]; //Sets max element to first position in input array
        for(int i = 1; i < arr.length; i++) { //loops through the input array
            if(arr[i] > max) { //if an element found in the input array is larger then initialized max,
                max = arr[i]; //set the max to the new largest element in array
            }
        }
        return max; //returns the max element in the array.
    } //end maxElement method

    public static void main(String[] args) { //Start main method
        int[] arr = new int[10]; //Initialize the array

        //In all the test cases, named it test case #
        //Called the randomize array method an pass and the array arr
        //I print the original array calling Arrays.toString(arr)
        //Then I call the maxElement method to show the length of the intermediate array
        //And print the sorted array by calling my countingSort method and Arrays.toString to display.

        System.out.println("Test Case 1"); //print statement test number
        randomizeArray(arr); //call randomizeArray
        System.out.println("Original Array: " + Arrays.toString(arr)); //prints original array a
        System.out.println("Max Key Value in Original Array: " + maxElement(arr)); //finds max element
        System.out.println("Sorted Array: " + Arrays.toString(countingSort(arr))); //prints output array c

        System.out.println(); //prints a space for nicer output

        System.out.println("Test Case 2"); //print statement test number
        randomizeArray(arr); //call randomizeArray
        System.out.println("Original Array: " + Arrays.toString(arr)); //prints original array a
        System.out.println("Max Key Value in Original Array: " + maxElement(arr)); //finds max element
        System.out.println("Sorted Array: " + Arrays.toString(countingSort(arr))); //prints output array c

        System.out.println(); //prints a space for nicer output

        System.out.println("Test Case 3"); //print statement test number
        randomizeArray(arr); //call randomizeArray
        System.out.println("Original Array: " + Arrays.toString(arr)); //prints original array a
        System.out.println("Max Key Value in Original Array: " + maxElement(arr)); //finds max element
        System.out.println("Sorted Array: " + Arrays.toString(countingSort(arr))); //prints output array c

        System.out.println(); //prints a space for nicer output

        System.out.println("Test Case 4"); //print statement test number
        randomizeArray(arr); //call randomizeArray
        System.out.println("Original Array: " + Arrays.toString(arr)); //prints original array a
        System.out.println("Max Key Value in Original Array: " + maxElement(arr)); //finds max element
        System.out.println("Sorted Array: " + Arrays.toString(countingSort(arr))); //prints output array c

        System.out.println(); //prints a space for nicer output

        System.out.println("Test Case 5"); //print statement test number
        randomizeArray(arr); //call randomizeArray
        System.out.println("Original Array: " + Arrays.toString(arr)); //prints original array a
        System.out.println("Max Key Value in Original Array: " + maxElement(arr)); //finds max element
        System.out.println("Sorted Array: " + Arrays.toString(countingSort(arr))); //prints output array c

        System.out.println(); //prints a space for nicer output

        //Same process as the last 5, but I call the failRandomArray method so I get an error message.
        //I commented out the fail test case.
        //System.out.println("Test Case 6 (Fail Test)"); //print statement test number
        //failRandomizeArray(arr); //call failRandomizeArray
        //System.out.println("Original array: " + Arrays.toString(arr)); //prints original array a
        //System.out.println("Max Key Value in Original Array: " + maxElement(arr)); //finds max element
        //System.out.println("Sorted array: " + Arrays.toString(countingSort(arr))); //prints output array c

    } //End main method
} //End counting sort class
