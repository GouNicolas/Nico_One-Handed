package model;

public class Card {
    private int value;
    private String color;

    public Card(int value, String color) {
        this.value = value;
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public String getColor() {
        return color;
    }

    public String toString() {
        return value + " " + color;
    }
}
