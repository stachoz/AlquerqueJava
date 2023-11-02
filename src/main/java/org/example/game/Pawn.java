package org.example.game;

import java.util.ArrayList;
import java.util.List;

public class Pawn {
    private int x;
    private int y;
    private final List<Coordinates> possibleCaptures = new ArrayList<>();
    private final List<Coordinates> possibleMoves = new ArrayList<>();
    public Pawn(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pawn(Coordinates co){
        this.x = co.getX();
        this.y = co.getY();
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
    public void addPossibleMove(int x, int y) {
        possibleMoves.add(new Coordinates(x, y));
    }

    public int capturedSize(){
        return possibleCaptures.size();
    }

    public int movesSize(){
        return possibleMoves.size();
    }

    public Coordinates getPossibleCapture(int index){
        return possibleCaptures.get(index);
    }

    public Coordinates getPossibleMove(int index){
        return possibleMoves.get(index);
    }

}
