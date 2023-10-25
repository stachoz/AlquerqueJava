package org.example.game;

public class ComputerMove extends GameBrain{
    public ComputerMove(Board board, Player p1, Player p2) {
        super(board, p1, p2);
    }

    @Override
    public void makeMove() {
        if(pawnsAbleToCapture.hasAnyPawn()){

        }
    }
}
