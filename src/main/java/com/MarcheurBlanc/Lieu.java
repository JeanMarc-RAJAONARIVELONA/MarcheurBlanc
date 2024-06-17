package com.MarcheurBlanc;

public class Lieu {
    private final String nom;

    public Lieu(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom;
    }
}