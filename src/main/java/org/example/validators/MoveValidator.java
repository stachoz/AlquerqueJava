package org.example.validators;

import org.example.game.Board;
import org.example.enums.PawnColor;

public class MoveValidator {
    private final Board board;
    private final int boardSize;
    public MoveValidator(Board board) {
        this.board = board;
        this.boardSize = board.getSize();
    }

    public boolean isThisMovePossible(int pawnX, int pawnY, int moveX, int moveY, char emptyFieldColor){
        if(!(areCoordinatesInBoardRange(pawnX, pawnY) && areCoordinatesInBoardRange(moveX, moveY))) return false;
        if(board.getBoardElement(moveX, moveY) != emptyFieldColor) return false;
        int xDiff = moveX - pawnX;
        int yDiff = moveY - pawnY;
        if(xDiff == 0 && yDiff == -2 && canMoveLeft(pawnY)) return true;
        else if(xDiff == 0 && yDiff == 2 && canMoveRight(pawnY)) return true;
        else if(xDiff == 2 && yDiff == 0 && canMoveDown(pawnX)) return true;
        else if(xDiff == -2 && yDiff == 0 && canMoveUp(pawnX)) return true;
        else if(xDiff == -2 && yDiff == -2 && canMoveUpperLeftBevel(pawnX, pawnY)) return true;
        else if(xDiff == -2 && yDiff == 2 && canMoveUpperRightBevel(pawnX, pawnY)) return true;
        else if(xDiff == 2 && yDiff == -2 && canMoveLowerLeftBevel(pawnX, pawnY)) return true;
        else return xDiff == 2 && yDiff == 2 && canMoveLowerRightBevel(pawnX, pawnY);
    }

    public boolean isThisCapturePossible(int pawnX, int pawnY, int capturedX, int capturedY, int moveX, int moveY, char enemyColor, char emptyFieldColor){
        if (!(areCoordinatesInBoardRange(pawnX, pawnY) && areCoordinatesInBoardRange(moveX, moveY))) return false;
        return (isThisMovePossible(pawnX, pawnY, capturedX, capturedY, enemyColor) && isThisMovePossible(capturedX, capturedY, moveX, moveY, emptyFieldColor)
                && board.getBoardElement(moveX, moveY) == PawnColor.EMPTY.getValue()
        );
    }
    public boolean canMoveUp(int xCoordinate) {
        return xCoordinate > 0;
    }

    public boolean canMoveDown(int xCoordinate){
        return xCoordinate < boardSize;
    }

    public boolean canMoveLeft(int yCoordinate){
        return yCoordinate > 0;
    }

    public boolean canMoveRight(int yCoordinate){
        return yCoordinate < board.getSize();
    }

    public boolean canMoveUpperLeftBevel(int x1, int y1){
        return (x1 >= 2 && y1 >= 2 && board.getBoardElement(x1 - 1, y1 - 1) == '\\');
    }

    public boolean canMoveUpperRightBevel(int x, int y){
        return (x >= 2 && y <= boardSize - 2 && board.getBoardElement(x - 1, y + 1) == '/');
    }

    public boolean canMoveLowerLeftBevel(int x, int y){
        return (x <= boardSize - 2 && y >= 2 && board.getBoardElement(x + 1, y - 1) == '/');
    }

    public boolean canMoveLowerRightBevel(int x, int y){
        return (x <= boardSize - 2 && y <= boardSize - 2 && board.getBoardElement(x + 1, y + 1) == '\\');
    }

    public boolean areCoordinatesInBoardRange(int x, int y){
        return x <= boardSize && y <= boardSize && x >= 0 && y >= 0;
    }

}
