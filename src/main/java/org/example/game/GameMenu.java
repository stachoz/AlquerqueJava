package org.example.game;

import org.example.utils.ScannerUtil;

public class GameMenu {
    private final Player p1;
    private final Player p2;

    public GameMenu(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public int  moveMenu(){
        System.out.println("1. Manual Move");
        System.out.println("2. AI move");
        System.out.println("3. Draw");
        System.out.println("4. Surrender");
        return ScannerUtil.getNaturalNumberFromRange(1, 4);
    }
}
