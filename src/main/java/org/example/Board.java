package org.example;

public class Board {
    static private final char EMPTY_FIELD = PawnColor.EMPTY.getValue();
    static private final char WHITE_PAWN = PawnColor.WHITE.getValue();
    static private final char BLACK_PAWN = PawnColor.BLACK.getValue();
    static private final char[][] board = {
            // 1                    2              3                 4                 5
            {BLACK_PAWN, '-', BLACK_PAWN, '-', BLACK_PAWN, '-', BLACK_PAWN, '-', BLACK_PAWN},
            {'|', '\\', '|', '/', '|', '\\', '|', '/', '|'},
            {BLACK_PAWN, '-', BLACK_PAWN, '-', BLACK_PAWN, '-', BLACK_PAWN, '-', BLACK_PAWN},
            {'|', '/', '|', '\\', '|', '/', '|', '\\', '|'},
            {WHITE_PAWN, '-', WHITE_PAWN, '-', EMPTY_FIELD, '-', BLACK_PAWN, '-', BLACK_PAWN},
            {'|', '\\', '|', '/', '|', '\\', '|', '/', '|'},
            {WHITE_PAWN, '-', WHITE_PAWN, '-', WHITE_PAWN, '-', WHITE_PAWN, '-', WHITE_PAWN},
            {'|', '/', '|', '\\', '|', '/', '|', '\\', '|'},
            {WHITE_PAWN, '-', WHITE_PAWN, '-', WHITE_PAWN, '-', WHITE_PAWN, '-', WHITE_PAWN},
    };
    public void printBoard(){
        System.out.print("\n");
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                System.out.printf("%c", board[i][j]);
            }
            if (i % 2 == 0) {
                System.out.printf(" %d", i / 2);
            }
            System.out.print("\n");
        }
        char c = 'a';
        for(int i = 0; i < (board.length + 1) / 2; i++){
            System.out.printf("%c ", c++);
        }
        System.out.print("\n");
    }

    public void movePawn(int x1, int y1, int x2, int y2){
        char pawn = board[x1][y1];
        board[x1][y1] = EMPTY_FIELD;
        board[x2][y2] = pawn;
    }

    public void capture(int pawnX, int pawnY, int capturedX, int capturedY, int moveX, int moveY){
        char currentColor = board[pawnX][pawnY];
        board[pawnX][pawnY] = EMPTY_FIELD;
        board[capturedX][capturedY] = EMPTY_FIELD;
        board[moveX][moveY] =  currentColor;
    }

    public int getSize(){
        return board.length - 1;
    }

    public int getLength() {
        return board.length;
    }

    public char getBoardElement(int x, int y){
        return board[x][y];
    }

    public void takeOffPawn(int x, int y){
        board[x][y] = EMPTY_FIELD;
    }

}
