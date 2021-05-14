package edu.indiana.cs.c343; /**
 * @author Aaron Lack, alack, C343 FA20. Last Edited: 9/2/20.
 * RandomChunkOfDNA is problem 3 for the first problem set.
 * Expanding off of given code and finding hamming distance between the two strings.
 * Copy and paste results into the the README file.
 */

import java.util.Random;
import static java.lang.System.out;

public class RandomChunkOfDNA {

    // generate a random DNA sequence of length n:
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


    // some client code for testing:
    public static void main(String[] args) {
        RandomChunkOfDNA rndDNAGenerator = new RandomChunkOfDNA();
        String dna;

        //Comment this out for now, we dont need it.
//        for (int i = 10; i<=1000; i = i * 10) {
//            out.println("");
//            dna = rndDNAGenerator.nextDNA(i);
//            out.println("a DNA sequence " + i + " characters long: " + dna);
//        }

        //Prints Random DNA Sequences of Size 100
        //Storing them into variables to be used in HammingDNADistance

        String sequenceOne = " ";
        String sequenceTwo = " ";
        for (int i = 1; i<=2; i++) {
            out.println("");
            dna = rndDNAGenerator.nextDNA(100);
            if(i == 1) {
                sequenceOne = dna;
            }
            else if(i == 2) {
                sequenceTwo = dna;
            }
            //out.println("DNA sequence " + i + ": " + dna);

        }
        out.println("DNA sequence 1: " + sequenceOne);
        out.println("DNA sequence 2: " + sequenceTwo);

        //Call HammingDistance Method from Class. Instantiate new Class.
        HammingDNADistance ham = new HammingDNADistance();
        out.println("Hamming Distance: " + ham.HammingDistance(sequenceOne, sequenceTwo));
    }

} // end of class RandomChunkOfDNA