# Classic Games

This is a Java Project where I want to implement different board games.

## Goals
- To implement a multiplayer version of these games
    - Naughts & Crosses - Implemented
    - Connect 4 - Implemented
    - Chess - This is the hardest

- To implement the Minimax algorithm for all three games above - I will obviously try and speed up the process (Using alph-beta pruning among other things)

- To implement a UI
    - Intially just a Command Line interface
    - Ultimately a Graphical User Interface
    
  - AI
    - Naughts & Crosses.  Uses MiniMax (my own version created from pseudocode) and MiniMax with Alpha Beta Pruning (uses pseudocode check credit below).  There is a 50:50 chance of which one the game uses.
    - Connect Four has AI which takes hours.  (Had it running for 2 hours and still hadn't made a move)
    
## Credit
- Miqing Li - Taught AI2 and taught theory of MiniMax (and Alpha Beta Pruning).  The MiniMax code is adapated from his pseudocode
- https://www.javatpoint.com/ai-alpha-beta-pruning - Adapts some of this code for Alpha Beta Pruning