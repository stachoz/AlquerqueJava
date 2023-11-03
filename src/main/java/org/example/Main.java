package org.example;

import org.example.enums.PawnColor;
import org.example.game.*;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        Player p1 = new Player(PawnColor.WHITE);
        Player p2 = new Player(PawnColor.BLACK);
        GameBrain gameBrain = new GameBrain(board, p1, p2);
        ComputerMove computerMove = new ComputerMove(board, p1, p2);
        GameMenu gameMenu = new GameMenu();
        gameBrain.setPlayersNickname();
        board.printBoard();
        while(true){
            gameBrain.printTurn();
            int choice = gameMenu.moveMenu();
            if(choice == 1) {
                gameBrain.makeMove();
            }
            else if (choice == 2) {
                computerMove.makeMove();
            }
            else if(choice == 3) {
                if(gameBrain.draw()) break;
                continue;
            }
            else if(choice == 4){
                if(gameBrain.surrender()) break;
                continue;
            }
            if(computerMove.isGameEnd()) {
                gameBrain.endGame();
                break;
            }
            board.printBoard();
        }
    }
}