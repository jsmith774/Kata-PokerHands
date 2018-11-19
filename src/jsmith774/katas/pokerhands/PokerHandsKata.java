package jsmith774.katas.pokerhands;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PokerHandsKata {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static Player showdown(Player p1, Player p2) throws Exception {
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
			switch(p1Rank) {
			case HIGH_CARD: case STRAIGHT: case FLUSH: case STRAIGHT_FLUSH:
				return checkForHighCard(p1, p2);
			case ONE_PAIR:
				List<CardOrdinal> p1Pair = p1.getHand().getPairs();
				List<CardOrdinal> p2Pair = p2.getHand().getPairs();
				int compareOrdinal = p1Pair.get(0).compareTo(p2Pair.get(0));
				if(compareOrdinal > 0) {
					return p1;
				} else if(compareOrdinal < 0) {
					return p2;
				} 
				return checkForHighCard(p1, p2);
			case TWO_PAIR:								
				List<CardOrdinal> p1Pairs = p1.getHand().getPairs();
				List<CardOrdinal> p2Pairs = p2.getHand().getPairs();
				CardOrdinal[] p1PairArray = p1Pairs.toArray(new CardOrdinal[0]);
				CardOrdinal[] p2PairArray = p2Pairs.toArray(new CardOrdinal[0]);
				
				Arrays.sort(p1PairArray, Collections.reverseOrder());
				Arrays.sort(p2PairArray, Collections.reverseOrder());
				
				for(int i=0; i<p1PairArray.length; i++) {
					int comparePairs = p1PairArray[i].compareTo(p2PairArray[i]);
					if(comparePairs > 0) {
						return p1;
					} else if(comparePairs < 0) {
						return p2;
					} 
					return checkForHighCard(p1, p2);
				}	
			case SET: case FULL_HOUSE:
				CardOrdinal p1Set = p1.getHand().getSet();
				CardOrdinal p2Set = p2.getHand().getSet();
				
				if(p1Set.compareTo(p2Set) > 0) {
					return p1;
				} else {
					return p2;
				}
				//PUSH NOT POSSIBLE
			case QUADS: //PUSH NOT POSSIBLE
				CardOrdinal p1Quads = p1.getHand().getQuads();
				CardOrdinal p2Quads = p2.getHand().getQuads();
				
				if(p1Quads.compareTo(p2Quads) > 0) {
					return p1;
				} else {
					return p2;
				}
				//PUSH NOT POSSIBLE
			default:			
				throw new Exception("Invalid Hand");
			}		
		}
	
	}
	private static Player checkForHighCard(Player p1, Player p2) {
		Card[] p1Cards = p1.getHand().getCards();
		Card[] p2Cards = p2.getHand().getCards();
		
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
	}
}
