package gameTester;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import game.Card;
import game.DeckOfCards;
import game.Game;

class cardTester {
	private static DeckOfCards deck = new DeckOfCards();
	
	

	@Test
	void test() {
		
		Card card1 = deck.dealTopCard(); //2 of hearts
		Card card2 = deck.dealTopCard(); //3 of hearts
		Card card3 = deck.dealTopCard(); //4 of hearts
		Card card4 = deck.dealTopCard(); //5 of hearts
		//System.out.println(card1);
		
		ArrayList<Card> pile1 = new ArrayList<>();
		
		pile1.add(deck.dealTopCard()); //6 of hearts
		pile1.add(deck.dealTopCard()); //7 of hearts
		pile1.add(deck.dealTopCard()); //8 of hearts
		pile1.add(deck.dealTopCard()); //9 of hearts
		
		
		Game game = new Game();
		boolean test =  false;
		if (game.check(pile1, card4)) {
			test = true;
		}
		assertEquals(test, true);
		

	}
	
	
	@Test
	void testCheckMethod() {
		
		Card card1 = deck.dealTopCard(); //2 of hearts
		Card card2 = deck.dealTopCard(); //3 of hearts
		Card card3 = deck.dealTopCard(); //4 of hearts
		Card card4 = deck.dealTopCard(); //5 of hearts
		//System.out.println(card1);
		
		ArrayList<Card> pile1 = new ArrayList<>();
		
		pile1.add(deck.dealTopCard()); //6 of hearts
		pile1.add(deck.dealTopCard()); //7 of hearts
		pile1.add(deck.dealTopCard()); //8 of hearts
		pile1.add(deck.dealTopCard()); //9 of hearts
		
		
		Game game = new Game();
		//test
		Card card5 = deck.dealTopCard(); //10 of hearts
		boolean test2 =  false;
		if (game.check(pile1, card5)) {
			test2 = true;
		}
		assertEquals(test2, false);
		

	}
	


}
