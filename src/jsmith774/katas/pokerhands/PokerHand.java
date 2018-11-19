package jsmith774.katas.pokerhands;

import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class PokerHand {
	private Card[] cards = new Card[5];
	
	private Map<CardOrdinal, Integer> cardOrdinalMap = new HashMap<CardOrdinal, Integer>(); //key = cardOrdinal; value=count of that ordinal in hand

	public static PokerHand createFromString(String handString) throws Exception {
		PokerHand hand = new PokerHand();
		
		Card card = null;
		StringTokenizer tokenizer = new StringTokenizer(handString);
		CardOrdinal ordinal = null;
		
		//tokenize String and create/load Cards from each token
		//could add error handling; check for exactly 5 tokens etc
		
		Map<CardOrdinal, Integer> cardOrdinalMap = hand.cardOrdinalMap;
		int i=0;
		while(tokenizer.hasMoreTokens()) {
		//for each token }
			if(i == 5) { 
				throw new Exception("Too many cards provided in string input.");
			}
			String cardValue = tokenizer.nextToken();
			card = new Card(cardValue);
			
			hand.cards[i] = card;
			ordinal = card.getOrdinal();
			 
			if(cardOrdinalMap.containsKey(ordinal)) {
				//increment value
				Integer ordinalCount = cardOrdinalMap.get(ordinal);
				cardOrdinalMap.put(ordinal, ++ordinalCount);
			} else {
				cardOrdinalMap.put(ordinal, 1);
			}
			i++;
		}
		if(i != 5) {
			throw new Exception("Improper number of cards in string input.");
		}
		
		
		return hand;
	}
	
	public Card[] getCards() {
		return this.cards;
	}
	
	public HandRank getRank() {
		Map map = this.cardOrdinalMap;
		Set keySet = map.keySet();
		int keyCount = keySet.size();
		if(keyCount == 5) {
			boolean isStraight = false;
			boolean isFlush = false;
			
			//checkForStraight
			Arrays.sort(cards);
			if(cards[4].getOrdinal().getValue() - cards[0].getOrdinal().getValue() == 4) {
				isStraight = true;
			}
			
			//checkForFlush
			CardSuit lastSuit = cards[0].getSuit();
			for(int i=1; i < cards.length; i++) //start with index 1 (second card)
			{			
				if(cards[i].getSuit().equals(lastSuit)) {
					isFlush = true;				
				} else {
					isFlush = false;
				}
				if(!isFlush) {
					break;
				}
			}
			
			if(isStraight && isFlush) {
				return HandRank.STRAIGHT_FLUSH;
			}
			if(isStraight) {
				return HandRank.STRAIGHT;
			}
			if(isFlush) {
				return HandRank.FLUSH;
			}
			return HandRank.HIGH_CARD;
		} else if(keyCount == 4) {
			return HandRank.ONE_PAIR;
		} else if(keyCount == 3) {
			if(map.containsValue(2)) {
				return HandRank.TWO_PAIR;
			} else {
				return HandRank.SET;
			}
		} else {
			if(map.containsValue(3) ) {
				return HandRank.FULL_HOUSE;
			} else {
				return HandRank.QUADS;
			}
		}
	}
	
	public List<CardOrdinal> getPairs() {
		List<CardOrdinal> pairs = new ArrayList<CardOrdinal>();
		
		Set<Map.Entry<CardOrdinal, Integer>> entries = cardOrdinalMap.entrySet();
		for (Map.Entry<CardOrdinal, Integer> entry : entries) {
		    if(entry.getValue() == 2) {
		    	pairs.add(entry.getKey());
		    }
		}
		return pairs;
	}
	
	public CardOrdinal getSet() {
		Set<Map.Entry<CardOrdinal, Integer>> entries = cardOrdinalMap.entrySet();
		for (Map.Entry<CardOrdinal, Integer> entry: entries) {
			if(entry.getValue() == 3) {
				return entry.getKey();
			}
		}
		//no set
		return null;
	}
	
	public CardOrdinal getQuads() {
		Set<Map.Entry<CardOrdinal, Integer>> entries = cardOrdinalMap.entrySet();
		for (Map.Entry<CardOrdinal, Integer> entry: entries) {
			if(entry.getValue() == 4) {
				return entry.getKey();
			}
		}
		//no quads
		return null;
	}
}
