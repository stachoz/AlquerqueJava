package org.example;

public class GameBrain {
    private final Board board;
    private final Player p1;
    private final Player p2;
    private final MoveValidator moveValidator;
    private int moveCounter = 0;
    private boolean hasCaptured = false;
    private final PawnsAbleToCapture pawnsAbleToCapture;


    public GameBrain(Board board, Player p1, Player p2){
        this.board = board;
        this.p1 = p1;
        this.p2 = p2;
        this.moveValidator = new MoveValidator(board);
        this.pawnsAbleToCapture = new PawnsAbleToCapture((board.getLength() + 1) / 2);
    }

    public void makeMove(){
        Player currentPlayer = getCurrentTurnPlayer();
        printTurn(currentPlayer);
        char currentColor = currentPlayer.getColor().getValue();
        char enemyColor = enemyColor(currentColor);
        // hasCaptured and pawnAbletoCapture contain from previous turn
        if(hasCaptured && pawnsAbleToCapture.hasAnyPawn()){
            System.out.println("Enemy Player (" + enemyColor + ") missed capture!");
            System.out.println("Do you want to take off his pawn?");
            char answer = ScannerUtil.getChar();
            if(answer == 'y'){
                System.out.println("enter pawn's coordinates which you want to take off");
                int pawnX, pawnY;
                do {
                    pawnX = getX();
                    pawnY = getY();
                } while (!pawnsAbleToCapture.isPawnAbleToCapture(pawnX, pawnY));
                board.takeOffPawn(pawnX, pawnY);
            }
        }

        findPawnsAbleToCapture(enemyColor);

        System.out.println("enter pawn's coordinates which you want to move");
        int pawnX, pawnY;
        while(true){
            pawnY = getY();
            pawnX = getX();
            if(board.getBoardElement(pawnX, pawnY) == currentColor && canPawnMove(pawnX, pawnY, currentColor, BoardDistance.MOVE) || canPawnMove(pawnX, pawnY, currentColor, BoardDistance.CAPTURE)) break;
            else System.out.println("bad pawn coordinates");
        }
        // move coordinates
        System.out.println("enter coordinates where you want to move");
        while(true){
            int moveY = getY();
            int moveX = getX();
            if(board.getBoardElement(moveX, moveY) == PawnColor.EMPTY.getValue()) {
                if(detectMoveByDistance(pawnX, pawnY, moveX, moveY, BoardDistance.MOVE) && moveValidator.isThisMovePossible(pawnX, pawnY, moveX, moveY)){
                    board.movePawn(pawnX, pawnY, moveX, moveY);
                    hasCaptured = false;
                    break;
                }
                else if(detectMoveByDistance(pawnX, pawnY, moveX, moveY, BoardDistance.CAPTURE)){
                    int capturedX = capturedCoordinate(pawnX, moveX);
                    int capturedY = capturedCoordinate(pawnY, moveY);
                    if(moveValidator.isThisCapturePossible(pawnX, pawnY, capturedX, capturedY,moveX, moveY, enemyColor)){
                        board.capture(pawnX, pawnY, capturedX, capturedY, moveX, moveY);
                        hasCaptured = true;
                        break;
                    }
                }
            }
            System.out.println("this move is illegal");
        }
        moveCounter++;
    }

    private int getX(){
        while(true){
            System.out.println("enter number: ");
            int x = ScannerUtil.getNaturalNumber();
            if(x >= 0 && x <= board.getSize() / 2) return x*2;
            else System.out.println("Number must be between 0 - 4");
        }
    }

    private int getY(){
        while (true){
            System.out.println("Enter letter: ");
            char l = ScannerUtil.getChar();
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

    private char enemyColor(char currentColor) {
        return (currentColor == PawnColor.BLACK.getValue() ? PawnColor.WHITE.getValue() : PawnColor.BLACK.getValue());
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
    private int capturedCoordinate(int pawnCo, int moveCo){
        return pawnCo + ((moveCo - pawnCo) / 2);
    }
    private boolean canPawnMove(int x, int y, char currentColor, BoardDistance boardDistanceMoveType){
        int distance = boardDistanceMoveType.getValue();
        int[] directions = {-distance, 0, distance};
        for(int directionX : directions){
            for(int directionY : directions){
                if(directionX == 0 && directionY == 0) continue;
                int moveX = x + directionX;
                int moveY = y + directionY;
                if(boardDistanceMoveType == BoardDistance.MOVE && moveValidator.isThisMovePossible(x, y, moveX, moveY) && board.getBoardElement(moveX, moveY) == PawnColor.EMPTY.getValue()) return true;
                else if(boardDistanceMoveType == BoardDistance.CAPTURE){
                    int capturedX = capturedCoordinate(x, moveX);
                    int capturedY = capturedCoordinate(y, moveY);
                    char enemyColor = enemyColor(currentColor);
                    if(moveValidator.isThisCapturePossible(x, y, capturedX, capturedY, moveX, moveY, enemyColor)) return true;
                }
            }
        }
        return false;
    }
    private void findPawnsAbleToCapture(char currentColor){
        int size = board.getSize();
        for(int i = 0; i < size; i+=2){
            for(int j = 0; j < size; j+=2){
                if(board.getBoardElement(i, j) == currentColor && canPawnMove(i, j, currentColor, BoardDistance.CAPTURE)) pawnsAbleToCapture.setPawnAbleToCapture(true, i / 2, j / 2);
            }
        }
    }
}