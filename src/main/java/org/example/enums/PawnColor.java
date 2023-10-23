package org.example.enums;

public enum PawnColor {
    BLACK('B'), WHITE('W'), EMPTY('X');
    private char value;
    PawnColor(char value){
        this.value = value;
    }

    public char getValue(){return value;}

}
