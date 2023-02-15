# Kings-Corners
a game of kings corners implemented with java using Object Oriented Programming, user input, and JUnit testing.

--log

--2-14-23
Updated the visuals commands so who ever is playing can read better

--2-13-23
Game is functional
Added a PlayGame class so the user just needs to run this class and start using application
Bugs fixed: when user puts wrong input for any question, the application will not crash anymore. Solved mutiple bugs

Debugging
Found more issues:
Sometimes the program wont add a card to the players deck, this can possibly prevent the player from making any moves
when the deck runs out of cards, it will start adding null to the players hand, which will prevent the player from winning

--2-9-23
Debugging
Current Known Issues:
Any card can be place in King pile, ONLY kings should be place in the king pile
Get an java.lang.NumberFormatException when not typing a number when user needs to choose a pile to put card down
get an java.util.InputMismatchException when user does not enter number when trying to move piles
Player can move a king to a normal pile: kings should only be placed in the kings corner pile


--2-8-23
Game is functional
game is 95% functional. if both players enter valid input, the application will be a successful. Needs to be debugged more

--2-8-2023
90% functional
Player can now place a card in an empty pile, and a king can be put down in an empty king pile. The player can also move piles if the move is valid. Still needs to be debugged
ArrayIndexOutOfBoundsException when kings corner pile has a total of 3 cards

--2-7-2023
Project is more 
Taking turns now works, piles are now properly updated when a player adds a card.
Merge Pile does not work yet.functional

--2-6-2023
Major Update to Game Class
Turn taking is now functional between players. Removed unnecessary methods, added more comments for more readable code, organized methods. 
Issues still present: when player chooses the card and the pile, if valid the card needs to be place onto the selected pile and removed from the players hand. If the move is invalid the user needs to be prompted that the move is invalid and should be prompted the three choices again: place card, move pile, or end turn

--2-6-2023
Pushed the current stage of the product.
--issues that need to be fixed:
Need to establish how two players are going to take turns, how to present the choices the user has, and the program needs to end when a user wins. 
