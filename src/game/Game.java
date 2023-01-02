package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Game {
	private static DeckOfCards deck = new DeckOfCards();

	public static void main(String[] args) {
		
		
		//start game simulation
		game();
		
		
		
	}
	
	public static void game() {
		//shuffle deck
		//deck.shuffle();
		
		//establish users and their deck
		Scanner input = new Scanner(System.in);
		String user1;
		String user2;
		
		ArrayList<Card> user1Deck = new ArrayList<>();
		ArrayList<Card> user2Deck = new ArrayList<>();
		
		//fill user's deck
		initiateDeck(deck, user1Deck);
		initiateDeck(deck, user2Deck);
		
//		System.out.println(user1Deck.toString());
//		System.out.println(user2Deck.toString());
		
		
		
	}
	
	//check if the card placement is valid
	//e.g queen can only be placed on top of a king
	public static void check(ArrayList<Card> pile, Card card) {
		//String[] deckOrder = {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};
		HashMap<Character, Integer> deckOrder = new HashMap<>();
		deckOrder.put('a', 0);
		deckOrder.put('2', 1);
		deckOrder.put('3', 2);
		deckOrder.put('4', 3);
		deckOrder.put('5', 4);
		deckOrder.put('6', 5);
		deckOrder.put('7', 6);
		deckOrder.put('8', 7);
		deckOrder.put('9', 8);
		deckOrder.put('1', 9);
		deckOrder.put('j', 10);
		deckOrder.put('q', 11);
		deckOrder.put('k', 12);
		
		//store value of last card to compare it to incoming card
		int len = pile.size();
		int pileCardValue = deckOrder.get(pile.get(len));
		
		int cardValue = deckOrder.get(card);
		
		if (cardValue < pileCardValue) {
			pile.add(card);
		}
		
	}
	
	public static void initiateDeck(DeckOfCards deck, ArrayList<Card> userDeck) {
		for (int i = 0; i < 7; i++) {
			userDeck.add(deck.dealTopCard());
		}
	}

}











//System.out.println(newDeck.getDeck().get(0));


//print out cards by suits
//for (int i = 0; i < 52; i++) {
//	if (i % 13 == 0 && i != 0) {
//		System.out.println();
//	}
//	System.out.print(deck.dealTopCard() + ", ");
//}

//shuffle cards
//System.out.println("\n\n");
//newDeck.shuffle();
////print out cards by suits
//for (int i = 0; i < 52; i++) {
//	if (i % 13 == 0 && i != 0) {
//		System.out.println();
//	}
//	System.out.print(newDeck.dealTopCard() + ", ");
//}


