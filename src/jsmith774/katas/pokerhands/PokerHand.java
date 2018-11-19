package jsmith774.katas.pokerhands;

import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PokerHand {
	private Card[] cards = new Card[5];
	
	private Map<CardOrdinal, Integer> cardOrdinalMap = new HashMap<CardOrdinal, Integer>(); //key = cardOrdinal; value=count of that ordinal in hand

	public static PokerHand createFromString(String handString) {
		PokerHand hand = new PokerHand();
		
		Card card = null;
		CardOrdinal ordinal = null;
		
		//tokenize String and create/load Cards from each token
		//could add error handling; check for exactly 5 tokens etc
		
		Map<CardOrdinal, Integer> cardOrdinalMap = hand.cardOrdinalMap;
		int i=0;
		//for each token }
			hand.cards[i] = card;
			ordinal = card.getOrdinal();
			 
			if(cardOrdinalMap.containsKey(ordinal)) {
				//increment value
				Integer ordinalCount = cardOrdinalMap.get(ordinal);
				cardOrdinalMap.put(ordinal, ++ordinalCount);
			} else {
				cardOrdinalMap.put(ordinal, 1);
			}
			
		// } verify exactly 5 tokens or throw exception
		return hand;
	}
	
	public HandRank getRank() {
		Map map = this.cardOrdinalMap;
		Set keySet = map.keySet();
		int keyCount = keySet.size();
		if(keyCount == 5) {
			//checkForFlush
			//checkForStraight
			
			//if not flush and not straight
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
	
	//hand outcome type (pair, 2pair, set, etc.)
	
	//hand high card (varies with type)
	//   ex: high card = cards in descending value order (5 values)
	//         pair = pair value, remaining cars in order (4 values)
	//         2 pair = highest pair, second highest pair, odd card (3 values)
	//         set = set, remaining cards in order (3 value)
	//		   etc.

}
