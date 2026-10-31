package com.example.crochetapp;

public class Stitch {
    public final String abbreviation;
    public final String name;
    public final String difficulty;
    public final String howTo;

    public Stitch(String abbreviation, String name, String difficulty, String howTo) {
        this.abbreviation = abbreviation;
        this.name = name;
        this.difficulty = difficulty;
        this.howTo = howTo;
    }
}
