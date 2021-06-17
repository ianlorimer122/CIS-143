package blackjack;

public class Card {
	/**
     * Obvious.
     */
    enum Suit {
        HEARTS,
        CLUBS,
        DIAMONDS,
        SPADES
    }

    /**
     * Obvious.
     */
    enum Rank {
        KING,
        QUEEN,
        JACK,
        _10,
        _9,
        _8,
        _7,
        _6,
        _5,
        _4,
        _3,
        _2,
       ACE
    }

    /**
     * Obvious.
     */
    private Suit suit;

    /**
     * Obvious.
     */
    private Rank rank;
    
    private int value;

    /**
     * Const.
     *
     * @param suit The SUITE of the card
     * @param value The VALUE of the card.
     */
    public Card(final Suit suit, final Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Obvious.
     */
    public Rank getRank() {
        return rank;
    }


    /**
     * Obvious.
     */
    public boolean equals(final Card card) {
        return (card.rank == this.rank);
    }
    @Override
    public String toString() {
    	String myRank = (rank.name().charAt(0) == '_') ?
    			rank.name().substring(1) : rank.name();
        return myRank + "_" + suit.name();
    }
    
    public int[] getValue() {
    	String name = rank.name();
    	if(name.charAt(0) == '_') {
    		int[] value = {Integer.parseInt(name.substring(1))};
    		return value;
    	}
    	if(name.equals("ACE")) {
    		int[] value = {1, 11};
    		return value;
    	}
    	int[] value = {10};
    	return value;
    }
    public boolean equalRank(Card other) {
    	return this.rank == other.rank;
    }
}
