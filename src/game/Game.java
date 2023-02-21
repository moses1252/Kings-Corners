package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Game {
	private static DeckOfCards deck = new DeckOfCards();

	// create 4 piles and 4 kings corner piles
	private static ArrayList<Card> pile1 = new ArrayList<>();
	private static ArrayList<Card> pile2 = new ArrayList<>();
	private static ArrayList<Card> pile3 = new ArrayList<>();
	private static ArrayList<Card> pile4 = new ArrayList<>();

	private static ArrayList<Card> kingCorner1 = new ArrayList<>();
	private static ArrayList<Card> kingCorner2 = new ArrayList<>();
	private static ArrayList<Card> kingCorner3 = new ArrayList<>();
	private static ArrayList<Card> kingCorner4 = new ArrayList<>();

	private static ArrayList<Card> user1Deck = new ArrayList<>();
	private static ArrayList<Card> user2Deck = new ArrayList<>();

	// set up game
	public void game() {
		// start game simulation
		System.out.println("Welcome to my program Kings Corner!!!");
		System.out.println("This is a two player game, and there can only be 1 winner.");
		System.out.println("Players should know the general rules of Kings Corner."
				+ "\nIf not please take a look at the projects readme file.");
		System.out.println("There will be 3 options for a user, and they will have to"
				+ " enter a letter, such as d, or a number, such as 4");
		System.out.println("The game will end when a player wins");
		System.out.print("Press 1 and press Enter to start game: ");

		// get user input
		Scanner rules = new Scanner(System.in);
		String understand;

		// make sure user understand rules
		boolean agree = true;
		while (agree) {
			understand = rules.next();
			if (understand.compareTo("1") == 0) {
				agree = false;
			} else {
				System.out.print("Press 1 and press Enter to start game: ");
			}
		}

		System.out.println("\nPlayer 1 will start the game!!");
		System.out.println("--------------------------------------------------------------");

		// shuffle deck
		deck.shuffle();

		// fill user's deck
		initiateDeck(deck, user1Deck);
		initiateDeck(deck, user2Deck);

		// deal 1 card to each pile
		initiatePiles();

		// both players keep playing until game ends
		boolean on = true;
		while (on) {
			// check if player2 deck is empty
			// if true end game and print winner
			if (user2Deck.isEmpty()) {
				on = false;
				break;
			} else {
				System.out.println("\nPlayer 1 turn!!");
				System.out.println("--------------------------------------------------------------");

				// show user deck
				System.out.println("\n---Current Piles---");
				printTable();
				System.out.print("\nPlayer's 1 Deck: ");

				// draw a card from the pile at the beginning of each turn
				user1Deck.add(deck.dealTopCard());
				playerTurn(user1Deck);
			}

			// check if player1 deck is empty
			// if true end game and print winner
			if (user1Deck.isEmpty()) {
				on = false;
				break;
			} else {
				System.out.println("\nPlayer 2 turn!!");
				System.out.println("--------------------------------------------------------------");
				// user2 turn
				System.out.println("\n---Current Piles---");
				printTable();
				System.out.print("Player's 2 Deck: ");
				// printUserDeck(user2Deck);

				// draw a card from the pile at the beginning of each turn
				user2Deck.add(deck.dealTopCard());
				playerTurn(user2Deck);
			}
		}

		// print winner
		winner();
	}

	// player turn
	// they should have three options
	// a. Place a card down
	// b. Move a pile to another pile
	// c. End turn
	private static void playerTurn(ArrayList<Card> userDeck) {
		boolean playerTurn = true;

		// players turn should only end when player decides too!!
		while (playerTurn) {
			// prompt choice for user and store answer
			char choice = action(userDeck);

			switch (choice) {
			case 'a':
				placeCardInDeck(userDeck);
				break;
			case 'b':
				movePile();
				break;
			case 'c':
				playerTurn = false;
				break;
			}
		}
	}

	// allow user to place card down on any 4 piles if move is valid
	// two options
	// 1. player can move a card to standard pile
	// 2. player can move a king into the corner
	private static void placeCardInDeck(ArrayList<Card> userDeck) {
		// get user input
		Scanner input = new Scanner(System.in);
		String cardSelected = ""; // get card choice from user
		String pileSelected = ""; // get pile choice from user
		int pileSelectedNum = -1;

		// have user choose card they want to place
		// make sure user input is valid for pile selection
		boolean choice1 = true;
		while (choice1) {

			// prompt user their card options to pick from
			printOptions(userDeck);

			System.out.print("Choose your card. Enter a, b, or any options available:");
			cardSelected = input.nextLine();
			System.out.println();

			if ((cardSelected.charAt(0) - 'a') < userDeck.size() && checkCard(cardSelected)) {
				choice1 = false;
			} else {
				System.out.println("Error: Enter a valid Letter!!\n" + "Choose a card from the deck shown below\n");
			}
		}

		// make sure user input is valid for pile selection
		boolean choice2 = true;
		while (choice2) {
			// show piles to user
			printPiles();

			// give user option of what pile they want to place card on
			System.out.print("Choose the pile. Enter a number between 1-8: ");
			pileSelected = input.nextLine();

			System.out.println();

			if (checkPile(pileSelected)) {
				pileSelectedNum = Integer.parseInt(pileSelected);

				// make sure user puts a valid answer
				if (pileSelectedNum >= 1 && pileSelectedNum < 8) {
					choice2 = false;
				} else {
					System.out.println("Error: Enter a valid number!!!");
				}
			} else {
				System.out.println("Error: Enter a valid number!!!");
			}
		}

		// check if card placement is valid
		moveCard(userDeck, cardSelected, pileSelectedNum);
		System.out.println("\n---Current Piles---");
		printTable();
	}

	// have player choose two piles
	// first pile should be the card the user wants to move
	// then second pile is where pile 1 is being place on
	private static void movePile() {
		Scanner input = new Scanner(System.in);
		int pile1 = 0; // get pile choice from user
		int pile2 = 0; // get pile choice from user

		// make sure user input is valid for pile selection
		boolean choice1 = true;
		while (choice1) {
			// print piles
			printPiles();

			System.out.print("Pick the pile you want to pick up: Enter any number from 1 - 8: ");
			pile1 = input.nextInt();
			System.out.println();

			if (pile1 >= 1 && pile1 <= 8) {
				choice1 = false;
			} else {
				System.out.println("Error: Enter a valid Letter!!\n" + "Choose a card from the deck shown below\n");
			}
		}

		// make sure user input is valid for pile selection
		boolean choice2 = true;
		while (choice2) {
			// print piles
			printPiles();

			System.out.print("Where do you want to place down the pile? Enter any number from 1 - 8: ");
			pile2 = input.nextInt();
			System.out.println();

			if (pile1 >= 1 && pile1 <= 8) {
				choice2 = false;
			} else {
				System.out.println("Error: Enter a valid Letter!!\n" + "Choose a card from the deck shown below\n");
			}
		}

		ArrayList<Card> pickUpPile = new ArrayList<>();
		ArrayList<Card> recievingPile = new ArrayList<>();

		// check if card placement is valid
		mergePile(pile1, pile2, pickUpPile, recievingPile);
		System.out.println("\n---Current Piles---");
		printTable();
	}

	// action player can do, place card, move a pile, or end turn
	// should return 'a', 'b', or 'c'
	private static char action(ArrayList<Card> userDeck) {
		Scanner input = new Scanner(System.in);
		String user = "";

		// prompt user the three options
		userOptions(userDeck);

		// make sure user puts correct value
		boolean choice = true;
		while (choice) {
			System.out.print("Enter a, b, c for your choice: ");
			user = input.nextLine();

			if (user.compareToIgnoreCase("a") == 0 || user.compareToIgnoreCase("b") == 0
					|| user.compareToIgnoreCase("c") == 0) {
				choice = false;
			} else {
				System.out.println("Error: Enter a, b, or c!!");
			}
		}

		// return user choice
		return user.charAt(0);
	}

	// return the index of the card the user chose
	private static int userChoice(String choice) {
		char ans = 'a';

		for (int i = 0; i < user1Deck.size(); i++) {
			if (choice.charAt(0) == ans) {
				return i;
			}
			ans++;
		}
		return -1;
	}

	// make sure user chooses valid card from deck
	private static boolean checkCard(String cardSelected) {
		if (cardSelected.charAt(0) - 'a' >= 0 && cardSelected.charAt(0) - 'z' < 26)
			return true;
		return false;
	}

	// make sure user chooses valid pile
	private static boolean checkPile(String pileSelected) {
		if (pileSelected.charAt(0) - '0' >= 0 && pileSelected.charAt(0) - '0' <= 8)
			return true;
		return false;
	}

	// check if the card placement is valid
	// e.g queen can only be placed on top of a king
	private static boolean check(ArrayList<Card> pile, Card card) {
		// String[] deckOrder = {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10",
		// "jack", "queen", "king"};
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

		int len = 0;

		// get pile size
		if (pile.size() > 0) {
			len = pile.size() - 1;
		}

		// get last card
		int pileCardValue = deckOrder.get(pile.get(len).toString().toLowerCase().charAt(0));

		// get card
		int cardValue = deckOrder.get(card.toString().toLowerCase().charAt(0));

		// if valid return true
		if (cardValue + 1 == pileCardValue) {
			return true;
		}
		return false;
	}

	// initiate the players deck with 7 cards
	// this should only be called once
	private static void initiateDeck(DeckOfCards deck, ArrayList<Card> userDeck) {
		for (int i = 0; i < 7; i++) {
			userDeck.add(deck.dealTopCard());
		}
	}

	// initiate the 4 piles
	// this should only be called once
	private static void initiatePiles() {
		pile1.add(deck.dealTopCard());
		pile2.add(deck.dealTopCard());
		pile3.add(deck.dealTopCard());
		pile4.add(deck.dealTopCard());
	}

	// make sure a king is being placed in a king corner pile
	private static boolean kingCorner(ArrayList<Card> pile, Card card) {
		if (pile.isEmpty() && card.getFaceName().charAt(0) == 'k')
			return true;
		return false;
	}

	// merge a pile to another if the requirements are met
	private static void mergePile(int pickUp, int receiving, ArrayList<Card> pile1, ArrayList<Card> pile2) {
		// need to check when moving a pile into an empty array
		pile1 = getPile(pickUp);
		pile2 = getPile(receiving);

		// check if card is being placed on a kings corner
		// if so make sure its a king
		if (pile2.isEmpty() && receiving > 4 && kingCorner(pile2, pile1.get(0))) {
			pile2.addAll(pile1);
			pile1.clear();
		} // check if pile is being merged to an empty pile, and it should be a normal
			// pile
		else if (pile2.isEmpty() && receiving < 5) {
			pile2.addAll(pile1);
			pile1.clear();
		} // check if pile is being placed on other pile, and it should be a normal pile
		else if (receiving < 5 && pickUp < 5 && check(pile2, pile1.get(0))) {
			pile2.addAll(pile1);
			pile1.clear();
		} else {
			System.out.println("Cannot stack these two piles!!!");
		}

//		
//		if (pile2.isEmpty() && receiving < 5) {
//			pile2 = new ArrayList<Card>(pile1);
//			pile1.clear();
//		} else if (receiving > 4 && kingCorner(pile2, pile1.get(0))) {//if the pile is empty and card being placed in a king order must a king
//			pile2.addAll(pile1);
//			pile1.clear();
//		}
//		else if (receiving < 5 &&check(pile2, pile1.get(0))) {
//			pile2.addAll(pile1);
//			pile1.clear();
//		} else {
//			System.out.println("Cannot stack these two piles!\nThe " + pile2.get(0)
//					+ " cannot be placed on " + pile1.get(pile1.size() - 1));
//		}
	}

	// check if the game is over
	private static boolean gameOver() {
		if (user1Deck.isEmpty() || user2Deck.isEmpty() || deck.getDeck().isEmpty()) {
			return true;
		}
		return false;
	}

	// print winner
	private static void winner() {
		if (gameOver()) {
			if (user1Deck.isEmpty()) {
				System.out.print("Player 1 is the Winner!!");
			} else if (user2Deck.isEmpty()) {
				System.out.print("Player 2 is the Winner!!");
			} else {

			}
		}
	}

	// check if card can be place down
	private static void moveCard(ArrayList<Card> userDeck, String cardSelected, int pileSelected) {
		// card selected
		int cardSelectedIndex = userChoice(cardSelected);

		if (pileSelected < 5 && userDeck.get(cardSelectedIndex).toString().toLowerCase().charAt(0) != 'k') {
			switch (pileSelected) {
			case 1: // pile 1
				// check if card can be placed
				if (pile1.isEmpty() && userDeck.get(cardSelectedIndex).toString().toLowerCase().charAt(0) != 'k') {
					moveCardHelper(pile1, userDeck.get(cardSelectedIndex));
					userDeck.remove(cardSelectedIndex);
				} else if (check(pile1, userDeck.get(cardSelectedIndex))) {
					// pile1.add(userDeck.get(cardSelectedIndex));
					moveCardHelper(pile1, userDeck.get(cardSelectedIndex));
					userDeck.remove(cardSelectedIndex);
				} else {
					System.out.println("Invalid Move: try another card\n");
				}
				break;
			case 2: // pile 2
				if (pile2.isEmpty() && userDeck.get(cardSelectedIndex).toString().toLowerCase().charAt(0) != 'k') {
					moveCardHelper(pile2, userDeck.get(cardSelectedIndex));
					userDeck.remove(cardSelectedIndex);
				} else if (check(pile2, userDeck.get(cardSelectedIndex))) {
					// pile2.add(userDeck.get(cardSelectedIndex));
					moveCardHelper(pile2, userDeck.get(cardSelectedIndex));
					userDeck.remove(cardSelectedIndex);
				} else {
					System.out.println("Invalid Move: try another card\n");
				}
				break;
			case 3: // pile 3
				if (pile3.isEmpty() && userDeck.get(cardSelectedIndex).toString().toLowerCase().charAt(0) != 'k') {
					moveCardHelper(pile3, userDeck.get(cardSelectedIndex));
					userDeck.remove(cardSelectedIndex);
				} else if (check(pile3, userDeck.get(cardSelectedIndex))) {
					// pile3.add(userDeck.get(cardSelectedIndex));
					moveCardHelper(pile3, userDeck.get(cardSelectedIndex));
					userDeck.remove(cardSelectedIndex);
				} else {
					System.out.println("Invalid Move: try another card\n");
				}
				break;
			case 4: // pile 4
				// check if card can be placed
				if (pile4.isEmpty() && userDeck.get(cardSelectedIndex).toString().toLowerCase().charAt(0) != 'k') {
					moveCardHelper(pile4, userDeck.get(cardSelectedIndex));
					userDeck.remove(cardSelectedIndex);
				} else if (check(pile4, userDeck.get(cardSelectedIndex))) {
					// pile4.add(userDeck.get(cardSelectedIndex));
					moveCardHelper(pile4, userDeck.get(cardSelectedIndex));
					userDeck.remove(cardSelectedIndex);
				} else {
					System.out.println("Invalid Move: try another card\n");
				}
				break;
			case 5: // pile 5
				// check if card can be placed
				// if empty it needs to be a king
				// if not add card as normal
				if (kingCorner1.isEmpty()) {
					if (userDeck.get(cardSelectedIndex).toString().toLowerCase().charAt(0) == 'k') {
						kingCorner1.add(userDeck.get(cardSelectedIndex));
						userDeck.remove(cardSelectedIndex);
					}
				} else if (check(kingCorner1, userDeck.get(cardSelectedIndex))) {
					kingCorner1.add(userDeck.get(cardSelectedIndex));
					userDeck.remove(cardSelectedIndex);
				} else {
					System.out.println("Invalid Move: try another card\n");
				}
				break;
			case 6: // pile 6
				if (kingCorner2.isEmpty()) {
					if (userDeck.get(cardSelectedIndex).toString().toLowerCase().charAt(0) == 'k') {
						kingCorner2.add(userDeck.get(cardSelectedIndex));
						userDeck.remove(cardSelectedIndex);
					}
				} else if (check(kingCorner2, userDeck.get(cardSelectedIndex))) {
					kingCorner2.add(userDeck.get(cardSelectedIndex));
					userDeck.remove(cardSelectedIndex);
				} else {
					System.out.println("Invalid Move: try another card\n");
				}
				break;
			case 7: // pile 7
				// check if card can be placed
				if (kingCorner3.isEmpty()) {
					if (userDeck.get(cardSelectedIndex).toString().toLowerCase().charAt(0) == 'k') {
						kingCorner3.add(userDeck.get(cardSelectedIndex));
						userDeck.remove(cardSelectedIndex);
					}
				} else if (check(kingCorner3, userDeck.get(cardSelectedIndex))) {
					kingCorner3.add(userDeck.get(cardSelectedIndex));
					userDeck.remove(cardSelectedIndex);
				} else {
					System.out.println("Invalid Move: try another card\n");
				}
				break;
			case 8: // pile 8
				// check if card can be placed
				if (kingCorner4.isEmpty()) {
					if (userDeck.get(cardSelectedIndex).toString().toLowerCase().charAt(0) == 'k') {
						kingCorner4.add(userDeck.get(cardSelectedIndex));
						userDeck.remove(cardSelectedIndex);
					}
				} else if (check(kingCorner4, userDeck.get(cardSelectedIndex))) {
					kingCorner4.add(userDeck.get(cardSelectedIndex));
					userDeck.remove(cardSelectedIndex);
				} else {
					System.out.println("Invalid Move: try another card\n");
				}
				break;
			}
		} else {
			System.out.println("Cannot place a King in a normal pile!!!");
		}
	}

	private static void moveCardHelper(ArrayList<Card> pile, Card card) {
		pile.add(card);
	}

	private static ArrayList<Card> getPile(int pileNum) {
		switch (pileNum) {
		case 1: // pile 1
			return pile1;
		case 2: // pile 2
			return pile2;
		case 3: // pile 3
			return pile3;
		case 4: // pile 4
			return pile4;
		case 5: // pile 5
			return kingCorner1;
		case 6: // pile 6
			return kingCorner2;
		case 7: // pile 7
			return kingCorner3;
		}
		return kingCorner4;
	}

	private static void emptyPile(int pileNum) {
		switch (pileNum) {
		case 1: // pile 1
			pile1.clear();
		case 2: // pile 2
			pile2.clear();
		case 3: // pile 3
			pile3.clear();
		case 4: // pile 4
			pile4.clear();
		case 5: // pile 5
			kingCorner1.clear();
		case 6: // pile 6
			kingCorner2.clear();
		case 7: // pile 7
			kingCorner3.clear();
		case 8: // pile 8
			kingCorner4.clear();
		}
	}

	/*
	 * all methods below are for printing purposes
	 */

	// print current state of all the piles
	private static void printPiles() {
		// print Piles
		System.out.println("\n---------------Current Piles---------------");
		System.out.println("Pile #" + 1 + ": " + print(pile1));
		System.out.println("Pile #" + 2 + ": " + print(pile2));
		System.out.println("Pile #" + 3 + ": " + print(pile3));
		System.out.println("Pile #" + 4 + ": " + print(pile4));
		System.out.println("---------------Current Kings Corners---------------");
		System.out.println("Pile #" + 5 + ": " + print(kingCorner1));
		System.out.println("Pile #" + 6 + ": " + print(kingCorner2));
		System.out.println("Pile #" + 7 + ": " + print(kingCorner3));
		System.out.println("Pile #" + 8 + ": " + print(kingCorner4));
	}

	// main user menu
	// this should be shown to user when its their turn
	private static void userOptions(ArrayList<Card> userDeck) {
		// show user's deck
		System.out.print("\nYour deck: ");
		printUserDeck(userDeck);
		System.out.println();
		System.out.println("\n\nWhat would you like to do?");
		System.out.println("a)\tPlace a card\nb)\tMove a pile\nc)\tEnd Turn");
		System.out.println("\nYour Choice: ");
	}

	// print given pile
	private static String print(ArrayList<Card> pile) {
		String result = "";
		for (Card card : pile) {
			result += card + ", ";
		}
		return result += "___";
	}

	// print all piles
	private static void printTable() {
		System.out.println("Pile 1: " + print(pile1));
		System.out.println("Pile 2: " + print(pile2));
		System.out.println("Pile 3: " + print(pile3));
		System.out.println("Pile 4: " + print(pile4));

		System.out.println("King Corner 1: " + print(kingCorner1));
		System.out.println("King Corner 2: " + print(kingCorner2));
		System.out.println("King Corner 3: " + print(kingCorner3));
		System.out.println("King Corner 4: " + print(kingCorner4));
	}

	// print options user can pick from their OWN pile
	private static void printOptions(ArrayList<Card> pile) {
		char a = 'a';
		for (int i = 0; i < pile.size(); i++) {
			System.out.println(a + ". " + pile.get(i));
			a++;
		}
	}

	// print the user deck
	private static void printUserDeck(ArrayList<Card> deck) {
		for (Card card : deck) {
			System.out.print(card + ", ");
		}
	}
}