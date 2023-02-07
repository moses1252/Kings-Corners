package gameTester;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import game.Card;
import game.DeckOfCards;
import game.Game;

class cardTester {
	private static DeckOfCards deck = new DeckOfCards();
	
	//test deck.toString()
//	@Test
//	void testPrint() {
//		System.out.println(deck.getDeck());
//	}
	
	
	//------------------Testing check method from Game Class--------------------------------
	//putting a 5 on a 9 should be false
	//purposely putting wrong card on a pile
	@Test
	void testWrongCard() {
		Card card1 = deck.dealTopCard(); //2 of hearts"ace"
		Card card2 = deck.dealTopCard(); //3 of hearts"king"
		Card card3 = deck.dealTopCard(); //4 of hearts"queen"
		Card card4 = deck.dealTopCard(); //5 of hearts"jack"
		
		ArrayList<Card> pile1 = new ArrayList<>();
		
		pile1.add(deck.dealTopCard()); //6 of hearts"10"
		pile1.add(deck.dealTopCard()); //7 of hearts"9"
		pile1.add(deck.dealTopCard()); //8 of hearts"8"
		pile1.add(deck.dealTopCard()); //9 of hearts"7"
		pile1.add(deck.dealTopCard()); //10 of hearts"7"
		
		
		Game game = new Game();
		//pile1: 6, 7, 8, 9 ---> next card needs to be 8 for game to return true
		//card4: 5
		boolean test =  game.check(pile1, card4);;
		
		assertEquals(test, false);
	}
	
	//putting a 7 on a 8 should be true
	//putting the correct card on a pile
	@Test
	void testCorrectCard() {
		
		Card card1 = deck.dealTopCard(); //2 of hearts
		Card card2 = deck.dealTopCard(); //3 of hearts
		Card card3 = deck.dealTopCard(); //4 of hearts
		Card card4 = deck.dealTopCard(); //5 of hearts
		Card card5 = deck.dealTopCard(); //6 of hearts
		Card card6 = deck.dealTopCard(); //7 of hearts
		//System.out.println(card1);
		
		ArrayList<Card> pile1 = new ArrayList<>();
		
		pile1.add(deck.dealTopCard()); //8 of hearts
		pile1.add(deck.dealTopCard()); //9 of hearts
		pile1.add(deck.dealTopCard()); //10 of hearts
		pile1.add(deck.dealTopCard()); //J of hearts

		//Card card7 = deck.dealTopCard(); //10 of hearts

		for (int i = 0; i < 12; i++) {//Q, K, A, 2, 3, 4, 5, 6, 7, 8, 9, 10
			card5 = deck.dealTopCard();
		}
		
		Game game = new Game();
		//pile1: 6, 7, 8, 9, 10, J, Q, K, A, 2, 3, 4, 5, 6, 7, 8  ---> next card needs to be 7 for game to return true
		//card4: 10
		boolean test2 =  game.check(pile1, card5);
		
		assertEquals(test2, true);
	}
	
	//try putting an Ace on a two
	@Test
	void testAce() {
		
		ArrayList<Card> pile1 = new ArrayList<>();
		
		pile1.add(deck.dealTopCard()); //2 
		
		Card two = deck.dealTopCard(); //3
		
		for (int i = 0; i < 11; i++) {//4, 5, 6, 7, 8, 9, 10, J, Q, K , ace
			two = deck.dealTopCard();
		}
		
		Game game = new Game();
		
		//test
		
		//pile1: 2  ---> next card needs to be Ace for game to return true
		//two:   ace
		boolean test2 =  game.check(pile1, two);
		
		assertEquals(test2, true);
		
	}
	
	//try putting a Queen on King
	@Test
	void testQueen() {
		ArrayList<Card> pile1 = new ArrayList<>();
		
		Card queen = null;
		
		for (int i = 0; i < 11; i++) {//2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q
			queen = deck.dealTopCard();
		}
		
		pile1.add(deck.dealTopCard()); //K
		
		Game game = new Game();
		//pile1: K  ---> next card needs to be Q for game to return true
		//queen:   Q
		boolean test2 =  game.check(pile1, queen);
		
		assertEquals(test2, true);
	}


}
