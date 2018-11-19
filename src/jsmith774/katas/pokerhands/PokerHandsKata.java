package jsmith774.katas.pokerhands;

import java.util.Arrays;
import java.util.Collections;

public class PokerHandsKata {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static Player showdown(Player p1, Player p2) {
		//first check handTypes //high, pair, 2 pair, set, etc
		
		//if handTypes are equal, look for higest ranks by hand type
		HandRank p1Rank = p1.getHand().getRank();
		HandRank p2Rank = p2.getHand().getRank();
		
		int result = p1Rank.compareTo(p2Rank);
		if(result > 0) {
			return p1;
		} else if(result < 0) {
			return p2;
		} else {
			//evaluate tied handRank for handValue
			Card[] p1Cards = p1.getHand().getCards();
			Card[] p2Cards = p2.getHand().getCards();
			switch(p1Rank) {
			case HIGH_CARD:
				Arrays.sort(p1Cards, Collections.reverseOrder());				
				Arrays.sort(p2Cards, Collections.reverseOrder());
				for(int i=0; i<p1Cards.length; i++) {
					int compareOrdinal = p1Cards[i].getOrdinal().compareTo(p2Cards[i].getOrdinal());
					if(compareOrdinal > 0) {
						return p1;
					} else if(compareOrdinal < 0) {
						return p2;
					} //else equal, continue processing next card
				}
				//all cards tied
				return Player.PUSH;
			case ONE_PAIR:
System.out.println("Compare ONE_PAIR hands");				
				break;
			case TWO_PAIR:
System.out.println("Compare TWO_PAIR hands");				
				break;
			//case SET: //PUSH NOT POSSIBLE
			case STRAIGHT:
				Arrays.sort(p1Cards, Collections.reverseOrder());				
				Arrays.sort(p2Cards, Collections.reverseOrder());
				for(int i=0; i<p1Cards.length; i++) {
					int compareOrdinal = p1Cards[i].getOrdinal().compareTo(p2Cards[i].getOrdinal());
					if(compareOrdinal > 0) {
						return p1;
					} else if(compareOrdinal < 0) {
						return p2;
					} //else equal, continue processing next card
				}
				//all cards tied
				return Player.PUSH;			
			case FLUSH:
System.out.println("Compare FLUSH hands");				
				break;
			//case FULL_HOUSE: //PUSH NOT POSSIBLE
			//case QUADS: //PUSH NOT POSSIBLE
			case STRAIGHT_FLUSH:
System.out.println("Compare STRAIGHT_FLUSH hands");				
				break;
			default:
System.out.println("No RANK case match");				
				//exception
				return Player.PUSH;
			}
		return Player.PUSH;		
		}
	
	}
	
}
