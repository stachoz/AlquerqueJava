package org.example.game;

import java.util.ArrayList;
import java.util.List;

public class PawnsAbleToCapture {
    private List<Pawn> pawns = new ArrayList<>();
    public boolean hasAnyPawn(){
        return !pawns.isEmpty();
    }

    public void addPawn(Pawn p){
        pawns.add(p);
    }

    public boolean isPawnAbleToCapture(int x, int y){
        for(Pawn p : pawns){
            if(p.getX() == x && p.getY() == y) return true;
        }
        return false;
    }

    public Pawn getPawn(int index) {
        return pawns.get(index);
    }

    public void reset() {
        pawns = new ArrayList<>();
    }

    public void print() {
        for(Pawn p : pawns){
            System.out.println("(" + p.getX() + "," + p.getY() + ")");
        }
    }

    public void updatePawn(int oldX, int oldY, int newX, int newY){
        for(Pawn p : pawns){
            if(p.getX() == oldX && p.getY() == oldY){
                p.setX(newX);
                p.setY(newY);
                break;
            }
        }
    }

    public int getLength() {
        return pawns.toArray().length;
    }
}