import java.util.ArrayList;
import java.util.Random;

/**
 * Add Comments to code later!
 */


public class MyDeckOfCards implements DeckADT {

    ArrayList<String> deck = new ArrayList<String>();

    public ArrayList<String> newDeck() {
        return deck;
    }

    @Override
    public void initialize() {
        String[] suit = {"C","S","D","H"};
        String[] numbers = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};

        for(int i = 0; i < numbers.length; i++) {
            for(int j = 0; j < suit.length; j++) {
                deck.add(numbers[i] + suit[j]);
            }
        }
    }

    @Override
    public String drawRandomCard() {
        Random rand = new Random();
        return deck.get(rand.nextInt(52));
    }

    @Override
    public String drawTopCardFromDeck() {
        return deck.get(0);
    }

    @Override
    public void shuffleDeck() {
        Random rn = new Random();
        String[] suit = {"C","S","D","H"};
        String[] numbers = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};

        ArrayList<Integer> shuffledCards = new ArrayList<Integer>();
        for(int i = 0; i < 52; i++) {
            shuffledCards.add(i);
        }

        while(shuffledCards.size() > 0) {
            for(int i = 0; i < numbers.length; i++) {
                for(int j = 0; j<suit.length; j++) {
                    int randomCard = rn.nextInt(shuffledCards.size());
                    deck.set(shuffledCards.get(randomCard),numbers[i]+suit[j]);
                    shuffledCards.remove(randomCard);
                }
            }
        }
    }

    public static void main(String[] args) {
        MyDeckOfCards newCardDeck = new MyDeckOfCards();
        System.out.print("All cards: ");
        newCardDeck.initialize();
        System.out.println(newCardDeck.newDeck());
        System.out.print("Top card: ");
        System.out.println(newCardDeck.drawTopCardFromDeck());
        System.out.print("Random card: ");
        System.out.println(newCardDeck.drawRandomCard());
        newCardDeck.shuffleDeck();
        System.out.print("Shuffled deck: ");
        System.out.println(newCardDeck.newDeck());
        System.out.print("Top card: ");
        System.out.println(newCardDeck.drawTopCardFromDeck());
        System.out.print("Random card: ");
        System.out.println(newCardDeck.drawRandomCard());



    }
}

