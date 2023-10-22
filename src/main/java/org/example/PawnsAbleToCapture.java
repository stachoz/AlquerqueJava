package org.example;

public class PawnsAbleToCapture {
    private final int size;
    private int[][] tab;
    public PawnsAbleToCapture(int size) {
        this.size = size;
        this.tab = new int[size][size];
    }
    public boolean hasAnyPawn(){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(tab[i][j] == 1) return true;
            }
        }
        return false;
    }
    public void setPawnAbleToCapture(boolean state, int x, int y){
        tab[x][y] = state ? 1 : 0;
    }
    public boolean isPawnAbleToCapture(int x, int y){
        return tab[x][y] == 1 ? true : false;
    }

    public void resetBoard(){
        tab = new int[size][size];
    }
}