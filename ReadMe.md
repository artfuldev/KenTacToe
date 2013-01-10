#KenTacToe
* Author(s): Kenshin Himura *(Sudarsan Balaji)*, Girish Koundinya *(pending contribution)*
* License: *GNU GPL v3 and CC-BY-NC-SA Licenses* (see gpl.txt and ccbyncsa.txt)
* Application Version: 1.2
* ReadMe Version: 1.1

##Description
A basic Tic Tac Toe (TTT) game engine with a vision to be able to present an AI capable of playing Tic Tac Toe with humans on boards upto 255x255 boxes.
Only basic framework has been built.
Game has grid and players and is made up of moves and searches.
Game has minimax search with alpha-beta pruning. Still a bit buggy though.


##Updates
Efforts are being made to make the source code readble by adding comments wherever required.
###1.0
* Game fully functional now. Rules are followed, AI picks move (though not the best!). Game ends when win conditions are met. AI to be implemented with minimax or something soon. Version updated to 1.0.
* Table.java now with full commentary.
* All java files are now with full @javadoc commentary. Enjoy learning!
###1.1
* NegaMax search implemented.
###1.2
* Minimax with Alpha-Beta Pruning implemented, though a bit buggy.
* More robust evaluation function for 3x3 boxes.

##Visions
* PVS with Alpha Beta Negamax approach envisioned.
* Play on any grid size (supported by int data type!).