

import java.util.List;

public class AuhinnaValjastaja {
    private List<Kaart> kaardipakk;

    public AuhinnaValjastaja(List<Kaart> kaardipakk) {
        this.kaardipakk = kaardipakk;
    }

    public double arvutatasu(double panus, String tulemus) {

        return switch (tulemus) {
            case "Royal Flush" -> 250 * panus;
            case "Straight Flush" -> 50 * panus;
            case "Straight" -> 4 * panus;
            case "One Pair" -> panus; // Lisa vastav väärtus või loogika
            case "Two Pair" -> 2*panus;
            case "High Card" -> 0;
            case "Flush" -> 6*panus;
            case "Three of a Kind" -> 3*panus;
            case "Four of a Kind" -> 25*panus;
            case "Full House" -> 9*panus;


            default -> 0; // Kui pole vastet, tagasta 0
        };
}}






