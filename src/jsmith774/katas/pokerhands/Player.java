package jsmith774.katas.pokerhands;

public class Player {

	private String name;
	private PokerHand hand;
	
	public Player() {
		
	}
	
	public Player(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public PokerHand getHand() {
		return hand;
	}
	public void setHand(PokerHand hand) {
		this.hand = hand;
	}
	
	
	
}
