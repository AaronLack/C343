public class TheRunTimes {
    public static void main(String[] args) {
        //This is the class where I make an instance of the CalculateRunTimes class
        //And pass in test cases 10,100,1000, and 10000.
        CalculateRunTimes test = new CalculateRunTimes();
        test.countInstructions(10);
        test.countInstructions(100);
        test.countInstructions(1000);
        
    }
}
