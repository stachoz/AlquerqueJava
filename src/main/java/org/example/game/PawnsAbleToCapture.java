package org.example.game;

import java.util.ArrayList;
import java.util.List;

public class PawnsAbleToCapture {
    private List<Pawn> pawns = new ArrayList<>();
    public boolean hasAnyPawn(){
        return pawns.size() != 0 ? true : false;
    }

    public void addPawn(Pawn p){
        pawns.add(p);
    }

    public boolean isPawnAbleToCapture(Pawn pawn){
        for(Pawn p : pawns){
            if(pawn.getX() == p.getX() && pawn.getY() == p.getY()) return true;
        }
        return false;
    }

    public void reset() {
        pawns = new ArrayList<>();
    }
}