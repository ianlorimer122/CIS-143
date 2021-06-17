package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Hand {
	private List<Card> h;
	private boolean done;
	private int bet;
	private Player player;
	private HAND_STATE state;
	public Hand(Player player) {
		h = new ArrayList<Card>();
		setDone(false);
		setBet(0);
		this.player = player;
	}
	public Hand() {
		h = new ArrayList<Card>();
		setDone(false);
		setBet(0);
		this.player = null;
	}
	public int getBet() {
		return bet;
	}
	public void setBet(int bet) {
		this.bet = bet;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	public void addCard(Card card) {
		h.add(card);
	}
	public List<Integer> getScore() {
		List<int[]> scores = new ArrayList<>();
		List<Integer> result = new ArrayList<>();
		int aceCount = 0;
		for(Card c : h) {
			scores.add(c.getValue());
			if(c.getRank() == Card.Rank.ACE) {
				aceCount += 1;
			}
		}
		int singular = 0;
		for(int[] s : scores) {
			if(s.length != 0) {
				singular += s[0];
			}
		}
		//If one ace, then there's two results: singular is 1 or singular is 11.
		//If two aces, then there are two results: singular+{2 12}
		//If three aces, then singular+{3, 13}
		//If four aces, then singular+{4, 14}
		switch(aceCount) {
		case 0:
			result.add(singular);
			break;
		case 1:
			result.add(singular + 0);
			result.add(singular + 10);
			break;
		case 2:
			result.add(singular + 1);
			result.add(singular + 11);
			break;
		case 3:
			result.add(singular + 2);
			result.add(singular + 12);
			break;
		case 4:
			result.add(singular + 3);
			result.add(singular + 13);
			break;
		}
		return result;
	}
	public Hand createSplitHand(Player player) {
		// TODO Auto-generated method stub
		Hand splitHand = new Hand(player);
		//splitHand.addCard(h.remove(0));
		return splitHand;
	}
	public Card getCard(int index) {
		return h.get(index);
	}
	public void remove(Card card2) {
		h.remove(card2);
	}
	public List<Card> getList() {
		// TODO Auto-generated method stub
		return this.h;
	}
	public void draw(DeckIteratorImpl deck) {
		this.addCard(deck.dealCard());
	}
	public Player getPlayer() {
		// TODO Auto-generated method stub
		return player;
	}
	public void setState(HAND_STATE state) {
		// TODO Auto-generated method stub
		this.state = state;
	}
	public HAND_STATE getState() {
		return state;
	}
}