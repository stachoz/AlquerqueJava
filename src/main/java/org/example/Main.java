package org.example;

public class Main {
    public static void main(String[] args) {
        Player p1 = new Player("p1", PawnColor.WHITE);
        Player p2 = new Player("p2", PawnColor.BLACK);
        Board board = new Board();
        GameBrain gameBrain = new GameBrain(board, p1, p2);
        board.printBoard();
        gameBrain.makeMove();
        board.printBoard();
    }
}