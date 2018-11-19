package jsmith774.katas.pokerhands;

public class Card implements Comparable<Card> {

	private CardOrdinal ordinal;
	private CardSuit suit;

	public Card(String cardValue) {
		try {
			this.ordinal = evalOrdinal(cardValue.charAt(0));
			this.suit = evalSuit(cardValue.charAt(1));
System.out.println("Ordinal = " + this.ordinal + " | Suit = " + this.suit);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.err.println(ex.getMessage());
		}
	}
	
	CardOrdinal getOrdinal() {
		return this.ordinal;
	}
	
	CardSuit getSuit() {
		return this.suit;
	}
	
	private CardOrdinal evalOrdinal(char charOrdinal) throws Exception {
		switch(charOrdinal) {
		case '2':
			return CardOrdinal.TWO;
		case '3':
			return CardOrdinal.THREE;
		case '4':
			return CardOrdinal.FOUR;
		case '5':
			return CardOrdinal.FIVE;
		case '6':
			return CardOrdinal.SIX;
		case '7':
			return CardOrdinal.SEVEN;
		case '8':
			return CardOrdinal.EIGHT;
		case '9':
			return CardOrdinal.NINE;
		case 'T':
			return CardOrdinal.TEN;
		case 'J':
			return CardOrdinal.JACK;
		case 'Q':
			return CardOrdinal.QUEEN;
		case 'K':
			return CardOrdinal.KING;
		case 'A':
			return CardOrdinal.ACE;
		default:
			throw new Exception("Invalid card ordinal:" + charOrdinal);
		}
	}
	
	private CardSuit evalSuit(char charSuit) throws Exception {
		switch(charSuit) {
		case 'C':
			return CardSuit.CLUB;
		case 'D':
			return CardSuit.DIAMOND;
		case 'H':
			return CardSuit.HEART;
		case 'S':
			return CardSuit.SPADE;
		default:
			throw new Exception("Invalid card suit:" + charSuit);
		}
	}
	
	public int compareTo(Card otherCard) {
		return this.getOrdinal().compareTo(otherCard.getOrdinal());
	}
}
