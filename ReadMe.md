#KenTacToe
* Author(s): Kenshin Himura *(Sudarsan Balaji)*
* License: *GNU GPL v3 and CC-BY-NC-SA Licenses* (see gpl.txt and ccbyncsa.txt)
* Application Version: 1.4
* ReadMe Version: 1.3

##Description
A basic Tic Tac Toe (TTT) game engine with a vision to be able to present an AI capable of playing Tic Tac Toe with humans on boards upto 255x255 boxes.
Only basic framework has been built.
Game has grid and players and is made up of moves and searches.
Game has minimax search with alpha-beta pruning. Still a bit buggy though.


##Updates

###Version 1.4
* Evaluation function made more robust.
* Added extra features to evaluate.
* Minor bugs removed.
* AI still buggy, have to fine-tune the evaluation.

###Version 1.3
* Minor bug and stability updates.
* Evaluation function updated to work with any grid size (not restricted to 3x3 box any more).
* AI almost still the same, with little to no modification.
* Scoring system has been updated to double type instead of float to allow for more range of numbers to be used.

###Version 1.2
* Minimax with Alpha-Beta Pruning implemented, though a bit buggy.
* More robust evaluation function for 3x3 boxes.

###Version 1.1
* NegaMax search implemented.

###Version 1.0
* Game fully functional now. Rules are followed, AI picks move (though not the best!). Game ends when win conditions are met. AI to be implemented with minimax or something soon.
* Table.java now with full commentary.
* All java files are now with full @javadoc commentary. Enjoy learning!

###Misc
* Efforts have taken to make the source code readable by adding comments wherever absolutely required.
* Additional commentary will be provided if required (or on request).
* Prime aim of this project is to make legible and understandable well-commented code, but that doesn't mean every line of code has been commented. If additional assistance is required by anyone reading the code, please do post a message asking for such assistance as can be provided (basically, more comments in the source code, that's all!).
* ReadMe updated to reflect version changes chronologially now.

##Visions
* PVS with Alpha Beta Negamax approach envisioned.
* Play on any grid size (supported by int data type!).