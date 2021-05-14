package edu.indiana.cs.c343;

/**
 * @author Aaron Lack, alack, C343 FA20. Last Edited: 9/2/20.
 * HammingDNADistance is problem 3 for the first problem set.
 * This class is to figure out hamming distance between two strings.
 * DNA strings set to 100 for testing purposes.
 *
 * Hamming distance is the
 * Split the two strands and put them into an array
 * Compare the arrays
 * If they have the same number, counter++
 * If they dont, reset the counter.
 * Take the max of the counter and return that number as the Hamming distance.
 */

public class HammingDNADistance {
    public static int HammingDistance(String sequenceOne, String sequenceTwo) {

        String[] splitOne = sequenceOne.split("");
        String[] splitTwo = sequenceTwo.split("");

        int hammingTracker = 0;
        int max = 0;
        for (int i = 0; i < splitOne.length; i++) {
            for (int j = 0; j < splitTwo.length; j++) {
                if (splitOne[i].equals(splitTwo[j])) {
                    hammingTracker = 0;
                }
                else {
                    hammingTracker++;
                }
                if (hammingTracker > max) {
                    max = hammingTracker;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        //Call HammingDistance Method from Class. Instantiate new Class.
        HammingDNADistance ham = new HammingDNADistance();
        //HammingDistance(sequenceOne, sequenceTwo);
    }

}
