package org.example.game;

import org.example.enums.BoardDistance;
import org.example.enums.PawnColor;

import java.util.Random;

public class ComputerMove extends GameBrain{
    public ComputerMove(Board board, Player p1, Player p2) {
        super(board, p1, p2);
    }

    @Override
    public void makeMove() {
        char currentColor = getCurrentTurnPlayer().getColor().getValue();
        if(pawnsAbleToCapture.hasAnyPawn()){
            Random random = new Random();
            int randomIndex = random.nextInt(pawnsAbleToCapture.getLength());
            Pawn pawnToMove = pawnsAbleToCapture.getPawn(randomIndex);
            findAllPossibleCaptures(pawnToMove, enemyColor(currentColor));
            randomIndex = random.nextInt(pawnToMove.getLengthOfPossibleCaptures());
            Coordinates moveCoordinates = pawnToMove.getPossibleCapture(randomIndex);
            int capturedX = capturedCoordinate(pawnToMove.getX(), moveCoordinates.getX());
            int capturedY = capturedCoordinate(pawnToMove.getY(), moveCoordinates.getY());
            board.capture(pawnToMove.getX(), pawnToMove.getY(), capturedX, capturedY, moveCoordinates.getX(), moveCoordinates.getY());
        }
    }

    private void findAllPossibleCaptures(Pawn pawn, char enemyColor){
        int x = pawn.getX();
        int y = pawn.getY();
        int distance = BoardDistance.CAPTURE.getValue();
        int[] directions = {-distance, 0, distance};
        for(int directionX : directions){
            for(int directionY : directions){
                if(directionX == 0 && directionY == 0) continue;
                int moveX = x + directionX;
                int moveY = y + directionY;
                int capturedX = capturedCoordinate(x, moveX);
                int capturedY = capturedCoordinate(y, moveY);
                if(moveValidator.isThisCapturePossible(x, y, capturedX, capturedY, moveX, moveY, enemyColor, PawnColor.EMPTY.getValue())){
                    pawn.addPossibleCapture(moveX, moveY);
                }
            }
        }
    }
}
