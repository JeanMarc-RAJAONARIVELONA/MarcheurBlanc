package com.MarcheurBlanc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public record Marcheur(String nom, Carte carte) {
    private static final Random random = new Random();

    public static List<Lieu> marche(Carte carte, Lieu départ, Lieu destination) {
        List<Lieu> lieux = new ArrayList<>();
        Set<Rue> visitedRues = new HashSet<>();
        lieux.add(départ);

        while (!lieux.getLast().equals(destination)) {
            Lieu current = lieux.getLast();
            List<Rue> connectedRues = carte.lesRues().stream()
                .filter(rue -> rue.aCeLieu(current) && !visitedRues.contains(rue))
                .collect(Collectors.toList());

            if (connectedRues.isEmpty()) {
                if (lieux.size() > 1) {
                    lieux.remove(lieux.size() - 1);
                    continue;
                } else {
                    break;
                }
            }

            Rue selectedRue = connectedRues.get(random.nextInt(connectedRues.size()));
            visitedRues.add(selectedRue);
            lieux.add(selectedRue.getLieuA().equals(current) ? selectedRue.getLieuB() : selectedRue.getLieuA());
        }
        return lieux;
    }
}
