package org.example.game;

import org.example.enums.PawnColor;

public class Player {

    private String name;
    private final PawnColor color;

    public Player(PawnColor color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PawnColor getColor() {
        return color;
    }
}
