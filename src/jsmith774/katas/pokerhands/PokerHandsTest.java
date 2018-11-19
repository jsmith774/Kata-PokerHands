package jsmith774.katas.pokerhands;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

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
	
	private static final ArrayList<String[]> testData = new ArrayList<String[]>();	
	
	
	
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		//p1Cards, p2Cards, expectedWinnerName, winningHandDescription
		testData.add(new String[]{"2H 3D 5S 9C KD", "2C 3H 4S 8C AH", PLAYER2.getName(), "High Card: Ace"});
		testData.add(new String[] {"2H 4S 4C 2D 4H", "2S 8S AS QS 3S", PLAYER1.getName(), "Full House: 4s over 2s"});
		testData.add(new String[] {"2H 3D 5S 9C KD", "2C 3H 4S 8C KH", PLAYER1.getName(), "High Card: KingNine"});
		testData.add(new String[] {"2H 3D 5S 9C KD", "2D 3H 5C 9S KH", "PUSH", "High Card: KingNineFiveThreeTwo"});
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
			System.out.println("Hand #" + i++);
			//set player1 hand
			//set player2 hand
			//p1 vs p2 showdown
			//test showdown result
		}
		fail("Not yet implemented");
	}

}
