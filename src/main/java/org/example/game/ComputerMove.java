package org.example.game;

import org.example.enums.BoardDistance;
import org.example.enums.PawnColor;

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
                decreasePawnNum(enemyColor(currentColor));
                board.printBoard();
                System.out.println("Computer capture (" + pawnX + "," + pawnY + ") ==> (" + moveX + "," + moveY + ")");
                pawnToMoveCo = moveCo;
            } while(canPawnMove(pawnToMoveCo.getX(), pawnToMoveCo.getY(), currentColor, BoardDistance.CAPTURE));
        } else {
            PawnsContainer pawnsAbleToMove = findPawnsAbleToMove(currentColor);
            int randomIndex = random.nextInt(pawnsAbleToMove.getSize());
            Pawn pawnToMove = pawnsAbleToMove.getPawn(randomIndex);
            findAllPossibleMoveByType(pawnToMove, BoardDistance.MOVE);
            int randomMoveIndex = random.nextInt(pawnToMove.movesSize());

            Coordinates randomMove = pawnToMove.getPossibleMove(randomMoveIndex);
            board.movePawn(pawnToMove.getX(), pawnToMove.getY(), randomMove.getX(), randomMove.getY());
        }
        moveCounter++;
    }

    @Override
    protected Coordinates getPawnToMoveCoordinates(char currentColor) {
        List<Pawn> allPawns = pawnsAbleToCapture.getAllPawns();
        int randomIndex = random.nextInt(allPawns.size());
        Pawn randomPawn = allPawns.get(randomIndex);
        return new Coordinates(randomPawn.getX(), randomPawn.getY());
    }

    protected Coordinates getMoveCoordinates(Pawn pawnToMove) {
        findAllPossibleMoveByType(pawnToMove, BoardDistance.CAPTURE);
        int size = pawnToMove.capturedSize();
        int randomIndex = random.nextInt(size);
        return pawnToMove.getPossibleCapture(randomIndex);
    }

    private void findAllPossibleMoveByType(Pawn pawn, BoardDistance boardDistanceMoveType){
        char enemyColor = enemyColor(getCurrentTurnPlayer().getColor().getValue());
        int x = pawn.getX();
        int y = pawn.getY();
        int distance = boardDistanceMoveType.getValue();
        int[] directions = {-distance, 0, distance};
        for(int directionX : directions){
            for(int directionY : directions){
                if(directionX == 0 && directionY == 0) continue;
                int moveX = x + directionX;
                int moveY = y + directionY;
                if(boardDistanceMoveType == BoardDistance.MOVE && moveValidator.isThisMovePossible(x, y, moveX, moveY, PawnColor.EMPTY.getValue())) pawn.addPossibleMove(moveX, moveY);
                else if(boardDistanceMoveType == BoardDistance.CAPTURE){
                    int capturedX = capturedCoordinate(x, moveX);
                    int capturedY = capturedCoordinate(y, moveY);
                    if(moveValidator.isThisCapturePossible(x, y, capturedX, capturedY, moveX, moveY, enemyColor, PawnColor.EMPTY.getValue())){
                        pawn.addPossibleCapture(moveX, moveY);
                    }
                }
            }
        }
    }

    private PawnsContainer findPawnsAbleToMove(char currentColor){
        int size = board.getSize();
        PawnsContainer pawnsAbleToMove = new PawnsContainer();
        for(int i = 0; i <= size; i++){
            for(int j = 0; j <= size; j++){
                if(board.getBoardElement(i, j) == currentColor && canPawnMove(i, j, currentColor, BoardDistance.MOVE)) {
                    pawnsAbleToMove.addPawn(new Pawn(i, j));
                }
            }
        }
        return pawnsAbleToMove;
    }
}
