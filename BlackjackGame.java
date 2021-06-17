package blackjack;

import java.util.*;
import java.util.stream.Collectors;
public class BlackjackGame {
	static DeckIteratorImpl deck;
	static List<Player> players;
	static Scanner scan = new Scanner(System.in); 
	static Dealer dealer = new Dealer();
	static final Integer TWENTY_ONE = 21;
	static final String[] yn = {"y", "n"};
	public static void main(String[] args) {
		deck = new DeckIteratorImpl();
		//get players from UI: name and bank
		getPlayers();
		playGame();
	}
	public static void getPlayers() {
		// declare the list
		/*
		 * Learning Objective Module 3 #9
		 * Demonstrate how to work with an ArrayList.
		 * The array list keeps track of the players.
		 * players = new ArrayList<>();
		 */
		players = new ArrayList<>();
		int bank = 0;
		int bet = 0;
		boolean done;
		boolean finished;
		do {
			System.out.print("\nEnter player name or stop: ");
			String name = scan.nextLine();
			if(name.toLowerCase().equals("stop")) {
				break;
			}
			done = false;
			while(!done) {
				/*
				 * Learning objective Module 1 #4: write code to throw a specific exception
				 * This throws a NumberFormatException when user enters
				 * anything but an integer.
				 * try {
					System.out.print("Enter bank for " + name + ": ");
					bank = Integer.parseInt(scan.nextLine());
					done = true;
				}
				catch(NumberFormatException e) {
					System.out.println("Bank must be an integer.");
				}
				*/
				try {
					System.out.print("Enter bank for " + name + ": ");
					bank = Integer.parseInt(scan.nextLine());
					done = true;
				}
				catch(NumberFormatException e) {
					System.out.println("Bank must be an integer.");
				}
			}
			finished = false;
			while(!finished) {
				try {
					System.out.println("Place your bet: ");
					bet = Integer.parseInt(scan.nextLine());
					finished = true;
				}
				catch(NumberFormatException e) {
					System.out.println("Bet must be an integer.");
				}
			}
			/*
			 * Learning Objective Module 1 #3
			 * Write a method that manipulates an array.
			 * This command add elements (name, bet, and bank) 
			 * to an ArrayList called players
			 * players.add(new Player(name, bank, bet));
			 */
			players.add(new Player(name, bank, bet));
		} while(players.size() < 2);
		// Prompt and get user name, or stop
	}
	private static void playGame() {
		// get bets
		// deal cards
		
		for(Player p : players) {
			p.placeBet(scan);
			p.getHand().draw(deck);
				//System.out.println(p.getHand().getScore());
			//System.out.println(deck.getDeck());
		}
		dealer.draw(deck);
		for(Player p : players) {
			p.getHand().draw(deck);
			//System.out.println(p.getHand().getScore());
		//System.out.println(deck.getDeck());
	    }
		dealer.draw(deck);
		List<Player> players21 = getPlayers21();
		// compute the list of other active players
		//player interaction of those players
		while(anyPlayerActive()) {
			// Loop though active players, each player plays
			// get bets
			playerInteraction();
			// Dealer plays
			HAND_STATE state;
			do {
				state = dealerInteraction();
			}while(state == HAND_STATE.CONTINUE);
			// Pay-Offs
			payOffs(state);
			// Clear table
		}
		// Report statistics
	}
	private static void payOffs(HAND_STATE dealerState) {
		//if(dealerState == HAND_STATE.DONE) {
		for(Player p : players) {
			// call a function
			payOff(p, p.getHand(), false, dealerState);
			if(p.hasSplit()) {
				payOff(p, p.getSplitHand(), true, dealerState);
			}
		}
		//}
	}
	private static void payOff(Player p, Hand hand, boolean isSplit, HAND_STATE dealerState) {
		String handName = isSplit ? "Split Hand" : "Main Hand";
		// both busted or both 21
		HAND_STATE handState = hand.getState();
		if((dealerState == HAND_STATE.BUSTED && handState == HAND_STATE.BUSTED) || 
				(dealerState == HAND_STATE.TWENTY_ONE && handState == HAND_STATE.TWENTY_ONE)) {
			// return bets to players
			System.out.println("The round is a draw.");
			int bank = p.getBank();
			p.setBank(bank + hand.getBet());
			System.out.println(p.getName() + ": " + handName + " Bet returned to bank(" + p.getBank() + ").");
			System.out.println("Bank: " + p.getBank());
			return;
		}
		if(dealerState == HAND_STATE.BUSTED) {
			// return bets to players
			System.out.println("Dealer is busted.");
			int bank = p.getBank();
			p.setBank(bank + 2 * hand.getBet());
			System.out.println(p.getName() + ": " + handName + "\nDealer pays the bet amount.");
			System.out.println("Bank: " + p.getBank());
			return;
		}
		if(handState == HAND_STATE.BUSTED) {
			// this hand loses the round
				System.out.println(p.getName() + "'s hand is busted.");
				System.out.println(p.getName() + ": " + handName + " bank is " + p.getBank() + ".");
				return;
		}
		Integer highScorePlayer = getHigh(p.getHand());
		Integer highScoreDealer = getHigh(dealer.getHand());
		if(highScoreDealer == highScorePlayer) {
			// return bet to player
			// TODO: print out
			p.setBank(p.getBank() + (isSplit ? p.getSplitBet() : p.getBet()));
			System.out.println("Scores are tied.");
			System.out.println(p.getName() + ": " + handName + " bank is " + p.getBank() + ".");
		}
		else if (highScorePlayer > highScoreDealer) {
			// player wins
			// TODO: print out
			p.setBank(p.getBank() + 2 * (isSplit ? p.getSplitBet() : p.getBet()));
			System.out.println(p.getName() + "'s hand wins.");
			System.out.println(p.getName() + ": " + handName);
			System.out.println("The bet amount was " + p.getBet());
			System.out.println("The bank is increased by " + (2 * p.getBet()) + ".");
			System.out.println(p.getName() + "'s bank is " + p.getBank() + ".");
		}
		else {
			// dealer wins
			// TODO: print out
			System.out.println("Dealer wins.");
			System.out.println(p.getName() + "'s bank is " + p.getBank() + ".");
		}
	}
	private static Integer getHigh(Hand hand) {
		List<Integer> scores = hand.getScore();
		if(scores.size() == 0) {
			return 0;
		}
		return scores.stream()
				.filter(s -> s < 22)
				.max(Integer::compare)
				.get();
	}
	private static HAND_STATE dealerInteraction() {
		Hand dHand = dealer.getHand();
		System.out.println("Dealer's Hand: " + dHand.getList());
		if(dealer.shouldHit()) {
			dealer.draw(deck);
			System.out.println("Dealer's Hand: " + dHand.getList());
			List<Integer> scores = scoresLessThan22(dHand.getScore());
			System.out.println("Dealer score: " + scores);
			if(scores.isEmpty()) {
				System.out.println("Dealer is busted.");
				
				return HAND_STATE.BUSTED;
			}
			else {
				return HAND_STATE.CONTINUE;
			}
		}
		if(dHand.getScore().contains(21)) {
			return HAND_STATE.TWENTY_ONE;
		}
		return HAND_STATE.DONE;
	}
	private static List<Integer> scoresLessThan22(List<Integer> list) {
		/*
		 * Learning Objective Module 6 #6
		 * Java code that uses an iterator to move
		 * through a collection
		 * This collector finds all scores that
		 * are less than 22.
		 * return list.stream()
		.filter(i -> i<22)
		.collect(Collectors.toList());
		 */
		return list.stream()
		.filter(i -> i<22)
		.collect(Collectors.toList());
	}
	private static List<Player> getPlayers21() {
		// get list of active players with scores of 21
		List<Player> active21 = new ArrayList<>();
		for(Player p : getActivePlayers()) {
			if(p.getHand().getScore().contains(TWENTY_ONE)) {
				active21.add(p);
			}
		}
		return active21;
	}
	private static List<Player> getPlayersNot21() {
		// get list of active players that don't have scores of 21
		List<Player> activeNot21 = new ArrayList<>();
		for(Player p : getActivePlayers()) {
			if(!p.getHand().getScore().contains(TWENTY_ONE)) {
				activeNot21.add(p);
			}
		}
		return activeNot21;
	}
	private static void playersWith21() {
		// check if the dealer has 10 or ace showing
		// TODO dealer shows cards
		//Card c = dealer.getHand().getCard(0);
		//if(c.getRank() == Card.Rank.ACE || c.getValue()[0] == 10) {
		if(dealer.getHand().getScore().contains(TWENTY_ONE)) {
			// Dealer has 21
			dealerHas21();
		}
		//}
		else {
			// Players have 21, dealer does not
			for(Player p : getActivePlayers()) {
				if(p.getHand().getScore().contains(TWENTY_ONE)) {
					// Player gets their bet back plus 2X their bet
					p.setBank(p.getBank() + p.getBet() * 3);
					p.setBet(0);
					p.setDone(true);
				}
			}
		}
	}
	private static void dealerHas21() {
		// TODO Auto-generated method stub
		for(Player p : getActivePlayers()) {
			if(p.getHand().getScore().contains(TWENTY_ONE)) {
				p.setBank(p.getBank() + p.getBet());
				p.setBet(0);
			}
			p.setDone(true);
			// otherwise, all active players lose bets
		}
	}
	
	private static void playerInteraction() {
		for(Player p : getActivePlayers()) {
			if(!p.isDone()) {
				for(Hand h : p.getHands()) {
					System.out.println("\n-----------------\nPlayer " + p.getName());
					if(p.isStartOfGame()) {
						Card card1 = h.getCard(0);
						Card card2 = h.getCard(1);
						if(card1.equalRank(card2)) {
							optionalSplit(p, card1, card2);
						}
						else {
							List<Integer> scores = p.getHand().getScore();
							if(scores.contains(9) || scores.contains(10) || scores.contains(11)) {
								optionalDoubleDown(p);
							}
						}
					}
					if(p.isDone()) {
						break;
					}
					mainPlayLoop(p);
				}
			}
		}
	}
	private static void mainPlayLoop(Player p) {
		// TODO handle splits
		p.setStartOfGame(false);
		if(!p.isDone()) {
			if(!p.getHand().isDone()) {
				processHand(p.getHand(), false);
			}
			if(p.hasSplit()) {
				
				if(!p.getSplitHand().isDone()) {
					processHand(p.getSplitHand(), true);
				}
				else {
					if(p.getHand().isDone()) {
						p.setDone(true);
					}
				}
			}
			else {
				if(p.getHand().isDone()) {
					p.setDone(true);
				}
			}
		}
	}
	private static void processHand(Hand hand, boolean isSplit) {
		while(!hand.isDone()) {
			Player player = hand.getPlayer();
			System.out.print("\n" + player.getName());
			System.out.println(": Your hand: " + hand.getList());
			List<Integer> scores = hand.getScore();
			System.out.println(scores);
			if(scores.contains(21)) {
				System.out.println("You have blackjack!");
//				player.setBank(player.getBank() + 2 * hand.getBet());
//				System.out.println("Bank: " + player.getBank());
				hand.setDone(true);
				hand.setState(HAND_STATE.TWENTY_ONE);
				break;
			}
			String[] answers = {"hit", "stand"};
//			if(scan.hasNextLine()) {
//				scan.nextLine();
//			}
			String answer = prompt("Hit or Stand? ", answers);
			if(answer.equals("hit")) {
				hand.addCard(deck.dealCard());
				List<Integer> scores1 = hand.getScore();
				if(scores1.contains(21)) {
					System.out.println(": Your hand: " + hand.getList());
					System.out.println("You have blackjack!");
					hand.setDone(true);
					break;
				}
				if(scoresLessThan22(scores1).size() == 0) {
					System.out.println("Cards: " + hand.getList());
					System.out.println("You're busted!");
					hand.setDone(true);
					hand.setState(HAND_STATE.BUSTED);
					break;
				}
			}
			else {
				hand.setDone(true);
				hand.setState(HAND_STATE.DONE);
			}
		}
	}
	/*
	private static void payDealer() {
		
	}
	*/
	private static void optionalDoubleDown(Player p) {
		// only called at start of game
		if(prompt("You can double down. Do you wish to do so? (Y/N) ", yn).equals("y")) {
			p.setBank(p.getBank() - p.getBet());
			p.setBet(p.getBet() * 2);
			p.getHand().draw(deck);
		}
	}
	private static void optionalSplit(Player p, Card card1, Card card2) {
		//only called at start of game
		String text = "You have two " + card1.getRank().name() + 
				"'s.\nDo you want to split? ";
		if(prompt(text, yn).equalsIgnoreCase("y")) {
			//split cards for this player
			p.setBank(p.getBank() - p.getBet());
			System.out.println(p.getBet());
			p.split();
			p.getSplitHand().addCard(card2);
			p.getHand().remove(card2);
		}
	}
	private static String prompt(String text, String[] answers) {
		List<String> answerList = Arrays.asList(answers);
		do {
			System.out.print(text);
			String response = scan.nextLine();
			if(answerList.contains(response)) {
				return response;
			}
		}while(true);
	}
	private static boolean anyPlayerActive() {
		// Any player is active and has bank
		for(Player p : players) {
			if(p.isActive() && !p.isDone()) {
				return true;
			}
		}
		return false;
	}
	private static List<Player> getActivePlayers() {
		List<Player> activePlayers = new ArrayList<>();
		for(Player p : players) {
			if(p.isActive() && !p.isDone()) {
				activePlayers.add(p);
			}
		}
		return activePlayers;
	}
}