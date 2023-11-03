package org.example.game;

import org.example.utils.ScannerUtil;

public class GameMenu {
    public int moveMenu(){
        System.out.println("1. Manual Move");
        System.out.println("2. AI move");
        System.out.println("3. Draw");
        System.out.println("4. Surrender");
        return ScannerUtil.getNaturalNumberFromRange(1, 4);
    }

}
