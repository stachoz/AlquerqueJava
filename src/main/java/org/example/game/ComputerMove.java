package org.example.game;

import org.example.enums.BoardDistance;
import org.example.enums.PawnColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerMove extends GameBrain{
    private final static Random random = new Random();
    public ComputerMove(Board board, Player p1, Player p2) {
        super(board, p1, p2);
    }

    @Override
    public void makeMove() {
        char currentColor = getCurrentTurnPlayer().getColor().getValue();
        findPawnsAbleToCapture(currentColor);
        if(pawnsAbleToCapture.hasAnyPawn()){
            Coordinates pawnToMoveCo = getPawnToMoveCoordinates(currentColor);
            do {
                Pawn pawnToMove = new Pawn(pawnToMoveCo);
                Coordinates moveCo = getMoveCoordinates(pawnToMove);
                int pawnX = pawnToMoveCo.getX();
                int pawnY = pawnToMoveCo.getY();
                int moveX = moveCo.getX();
                int moveY = moveCo.getY();
                int capturedX = capturedCoordinate(pawnX, moveX);
                int capturedY = capturedCoordinate(pawnY, moveY);
                board.capture(pawnX, pawnY, capturedX, capturedY, moveX, moveY);
                System.out.printf("Computer capture (" + pawnX + "," + pawnY + ") ==> (" + moveX + "," + moveY + ")");
                pawnToMoveCo = moveCo;
            } while(canPawnMove(pawnToMoveCo.getX(), pawnToMoveCo.getY(), currentColor, BoardDistance.CAPTURE));
        } else {
            List<Coordinates> movablePawns = new ArrayList<>();

        }
    }

    @Override
    protected Coordinates getPawnToMoveCoordinates(char currentColor) {
        List<Pawn> allPawns = pawnsAbleToCapture.getAllPawns();
        int randomIndex = random.nextInt(allPawns.size());
        Pawn randomPawn = allPawns.get(randomIndex);
        return new Coordinates(randomPawn.getX(), randomPawn.getY());
    }

    protected Coordinates getMoveCoordinates(Pawn pawnToMove) {
        findAllPossibleCaptures(pawnToMove);
        int size = pawnToMove.capturedSize();
        int randomIndex = random.nextInt(size);
        return pawnToMove.getPossibleCapture(randomIndex);
    }

    private void findAllPossibleCaptures(Pawn pawn){
        char enemyColor = enemyColor(getCurrentTurnPlayer().getColor().getValue());
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
