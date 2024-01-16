# Alquerque 
- arabic game similiar to checkers.

## About project

This is console application that allows users to play Alquerque Game following the established rules. Players can move, capture, 
perform multi-captures and penalize the enemy player when he miss capture opportunity. The application includes robust input 
validation to prevent illegal moves. 

The main feature of this application is the option for users to have the computer make moves on
their behalf. Logic of computer move is as follows:  
1. Computer is looking for pawns which can capture, randomly select one of them and capture as more enemy pawns as it is possible.
2. If no capturing moves are possible, the computer makes random pawn using any available pawn.

### Classes containg main logic:

- GameBrain - contains mechanisms of moving, capturing and taking off pawns. 
- ComputerMove - subclass of GameBrain. Make move automatically if player wants to.
- MoveValidator - check if move is legal. This class is covered with **Unit Tests** 
