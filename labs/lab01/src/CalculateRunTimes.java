import java.util.Random;

public class CalculateRunTimes {
    public void countInstructions(int n) {
        //Example 2
        //Instruction counter is the number of times the program runs
        //I have a counter variable to keep track of this.
        int counter = 0;
        int sumTwo = 0;
        counter++;
        for (int i = 1; i <= n; i++) {
            counter++;
            for (int j = 1; j <= n; j++) {
                counter++;
                sumTwo++;
                counter++;
            }
        }
        System.out.println("Example 2: Size = " + n + "  Instruction Counter = " + counter);

        //Example 7
        //Again, a tracker is needed for the instruction counter.
        int tracker = 0;
        int[] a = new int [1001];
        int[] b = new int[1001];
        Random randInt = new Random();
        //Puts random values into the arrays.
        for(int i = 0; i < 1000; i++){
            a[i] = randInt.nextInt();
            b[i] = randInt.nextInt();
        }

        //Constructs 2D array and fills with random value 1-1000.
        int [][] d = new int [1001][1001];
        for(int i = 0; i < 1000; i++){
            for(int j = 0; j < 1000; j++){
                d[i][j] = randInt.nextInt();
            }
        }

        d[0][0] = 0;
        int c;
        tracker++;
        for(int i = 1; i <= n; i++)  {
            tracker++;
            for(int j = 1;  j <= n; j ++) {
                tracker++;
                if (a[i] == b[j]) {
                    tracker++;
                    c = 0;
                    tracker++;
                }
                else {
                    tracker++;
                    c = 1;
                    tracker++;
                }
                //Using the minimum value
                int min = Math.min(d[i-1][j] + 1, d[i][j-1] + 1);
                tracker++;
                d[i][j] = Math.min(min, d[i-1][j-1] + c);
            }
        }
        System.out.println("Example 7: Size = " + n + "  Instruction Counter = " + tracker);

    }



}
