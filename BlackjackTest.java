package blackjack;

import java.util.*;

public class BlackjackTest {

    public static void main(String[] args) {
        BlackjackTest deckTest = new BlackjackTest();
        deckTest.checkForNull();
        deckTest.testShuffle();
        deckTest.testDeal();
        deckTest.numberOfDecks();
        deckTest.getPlayers();
        deckTest.testScores();
    }
    public void checkForNull() {
    	DeckIteratorImpl deck = new DeckIteratorImpl();
    	if(deck.getDeck() == null) {
    		throw new RuntimeException("Deck is null!");
    	}
    	System.out.println(">> checkForNull passed");
    }
    public void testShuffle() {
    	DeckIteratorImpl deck = new DeckIteratorImpl();
    	Stack<Card> deckcp = new Stack<Card>();
    	if(deckcp.equals(deck)) {
    		throw new RuntimeException("Deck can't be shuffled!");
    	}
    	System.out.println(">> testShuffle passed");
    }
    public void testDeal() {
    	DeckIteratorImpl deck = new DeckIteratorImpl();
    	if(deck.dealCard() == null) {
    		throw new RuntimeException("Can't deal a card!");
    	}
    	System.out.println(">> testDeal passed");
    }
    public void numberOfDecks() {
    	DeckIteratorImpl deck = new DeckIteratorImpl();
    	if(deck.deck.size() != 156) {
    		throw new RuntimeException("There aren't 156 cards in the 3 decks!");
    	}
    	System.out.println(">> numberOfDecks passed");
    }
    public void getPlayers() {
    	BlackjackGame game = new BlackjackGame();
    	game.players = new ArrayList<>();
    	BlackjackGame.players.add(new Player("Ian", 500, 100));
    	if(game.players == null) {
    		throw new RuntimeException("No players found!");
    	}
    	System.out.println(">> getPlayers passed");
    }
    public void testScores() {
    	Hand hand = new Hand();
    	List<Integer> scores = hand.getScore();
    	if(scores == null) {
    		throw new RuntimeException("No scores found!");
    	}
    	System.out.println(">> testScores passed");
    }
}