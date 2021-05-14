import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;
//Those are useful for reading text files

public class LevenshtienDistance {

    //Edit Distance Method
    //Got a large amount of help implementing from
    // http://people.cs.pitt.edu/~kirk/cs1501/Pruhs/Spring2006/assignments/editdistance/Levenshtein
    // %20Distance.html
    public int editDistance(String str1, String str2) {
        //Step 1 of problem
        int n = str1.length(); //length of str1
        int m = str2.length(); //length of str2
        int distance[][] = new int[n+1][m+1]; //The matrix showing the edit distance
        int cost;


        //edge cases
        if (m == 0) {
            return m;
        }
        if(n == 0) {
            return n;
        }

        //Step 2 of problem
        //For string1
        for(int i = 0; i <= n; i++) {
            distance[i][0] = i;
        }

        //For string2
        for(int j = 0; j <= m; j++) {
            distance[0][j] = j;
        }

        //Step 3-6 of problem
        //Str1_i is ith char of string 1, and Str2_j is jth char of string 2
        for(int i = 1; i <= n; i++) {
            char str1_i = str1.charAt(i-1);
            for(int j = 1; j <= m; j++) {
                char str2_j = str2.charAt(j-1);
                if(str1_i == str2_j) {
                    cost = 0;
                }
                else {
                    cost = 1;
                }
                distance[i][j] = Math.min(Math.min(distance[i-1][j]+1, distance[i][j-1]+1),
                        distance[i-1][j-1]+cost);
            }
        }
        //Step 7: return
        return distance[n][m];
    }


    //Hamming Distance Method (see PS01)
    //I incorrectly did Hamming Distance in PSO1, here is the correct version
    //I need to use charAt(i) like in edit distance instead of splitting the strings
    public int hammingDistance(String str1, String str2) {
        int hammingDistance = 0;
        for(int i = 0; i < str1.length(); i++) {
            if(!(str1.charAt(i) == str2.charAt(i))) {
                hammingDistance++;
            }
        }
        return hammingDistance;
    }

    //Generate a random DNA sequence of length n:
    //I got this from PS01 Starter Code
    public String nextDNA(int n) {
        String lDNA = "";
        Random lRandomizer = new Random();

        for (int i = 0; i < n; i++) {
            int j = lRandomizer.nextInt(4);
            if (j == 0) lDNA += "A";
            else if (j == 1) lDNA += "T";
            else if (j == 2) lDNA += "C";
            else if (j == 3) lDNA += "G";
        }
        return lDNA;
    }

    //Error Methods, starting at 4 and seeing where this code breaks, double i each iteration with *=2.
    public void errorMethod() {
        for(int i = 4; i < 35000; i*=2) {
            //make two random dna sequences by calling nextDNA
            String dna1 = nextDNA(i);
            String dna2 = nextDNA(i);
            //Call hamming and edit distance methods
            editDistance(dna1, dna2);
            hammingDistance(dna1, dna2);
        }
    }

    //Two seperate timer methods for edit distance and hamming distance
    public void editTimerMethod(String str1, String str2) {
        long startTime = System.nanoTime();
        editDistance(str1, str2);
        long endTime = System.nanoTime();
        long totalTime = (endTime - startTime);
        System.out.println(totalTime);
    }

    public void hammingTimerMethod(String str1, String str2) {
        long startTime = System.nanoTime();
        hammingDistance(str1, str2);
        long endTime = System.nanoTime();
        long totalTime = (endTime - startTime);
        System.out.println(totalTime);
    }

    public static void main(String[] args) {
        /**
         * Read the Flatland and Flatland2 methods here
         * Compute both hamming and levenshtien distances here (seperately for files)
         * Then, do the same but for DNA sequences, see PS01 for code
         * start with String length n = 4, then keep doubling the String length n, and keep computing the two
         * distances (Hamming and Edit) between two (increasingly longer) randomly generated DNA String
         * sequences, until you reach an error at run time.
         * Then, time the methods, call the time using System.nanotime (see PS05) for methods
         * Annotate time results and error results in your README file
         */

        //I have to use relative path because my files are on my desktop
        //you can use File flatland = new File(Flatland) for grading purposes.
        //Same with File flatland2 = new File(Flatland2)
        File flatland = new File("/Users/aaronlack/Desktop/Classes/FA20/C343/C343alack/ps/ps06/src/Flatland" +
                ".txt");

        //Try and catch for null files or fileNotFound Error
        Scanner nullCheck = null;
        try {
            nullCheck = new Scanner(flatland);
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }

        String result = "";
        while(nullCheck.hasNextLine()) {
            result += nullCheck.nextLine() + " ";
        }
        System.out.println(result);

        //Same process but for Flatland2
        File flatland2 = new File("/Users/aaronlack/Desktop/Classes/FA20/C343/C343alack/ps/ps06/src" +
                "/Flatland2.txt");
        Scanner nullCheck2 = null;
        try {
            nullCheck2 = new Scanner(flatland2);
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }

        String result2 = "";
        while(nullCheck2.hasNextLine()) {
            result2 += nullCheck2.nextLine() + " ";
        }
        System.out.println(result2);
        LevenshtienDistance test = new LevenshtienDistance();
        System.out.println("Edit Distance = " + test.editDistance(result, result2));
        System.out.println("Hamming Distance = " + test.hammingDistance(result, result2));
        System.out.println("Edit Distance Time: ");
        test.editTimerMethod(result, result2);
        System.out.println("Hamming Distance Time: ");
        test.hammingTimerMethod(result, result2);
        test.errorMethod();

    }
}
