public interface DeckADT {
    //  create a full set of cards (with 52 cards; no Jokers)
    public void initialize();

    //  draw a card, and return the card as string. for example "2S" (2 of Spades)
    //  using single-letter representations for suits:
    //  S (spades), C (clubs), H (hearts) and D (diamonds)
    public String drawRandomCard();

    //Adding two more methods to the abstract type
    public String drawTopCardFromDeck();

    public void shuffleDeck();
}

