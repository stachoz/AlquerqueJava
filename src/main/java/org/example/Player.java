package org.example;

public class Player {
    private final String name;
    private PawnColor color;

    public Player(String name, PawnColor color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public PawnColor getColor() {
        return color;
    }
}
