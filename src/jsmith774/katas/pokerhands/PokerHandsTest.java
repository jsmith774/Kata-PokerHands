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
	
	
	
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		//p1Cards, p2Cards, expectedWinnerName, winningHandDescription
		
		/* Suggested Tests */
		testData.add(new String[] {"2H 3D 5S 9C KD", "2C 3H 4S 8C AH", PLAYER2.getName()});
		testData.add(new String[] {"2H 4S 4C 2D 4H", "2S 8S AS QS 3S", PLAYER1.getName()});
		testData.add(new String[] {"2H 3D 5S 9C KD", "2C 3H 4S 8C KH", PLAYER1.getName()});
		testData.add(new String[] {"2H 3D 5S 9C KD", "2D 3H 5C 9S KH", "PUSH"});
		
		/* Other Test Cases */
		//out of order
		testData.add(new String[] {"9C 3D 2H 5S KD", "2C 3H 4S 8C KH", PLAYER1.getName()});
		testData.add(new String[] {"JC TD 3D KH 9S", "2C TH AD 4H 5D", PLAYER2.getName()});
		testData.add(new String[] {"2H 3H 5S 9C AD", "KC 9D 4C 8C 2C", PLAYER1.getName()});
		
		/* One Pair */
		/* Two Pair */
		/* Set */
		
		// STRAIGHTS //
		testData.add(new String[] {"2H 3D 4S 5C 6D", "7C 8H TS QC AH", PLAYER1.getName()});
		testData.add(new String[] {"4S 5C 8D 6D 7D", "7S 6H 5H 3D 4H", PLAYER1.getName()}); //2 non-equal straights
		testData.add(new String[] {"2H 3D 4S 5C 6D", "2S 6H 5H 3D 4H", "PUSH"}); //2 equal straights

		/* Flush */
		testData.add(new String[] {"2H 3H 5H 7H 8H", "9C TH JH KD AS", PLAYER1.getName()});
		testData.add(new String[] {"2H 3H 5H 7H 8H", "2C 8C 7C 3C 5C", "PUSH"});
		
		/* Full House */
		
		/* Straight Flush */
			// Lower straight flush vs higher straigh */
			// Lower straight flush vs higher flush
	
		/* Invalid Ordinal */
		
		/* Invalid Suit */
		
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
	void testCompareHands() {
		int i=1;
		for(String[] dataRow: testData) {
			
			System.out.println("Testing Hand #" + i++);
			
			PLAYER1.setHand(PokerHand.createFromString(dataRow[0]));
			PLAYER2.setHand(PokerHand.createFromString(dataRow[1]));
			
			Player winningPlayer = PokerHandsKata.showdown(PLAYER1, PLAYER2);
			
			assertEquals(dataRow[2], winningPlayer.getName());
		}
	}

}
