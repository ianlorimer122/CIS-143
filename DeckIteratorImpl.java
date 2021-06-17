package blackjack;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import blackjack.Card.Rank;
import blackjack.Card.Suit;


public class DeckIteratorImpl implements Deck {
	public static final int NUMBER_OF_DECKS = 3;
	public LinkedList<Card> deck = new LinkedList<Card>();
	public DeckIteratorImpl() {
		this.createAndShuffleDeck();
	}
	/*
	 * Learning Objective Module 5 #6
	 * Demonstrate how to work with annotations.
	 * This file implements the Deck interface,
	 * therefore it has Override annotations for the
	 * implemented methods.
	 * @Override
		public List<Card> getDeck() {
			return this.deck;
		}
	 */
	@Override
	public List<Card> getDeck() {
		return this.deck;
	}

	@Override
	public void shuffle() {
		// TODO Auto-generated method stub
		Collections.shuffle((List<?>) deck);
	}
	/*
	@Override
	
	public boolean hasNext() {
		return deck.size() > 0;
	}
	*/

	@Override
	public Card dealCard() {
		Card card = deck.poll();
		if(card != null)
		{
			//return new Card(Suit.CLUBS, Rank._4);
			return card;
		}
		createAndShuffleDeck();
		return dealCard();
	}
	public void createAndShuffleDeck() {
		for(int num = 0;num < NUMBER_OF_DECKS;num++) {
			for(Card.Suit s : Card.Suit.values())
	    	{
	    		for(Card.Rank r : Card.Rank.values())
	    		{
	    			deck.offer(new Card(s, r));
	    		}
	    	}
		}
		this.shuffle();
	}
}
