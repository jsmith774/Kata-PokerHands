package jsmith774.katas.pokerhands;

public class PokerHandsKata {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static Player showdown(Player p1, Player p2) {
		//first check handTypes //high, pair, 2 pair, set, etc
		
		//if handTypes are equal, look for higest ranks by hand type
		
		int result = p1.getHand().getRank().compareTo(p2.getHand().getRank());
		if(result > 0) {
			return p1;
		} else if(result < 0) {
			return p2;
		} else {
			//evaluate tied handRank for handValue
			return null;
		}
	
	}

}
