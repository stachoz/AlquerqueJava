package org.example;

public class GameBrain {
    final private Board board;
    final private Player p1;
    final private Player p2;
    private int moveCounter = 0;

    public GameBrain(Board board, Player p1, Player p2){
        this.board = board;
        this.p1 = p1;
        this.p2 = p2;
    }

    public void makeMove(){
        Player currentPlayer = getCurrentTurnPlayer();
        printTurn(currentPlayer);
        char currentColor = currentPlayer.getColor().getValue();
        // pawn coordinates
        System.out.println("enter pawn's coordinates which you want to move");
        int pawnX, pawnY;
        while(true){
            pawnY= getY();
            pawnX = getX();
            if(board.getBoardElement(pawnX, pawnY) == currentColor) break;
            else System.out.println("bad pawn coordinates");
        }
        // move coordinates
        System.out.println("enter coordinates where you want to move");
        int moveX, moveY;
        MoveValidator moveValidator = new MoveValidator(board);
        while(true){
            moveY = getY();
            moveX = getX();
            if(detectMoveByDistance(pawnX, pawnY, moveX, moveY, BoardDistance.MOVE) && board.getBoardElement(moveX, moveY) == PawnColor.EMPTY.getValue()
                    && moveValidator.isMovePossible(pawnX, pawnY, moveX, moveY)){
                board.movePawn(pawnX, pawnY, moveX, moveY);
                break;
            }
            else if(detectMoveByDistance(pawnX, pawnY, moveX, moveY, BoardDistance.CAPTURE) && board.getBoardElement(moveX, moveY) == PawnColor.EMPTY.getValue())
            System.out.println("this move is illegal");
        }
    }

    private int getX(){
        while(true){
            System.out.println("enter number: ");
            int x = ScannerUtil.get_natural_num();
            if(x >= 0 && x <= board.getSize() / 2) return x*2;
            else System.out.println("Number must be between 0 - 4");
        }
    }

    private int getY(){
        while (true){
            System.out.println("Enter letter: ");
            char l = ScannerUtil.get_char();
            if(l == 'a') return 0;
            else if(l == 'b') return 2;
            else if(l == 'c') return 4;
            else if(l == 'd') return 6;
            else if(l == 'e') return 8;
            else System.out.println("bad letter");
        }
    }

    private Player getCurrentTurnPlayer(){
        return moveCounter % 2 == 0 ? p1 : p2;
    }

    private boolean detectMoveByDistance(int x1, int y1, int x2, int y2, BoardDistance expectedDistance){
        int distance = expectedDistance.getValue();
        int xDiff = Math.abs(x1 - x2);
        int yDiff = Math.abs(y1 - y2);
        return xDiff == distance && yDiff == 0 || xDiff == 0 && yDiff == distance || xDiff == distance && yDiff == distance;
    }
    private void printTurn(Player p){
        System.out.println("it " + p.getName() + " (" + p.getColor().getValue() + ") " + " turn ");
    }

    private boolean checkFieldsToCapture(int x1, int y1, int x2, int y2){

    }
}
