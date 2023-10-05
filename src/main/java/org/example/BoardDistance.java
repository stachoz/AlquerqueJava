package org.example;

public enum BoardDistance {
    MOVE(2), CAPTURE(4);
    private int value;
    BoardDistance(int value){
        this.value = value;
    }
    public int getValue(){return value;}
}
