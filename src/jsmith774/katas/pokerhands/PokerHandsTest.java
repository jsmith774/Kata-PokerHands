package jsmith774.katas.pokerhands;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PokerHandsTest {
	/*
	 	Suggested Test Cases

	 	**Sample input:**

			Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C AH  
			Black: 2H 4S 4C 2D 4H  White: 2S 8S AS QS 3S  
			Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C KH  
			Black: 2H 3D 5S 9C KD  White: 2D 3H 5C 9S KH  
  
			Each row of input is a game with two players.
			The first five cards belong to the player named “Black” 
			and the second five cards belong to the player named “White”.  
  
  
	 	**Sample output:**

			White wins. - with high card: Ace  
			Black wins. - with full house: 4 over 2   
			Black wins. - with high card: 9  
			Tie.  

	 */
	
	private static final Player PLAYER1 = new Player("Black");
	private static final Player PLAYER2 = new Player("White");
	
	private static final List<String[]> testData = new ArrayList<String[]>();	
	
	private static final String invalidOrdinalData = "3D BS AC 3H 5C";
	private static final String invalidSuitData = "3C 9F AH 3S 5S";
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		//p1Cards, p2Cards, expectedWinnerName, winningHandDescription
		
		/* Suggested Tests */
		testData.add(new String[] {"2H 3D 5S 9C KD", "2C 3H 4S 8C AH", PLAYER2.getName()});
		testData.add(new String[] {"2H 4S 4C 2D 4H", "2S 8S AS QS 3S", PLAYER1.getName()});
		testData.add(new String[] {"2H 3D 5S 9C KD", "2C 3H 4S 8C KH", PLAYER1.getName()});
		testData.add(new String[] {"2H 3D 5S 9C KD", "2D 3H 5C 9S KH", "PUSH"});
		
		/* Other Test Cases */
		// LESS SEQUENTIAL INPUT DATA
		testData.add(new String[] {"9C 3D 2H 5S KD", "2C 3H 4S 8C KH", PLAYER1.getName()});
		testData.add(new String[] {"JC TD 3D KH 9S", "2C TH AD 4H 5D", PLAYER2.getName()});
		testData.add(new String[] {"2H 3H 5S 9C AD", "KC 9D 4C 8C 2C", PLAYER1.getName()});
		
		// ONE PAIR
		testData.add(new String[] {"AH 2D TC KC QH", "9D 2H 7H 2C 4S", PLAYER2.getName()}); //vs no pair
		testData.add(new String[] {"AH 2D QC KC QH", "9D 2H 7H 2C 4S", PLAYER1.getName()});//2 non-equal pairs
		testData.add(new String[] {"AH 2D QC JC QH", "AD 4H QS 3C QD", PLAYER1.getName()});//2 equal pairs, non equal kickers
		testData.add(new String[] {"AH 3D QC 4D QH", "AD 4H QS 3C QD", "PUSH"});//2 equal pairs, equal kickers
		
		// TWO PAIR
		testData.add(new String[] {"AH AD TC KC QH", "3D 2H 7H 2C 3S", PLAYER2.getName()}); //vs 1 pair
		testData.add(new String[] {"2H AD 2C KC AH", "9D 5H 9H 5C 4S", PLAYER1.getName()});//2 non-equal two pairs
		testData.add(new String[] {"2H 2D QC JC QH", "2C 2H QS 9C QD", PLAYER1.getName()});//2 equal two pairs, non equal kickers
		testData.add(new String[] {"2H 2D QC 9D QH", "2C 2H QS 9C QD", "PUSH"}); //2 equal two pairs, equal kickers		
		
		// SET
		testData.add(new String[] {"AH AD KD KC QH", "2D 2H 7H 2C 3S", PLAYER2.getName()}); //vs 2 pair
		testData.add(new String[] {"2H 3D 2C 2C AH", "9D TH 9H 9C 8S", PLAYER2.getName()});//2 non-equal sets
		testData.add(new String[] {"2H 2D QC 2S QH", "3C 3H QS 3D QD", PLAYER2.getName()}); //2 non-equal sets, equal kickers
		
		 
		// STRAIGHT 
		testData.add(new String[] {"2H 3D 4S 5C 6D", "7C 8H TS QC AH", PLAYER1.getName()}); //vs non straight
		testData.add(new String[] {"4S 5C 8D 6D 7D", "7S 6H 5H 3D 4H", PLAYER1.getName()}); //2 non-equal straights
		testData.add(new String[] {"2H 3D 4S 5C 6D", "2S 6H 5H 3D 4H", "PUSH"}); //2 equal straights

		// FLUSH
		testData.add(new String[] {"2H 3H 5H 7H 8H", "9C TH JH KD AS", PLAYER1.getName()}); //vs non flush
		testData.add(new String[] {"TC JH QH KS AC", "2H 3H 5H 7H 8H", PLAYER2.getName()});//vs straight
		testData.add(new String[] {"2H 3H 5H 7H 8H", "2C 8C 7C 9C 5C", PLAYER2.getName()}); //2 non-equal flushes
		testData.add(new String[] {"2H 3H 5H 7H 8H", "2C 8C 7C 3C 5C", "PUSH"}); //2 equal flushes
		
		// FULL HOUSE
		testData.add(new String[] {"2H 3H 5H 7H 8H", "2D 3D 2C 2S 3S", PLAYER2.getName()});// vs flush
		testData.add(new String[] {"2H AD 2C 2S AS", "9H 9D AC AH 9S", PLAYER2.getName()});// vs full house
		
		// QUADS
		testData.add(new String[] {"AD KD AC AS KS", "2D KC 2H 2C 2S", PLAYER2.getName()}); //vs full house
		testData.add(new String[] {"AD KD AC AS AC", "2D KC 2H 2C 2S", PLAYER1.getName()}); // vs quads
		testData.add(new String[] {"2D KC 2H 2C 2S", "AD KD AC AS AC", PLAYER2.getName()});
		
		/* STRAIGHT FLUSH */
		testData.add(new String[] {"AD AS AC AH KH", "3H 2H 5H 4H 6H", PLAYER2.getName()}); //vs non straight flush
		testData.add(new String[] {"TD JS QC KD AH", "3H 2H 5H 4H 6H", PLAYER2.getName()});//vs straight
		testData.add(new String[] {"9D TD JD QD AD", "3H 2H 5H 4H 6H", PLAYER2.getName()});//vs flush
		testData.add(new String[] {"AD KD AS AC KC", "3H 2H 5H 4H 6H", PLAYER2.getName()});//vs full house
		testData.add(new String[] {"AC AS AH KD AD", "3H 2H 5H 4H 6H", PLAYER2.getName()});//vs quads
		testData.add(new String[] {"3D 7D 5D 4D 6D", "3H 2H 5H 4H 6H", PLAYER1.getName()});//2 non-equal straight flushes
		testData.add(new String[] {"TD JD QD KD AD", "AH TH KH JH QH", "PUSH"});//2 equal straighr flushes	
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCompareHands() throws Exception {
		int i=1;
		for(String[] dataRow: testData) {
			
			PLAYER1.setHand(PokerHand.createFromString(dataRow[0]));
			PLAYER2.setHand(PokerHand.createFromString(dataRow[1]));
			
			Player winningPlayer = PokerHandsKata.showdown(PLAYER1, PLAYER2);
			
			assertEquals(dataRow[2], winningPlayer.getName());
		}
		
	}
	
	@Test
	void testInvalidOrdinal() {
		boolean hasException = false;
		try {
			PLAYER1.setHand(PokerHand.createFromString(invalidOrdinalData));
		} catch(Exception ex) {
			hasException = true;
		}
		
		if(!hasException) {
			fail("Expected exception for invalid ordinal not received");
		}
	}

	@Test
	void testInvalidSuit() {
		boolean hasException = false;
		try {
			PLAYER1.setHand(PokerHand.createFromString(invalidSuitData));
		} catch(Exception ex) {
			hasException = true;
		}
		
		if(!hasException) {
			fail("Expected exception for invalid suit not received");
		}
	}
}
