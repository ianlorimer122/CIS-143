package blackjack;

public class Dealer {
	private Hand hand;
	public Dealer() {
		hand = new Hand();
	}
	public boolean shouldHit() {
		for(int score : hand.getScore()) {
			//liberal assumption that dealer will always draw if hand
			//can be viewed with score < 17
			if(score > 17) {
				return false;
			}
		}
		return true;
	}
	public void draw(DeckIteratorImpl d) {
		
		hand.addCard(d.dealCard());
	}
	public Hand getHand() {
		return hand;
	}
}
