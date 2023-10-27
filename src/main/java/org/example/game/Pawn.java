package org.example.game;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Pawn {
    private int x;
    private int y;
    private List<Coordinates> possibleCaptures = new ArrayList<>();

    public Pawn(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void addPossibleCapture(int x, int y){
        possibleCaptures.add(new Coordinates(x, y));
    }

    public int getLengthOfPossibleCaptures(){
        return possibleCaptures.toArray().length;
    }

    public Coordinates getPossibleCapture(int index){
        return possibleCaptures.get(index);
    }
}
