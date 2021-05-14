import java.io.File;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Scanner;

public class PS07Model extends Observable {
    private int width;
    private int height;
    int [][] d;
    char[][] e;

    public PS07Model(int width, int height) {
        this.width = width;
        this.height = height;
        this.d = new int[0][0];
        this.e = new char[0][0];
    }

    //TODO
    // straight from PS06, but modified for ps07
    // Modified version: store a letter into a seperate "explanation" array of char, named e[i][j]
    // if the two compared characters are the same, store e[i][j] = ' ' (no change in distance)
    // if the character is substituted, then store e[i][j] = 'S'
    // if the character is deleted, then store e[i][j] = 'D'
    // if a character is inserted, then store e[i][j] = 'I'

    public int dist(String str1, String str2) {
        //Step 1 of problem
        int n = str1.length(); //length of str1
        int m = str2.length(); //length of str2
        this.d = new int[n+1][m+1];
        this.e = new char[n+1][m+1];
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
            d[i][0] = i;
        }

        //For string2
        for(int j = 0; j <= m; j++) {
            d[0][j] = j;
        }

        //Str1_i is ith char of string 1, and Str2_j is jth char of string 2
        for(int i = 1; i <= n; i++) {
            notifyObservers(); //Call observable class when an outer loop is complete
            char str1_i = str1.charAt(i-1);
            for(int j = 1; j <= m; j++) {
                char str2_j = str2.charAt(j-1);

                if(str1_i == str2_j) {
                    cost = 0;
                    e[i][j] = ' ';
                }

                else {
                    cost = 1;
                    //replace
                    if(n == m) {
                        e[i][j] = 'S';
                    }
                    //delete
                    if(n > m) {
                        e[i][j] = 'D';
                    }
                    //insert
                    if(m > n) {
                        e[i][j] = 'I';
                    }
                }
//                int top = d[i-1][j] + 1; //deletion
//                int diagonal = d[i-1][j-1] + cost; //subsitution
//                int left = d[i][j-1] + 1; //insertion
                int min = Math.min(Math.min(d[i-1][j]+1, d[i][j-1]+1), d[i-1][j-1]+cost);
                d[i][j] = min;
            }
            setChanged();
            notifyObservers();
        }
        return d[n][m];
    }

    public int dLine(int i)   {
        return dist(aString(),bString());
    }

    public int eLine(int i) {
        return dist(aString(),bString());
    }

    //aString() and bString() should return two string being compared by dist()
    public String aString() {
        File flatland = new File("/Users/aaronlack/Desktop/Classes/FA20/C343/C343alack/ps/ps07/src/Flatland" +
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
        return result;
    }

    public String bString() {
        File flatland2 = new File("/Users/aaronlack/Desktop/Classes/FA20/C343/C343alack/ps/ps07/src" +
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
        return result2;
    }

    public static void main(String[] args) {
        PS07Model model = new PS07Model(1280, 1280);
        System.out.println("Edit Distance = " + model.dist(model.aString(), model.bString()));
    }
}

