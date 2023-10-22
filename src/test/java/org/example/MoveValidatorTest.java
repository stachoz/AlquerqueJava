package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class MoveValidatorTest {
    MoveValidator moveValidator;
    Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
        moveValidator = new MoveValidator(board, emptyFieldColor);
    }

    @Test
    void canMoveUpFromAboveTop() {
        boolean result = moveValidator.canMoveUp(0);
        assertFalse(result);
    }

    @Test
    void canMoveUpFromBottom() {
        boolean result = moveValidator.canMoveUp(board.getSize());
        assertTrue(result);
    }

    @Test
    void canMoveDownFromBottom(){
        boolean result = moveValidator.canMoveDown(board.getSize());
        assertFalse(result);
    }

    @Test
    void canMoveDownFromTop(){
        boolean result = moveValidator.canMoveDown(0);
        assertTrue(result);
    }

    @Test
    void canMoveLeftFromLeftSide(){
        boolean result = moveValidator.canMoveLeft(0);
        assertFalse(result);
    }

    @Test
    void canMoveLeftFromOtherPlace(){
        boolean result = moveValidator.canMoveLeft(2);
        assertTrue(result);
    }

    @Test
    void canMoveRightFromRightSide(){
        boolean result = moveValidator.canMoveRight(board.getSize());
        assertFalse(result);
    }

    @Test
    void canMoveRightFromOtherPlace(){
        boolean result = moveValidator.canMoveRight(board.getSize());
        assertFalse(result);
    }
    @Test
    void canMoveUpperLeftBevelFromLeftBoardSide(){
        boolean result = moveValidator.canMoveUpperLeftBevel(0, 6);
        assertFalse(result);
    }

    @Test
    void canMoveUpperLeftBevelFromProperField(){
        boolean result = moveValidator.canMoveUpperLeftBevel(2, 2);
        assertTrue(result);
    }

    @Test
    void canMoveUpperLeftBevelFromInappropriateField(){
        boolean result = moveValidator.canMoveUpperLeftBevel(4, 2);
        assertFalse(result);
    }

    @Test
    void canMoveUpperRightBevelFromRightBottom(){
        int s = board.getSize();
        boolean result = moveValidator.canMoveUpperRightBevel(s, s);
        assertFalse(result);
    }

    @Test
    void canMoveUpperRightBevelFromRightSide(){
        boolean result = moveValidator.canMoveUpperRightBevel(0, board.getSize());
        assertFalse(result);
    }

    @Test
    void canMoveUpperRightBevelFromMid(){
        boolean result = moveValidator.canMoveUpperRightBevel(4, 4);
        assertTrue(result);
    }

    @Test
    void canMoveUpperRightBevelFromProperField(){
        boolean result = moveValidator.canMoveUpperRightBevel( 2, 6);
        assertTrue(result);
    }

    @Test
    void canMoveLowerLeftBevelFromLeftSide(){
        boolean result = moveValidator.canMoveLowerLeftBevel(board.getSize() - 2,0);
        assertFalse(result);
    }

    @Test
    void canMoveLowerLeftBevelFromMid(){
        boolean result = moveValidator.canMoveLowerLeftBevel(4,4);
        assertTrue(result);
    }

    @Test
    void canMoveLowerLeftBevelFromProperField(){
        boolean result = moveValidator.canMoveLowerLeftBevel(board.getSize() - 2,2);
        assertTrue(result);
    }

    @Test
    void canMoveLowerLeftBevelFromInappropriateField(){
        boolean result = moveValidator.canMoveLowerLeftBevel(board.getSize() - 2,4);
        assertFalse(result);
    }

    @Test
    void canMoveLowerRightBevelFromRightSide(){
        boolean result = moveValidator.canMoveLowerRightBevel(0, board.getSize());
        assertFalse(result);
    }

    @Test
    void canMoveLowerRightBevelFromBottom(){
        boolean result = moveValidator.canMoveLowerRightBevel(board.getSize(),4);
        assertFalse(result);
    }

    @Test
    void canMoveLowerRightBevelFromMid(){
        boolean result = moveValidator.canMoveLowerRightBevel(4,4);
        assertTrue(result);
    }
    @Test
    void canMoveLowerRightBevelFromInappropriateFiled(){
        boolean result = moveValidator.canMoveLowerRightBevel(6,4);
        assertFalse(result);
    }

}