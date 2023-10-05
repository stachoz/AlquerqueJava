package org.example;

public class MoveValidator {
    private Board board;
    private int boardSize;
    public MoveValidator(Board board) {
        this.board = board;
        this.boardSize = board.getSize();
    }

    public boolean isMovePossible(int x1, int y1, int x2, int y2){
        int xDiff = x2 - x1;
        int yDiff = y2 - y1;
        if(xDiff == 0 && yDiff == -2 && canMoveLeft(y1)) return true;
        else if(xDiff == 0 && yDiff == 2 && canMoveRight(y1)) return true;
        else if(xDiff == 2 && yDiff == 0 && canMoveDown(x1)) return true;
        else if(xDiff == -2 && yDiff == 0 && canMoveUp(x1)) return true;
        else if(xDiff == -2 && yDiff == -2 && canMoveUpperLeftBevel(x1, y1)) return true;
        else if(xDiff == -2 && yDiff == 2 && canMoveUpperRightBevel(x1, y1)) return true;
        else if(xDiff == 2 && yDiff == -2 && canMoveLowerLeftBevel(x1, y1)) return true;
        else if(xDiff == 2 && yDiff == 2 && canMoveLowerRightBevel(x1, y1)) return true;
        return false;
    }

    public boolean isCapturePossible(int x1, int y1, int x2, int y2){
        int xDiff = x2 - x1;
        int yDiff = y2 - y1;

    }
    boolean canMoveUp(int xCoordinate) {
        return xCoordinate > 0;
    }

    boolean canMoveDown(int xCoordinate){
        return xCoordinate < boardSize;
    }

    boolean canMoveLeft(int yCoordinate){
        return yCoordinate > 0;
    }

    boolean canMoveRight(int yCoordinate){
        return yCoordinate < board.getSize();
    }

    boolean canMoveUpperLeftBevel(int x1, int y1){
        return (x1 >= 2 && y1 >= 2 && board.getBoardElement(x1 - 1, y1 - 1) == '\\');
    }

    boolean canMoveUpperRightBevel(int x, int y){
        return (x >= 2 && y <= boardSize - 2 && board.getBoardElement(x - 1, y + 1) == '/');
    }

    boolean canMoveLowerLeftBevel(int x, int y){
        return (x <= boardSize - 2 && y >= 2 && board.getBoardElement(x + 1, y - 1) == '/');
    }

    boolean canMoveLowerRightBevel(int x, int y){
        return (x <= boardSize - 2 && y <= boardSize - 2 && board.getBoardElement(x + 1, y + 1) == '\\');
    }
}
