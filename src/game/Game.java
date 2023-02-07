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

	public static void main(String[] args) {
		// start game simulation
		System.out.println("Welcome to my program Kings Corner!!!");
		System.out.println("This is a two player game, and there can only be 1 winner.");
		System.out.println("Players should know the general rules of Kings Corner." 
				+"\nIf not please take a look at the projects readme file.");
		System.out.println("There will be 3 options for a user, and they will have to"
				+ " enter a letter, such as d, or a number, such as 4");
		System.out.println("The game will end when a player wins");
		System.out.print("Press 1 and press Enter to start game: ");
		
		Scanner rules = new Scanner(System.in);
		String understand;
		
		//make sure user understand rules
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
		
		game();
	}

	// set up game
	public static void game() {
		// shuffle deck
		deck.shuffle();
		
		// fill user's deck
		initiateDeck(deck, user1Deck);
		initiateDeck(deck, user2Deck);

		// deal 1 card to each pile
		initiatePiles();
		
		// both players keep playing until game ends
		boolean on = true;
		while(on) {
			
			//check if player2 deck is empty
			//if true end game and print winner
			if (user2Deck.isEmpty()) {
				on = false;
				break;
			} else {
				System.out.println("\nPlayer 1 turn!!");
				System.out.println("--------------------------------------------------------------");
				//player 1 turn
				//show user deck
				//show user piles
				System.out.print("Player's 1 Deck: ");
				printUserDeck(user1Deck);
				System.out.println("\n---Current Piles---");
				printTable();
				playerTurn(user1Deck);
			}
			
			//check if player1 deck is empty
			//if true end game and print winner
			if (user1Deck.isEmpty()) {
				on = false;
				break;
			} else {
				System.out.println("\nPlayer 2 turn!!");
				System.out.println("--------------------------------------------------------------");
				//user2 turn
				System.out.print("Player's 2 Deck: ");
				printUserDeck(user2Deck);
				System.out.println("---Current Piles---");
				printTable();
				playerTurn(user2Deck);
			}
		}
		
		//print winner
		winner();
	}
	
	//player turn
	public static void playerTurn(ArrayList<Card> userDeck) {
		//they should have three options
		//menu1:
			//a. Place a card down
			//b. Move a pile to another pile
			//c. End turn
		
		boolean playerTurn = true;
		
		//players turn should only end when player decides too!!
		while (playerTurn) {
			//prompt choice for user and store answer
			char choice = action();
			
			switch(choice) {
			case 'a':
				placeCardInDeck(userDeck);
				break;
			case 'b':
				//mergePile();
				break;
			case 'c':
				playerTurn = false;
				break;
			}
			
		}
	}

	//action player can do, place card, move a pile, or end turn
	//should return 'a', 'b', or 'c'
	public static char action() {
		Scanner input = new Scanner(System.in);
		String user = "";
		
		//prompt user the three options
		userOptions();
		
		//make sure user puts correct value
		//use a while loop to ensure the program continues running
		boolean choice = true;
		while (choice) {
			System.out.print("Enter a, b, c for your choice: ");
			user = input.nextLine();
			
			//make sure user puts a valid answer
			if (user.compareToIgnoreCase("a") == 0 
					|| user.compareToIgnoreCase("b") == 0 
					||user.compareToIgnoreCase("c") == 0) {
				choice = false;
			} else {
				System.out.println("Error: Enter a, b, or c!!");
			}
		}
		
		//return user choice
		return user.charAt(0);
	}
	
	//allow user to place card down on any 4 piles if move is valid
	private static void placeCardInDeck(ArrayList<Card> userDeck) {
		//two options
		//1. player can move a card to standard pile
		//2. player can move a king into the corner
		
		Scanner input = new Scanner(System.in);
		String cardSelected = ""; //get card choice from user
		int pileSelected = 0; //get pile choice from user
		
		boolean leave = true; //this will allow the user to place cards until they are done
		while (leave) {
			
			//have user choose card they want to place
			//make sure user input is valid for pile selection
			boolean choice1 = true;
			while (choice1) {
				//prompt user their card options to pick from
				printOptions(user1Deck);
				
				System.out.print("Choose your card. Enter a, b, or any options available:");
				cardSelected = input.nextLine();
				System.out.println();
				
				if ( (cardSelected.charAt(0) - 'a') < userDeck.size()) {
					choice1 = false;
				} else {
					System.out.println("Error: Enter a valid Letter!!\n"
							+ "Choose a card from the deck shown below\n");
				}
			}
			
			
			//make sure user input is valid for pile selection
			boolean choice2 = true;
			while (choice2) {
				//show piles to user
				printPiles();
				
				//give user option of what pile they want to place card on
				System.out.print("Choose the pile. Enter a number between 1-8: ");
				pileSelected = input.nextInt();
				
				//make sure user puts a valid answer
				if (pileSelected >= 1 && pileSelected < 8) {
					choice2 = false;
				} else {
					System.out.println("Error: Enter a valid number!!!");
				}
				
			}
			
			System.out.println("end-----------------------------------------");
			leave = false;
		}
		
	}
	
	//return the index of the card the user chose
	public static int userChoice(String choice) {
		char ans = 'a';

		for (int i = 0; i < user1Deck.size(); i++) {
			if (choice.charAt(0) == ans) {
				return i;
			}
			ans++;
		}
		return -1;
	}

	// check if the card placement is valid
	//place card to pile
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

		// compare last card in pile to incoming card
		
		//get pile size
		int len = pile.size() - 1; 
		
		//get last card
		int pileCardValue = deckOrder.get(pile.get(len).toString().toLowerCase().charAt(0));
		
		//get card
		int cardValue = deckOrder.get(card.toString().toLowerCase().charAt(0));
		// System.out.println("game class method cardValue: " + cardValue);

		//if valid return true
		if (cardValue + 1 == pileCardValue) {
			//pile.add(card);
			return true;
		}
		return false;
	}

	private static void initiateDeck(DeckOfCards deck, ArrayList<Card> userDeck) {
		for (int i = 0; i < 7; i++) {
			userDeck.add(deck.dealTopCard());
		}
	}

	private static void initiatePiles() {
		pile1.add(deck.dealTopCard());
		pile2.add(deck.dealTopCard());
		pile3.add(deck.dealTopCard());
		pile4.add(deck.dealTopCard());
	}

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

	public static void kingCorner(ArrayList<Card> pile, Card card) {
		if (pile.isEmpty() && card.getFaceName().charAt(0) == 'K')
			pile.add(card);
		else
			throw new IllegalArgumentException("You can only place a King!!");
	}

	// merge a pile to another if the requirements are met
	//TODO: .clear will not work because it will also clear it for pile1
	public static void mergePile(ArrayList<Card> pile1, ArrayList<Card> pile2) {
		if (check(pile1, pile2.get(0))) {
			pile1.addAll(pile2);
			pile2.clear();
		} else {
			throw new IllegalArgumentException("Cannot stack these two piles!\nThe " + pile2.get(0)
					+ " cannot be placed on " + pile1.get(pile1.size() - 1));
		}
	}
	
	//check if the game is over
	public static boolean gameOver() {
		if (user1Deck.isEmpty() || user2Deck.isEmpty() || deck.getDeck().isEmpty()) {
			return true;
		}
		return false;
	}
	
	//print winner
	public static String winner() {
		if (gameOver()) {
			if (user1Deck.isEmpty()) {
				return "Player 1 is the Winner!!";
			} else if (user2Deck.isEmpty()) {
				return "Player 2 is the Winner!!";
			}
		}
		return "There is no Winner!!!";
	}
	
	/*
	 * all methods below are for printing purposes 
	 */
	
	// main user menu
	// this should be shown to user when its their turn
	private static void userOptions() {
		//System.out.println();
		//printPiles();
		System.out.println("\nWhat would you like to do?");
		System.out.println("a)\tPlace a card\nb)\tMove a pile\nc)\tEnd Turn");
		System.out.println("\nYour Choice: ");
	}
	
	//print given pile
	public static String print(ArrayList<Card> pile) {
		String result = "";
		for (Card card : pile) {
			result = card + ", ";
		}
		return result + "___";
	}

	//print all piles
	public static void printTable() {
		System.out.println("Pile 1: " + print(pile1));
		System.out.println("Pile 2: " + print(pile2));
		System.out.println("Pile 3: " + print(pile3));
		System.out.println("Pile 4: " + print(pile4));

		System.out.println("King Corner 1: " + print(kingCorner1));
		System.out.println("King Corner 2: " + print(kingCorner2));
		System.out.println("King Corner 3: " + print(kingCorner3));
		System.out.println("King Corner 4: " + print(kingCorner4));
	}

	//print options user can pick from their OWN pile
	public static void printOptions(ArrayList<Card> pile) {
		char a = 'a';
		for (int i = 0; i < pile.size(); i++) {
			System.out.println(a + ". " + pile.get(i));
			a++;
		}
	}

	//print the user deck
	public static void printUserDeck(ArrayList<Card> deck) {
		for (Card card : deck) {
			System.out.print(card + ", ");

		}
	}
}




////methods not needed
//private static ArrayList<Card> returnPile(int pile) {
//	ArrayList<Card> thisPile;
//	if (pile == 1) {
//		thisPile = (ArrayList<Card>) pile1.clone();
//		return thisPile;
//	} else if (pile == 2) {
//		thisPile = (ArrayList<Card>) pile2.clone();
//		return thisPile;
//	} else if (pile == 3) {
//		thisPile = (ArrayList<Card>) pile3.clone();
//		return thisPile;
//	} else if (pile == 4) {
//		thisPile = (ArrayList<Card>) pile4.clone();
//		return thisPile;
//	} else if (pile == 5) {
//		thisPile = (ArrayList<Card>) kingCorner1.clone();
//		return thisPile;
//	} else if (pile == 6) {
//		thisPile = (ArrayList<Card>) kingCorner2.clone();
//		return thisPile;
//	} else if (pile == 7) {
//		thisPile = (ArrayList<Card>) kingCorner3.clone();
//		return thisPile;
//	}
//	thisPile = (ArrayList<Card>) kingCorner4.clone();
//	return thisPile;
//}