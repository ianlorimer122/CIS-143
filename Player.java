package blackjack;

import java.util.Scanner;

public class Player {
	private String name;
	private Hand[] hands;
	private int bank; // chips available
	private boolean withdrawn; //? TODO
	private boolean done;
	private boolean startOfGame;
	public Player(String name, int bank, int bet) {
		setName(name);
		setBank(bank);
		setStartOfGame(true);
		hands = new Hand[2];
		hands[0] = new Hand(this);
		hands[0].setBet(bet);
		hands[1] = null;
		withdrawn = false;
		done = false;
	}
	public boolean isStartOfGame() {
		return startOfGame;
	}
	public void setStartOfGame(boolean startOfGame) {
		this.startOfGame = startOfGame;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	public boolean isWithdrawn() {
		return withdrawn;
	}
	public boolean isActive() {
		return !withdrawn && bank > 0;
	}
	public void split() {
		bank -= this.getBet();
		hands[1] = hands[0].createSplitHand(this);
		this.setSplitBet(this.getBet());
	}
	public Hand getHand() {
		return hands[0];
	}
	public Hand getSplitHand() {
		return hands[1];
	}
	public int getBet() {
		return this.getHand().getBet();
	}
	public int getSplitBet() {
		return this.getSplitHand().getBet();
	}
	public void setSplitBet(int bet) {
		this.getSplitHand().setBet(bet);
	}
	public void setBet(int bet) {
		this.getHand().setBet(bet);
	}
	public void setSplitHand(Hand splitHand) {
		hands[1] = splitHand;
	}
	public int getBank() {
		return bank;
	}
	public void setBank(int bank) {
		this.bank = bank;
	}
	public void placeBet(Scanner scan) {
		bank -= this.getBet();
	}
	public boolean hasSplit() {
		return this.getSplitHand() != null;
	}
	public Hand[] getHands() {
		// TODO Auto-generated method stub
		return hands;
	}
}
