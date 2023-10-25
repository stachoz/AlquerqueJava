package org.example;

import org.example.enums.PawnColor;
import org.example.game.*;

public class Main {
    public static void main(String[] args) {
        Player p1 = new Player("p1", PawnColor.WHITE);
        Player p2 = new Player("p2", PawnColor.BLACK);
        Board board = new Board();
        GameBrain gameBrain = new GameBrain(board, p1, p2);
        ComputerMove computerMove = new ComputerMove(board, p1, p2);
        GameMenu gameMenu = new GameMenu(p1, p2);
        while(true){
            gameBrain.printTurn();
            int choice = gameMenu.moveMenu();
            board.printBoard();
            if(choice == 1) {
                gameBrain.makeMove();
            }
            else if (choice == 2) {
                computerMove.makeMove();
            }
            else if(choice == 3) {
                // draw
            }
            else if(choice == 4){
                // surrender
            }
            board.printBoard();
        }
    }
}